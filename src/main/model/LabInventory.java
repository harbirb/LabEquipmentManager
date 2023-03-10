package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a Lab Inventory with a collection of equipment
public class LabInventory implements Writable {
    private List<Equipment> labInventory;

    //EFFECTS: constructs an empty list of equipment in the lab inventory
    public LabInventory() {
        labInventory = new ArrayList<>();
    }

    //EFFECTS: returns the list of equipment in the lab inventory
    public List<Equipment> getLabInventory() {
        return labInventory;
    }

    //REQUIRES: equipment is not null
    //MODIFIES: this
    //EFFECTS: adds the equipment to the lab inventory, if it is not already in the inventory
    public boolean addEquipment(Equipment equipment) {
        if (labInventory.contains(equipment)) {
            return false;
        } else {
            labInventory.add(equipment);
            EventLog.getInstance().logEvent(new Event(equipment.getName()
                    + " has been added to the lab inventory!"));
            return true;
        }
    }

    //REQUIRES: equipment already exists within the lab inventory
    //MODIFIES: this
    //EFFECTS: removes the equipment from the lab inventory

    public void removeEquipment(Equipment equipment) {
        labInventory.remove(equipment);
        EventLog.getInstance().logEvent(new Event(equipment.getName()
                + " has been removed from the lab inventory"));
    }


    //EFFECTS: returns the Equipment named equipmentName, null otherwise
    public Equipment searchEquipmentByName(String equipmentName) {
        Equipment selectedEquipment = null;
        for (Equipment e: labInventory) {
            if (e.getName().equals(equipmentName)) {
                selectedEquipment = e;
            }
        }
        return selectedEquipment;
    }


    //EFFECTS: returns the status of all equipment in the lab inventory with the format Name : Status

    public List<String> getEquipmentStatus() {
        List<String> equipmentStatusList = new ArrayList<>();
        for (Equipment e: labInventory) {
            equipmentStatusList.add(e.getName() + " : " + e.getStatus());
        }
        return equipmentStatusList;
    }

    //EFFECTS: returns equipment in the inventory with the greatest running cost
    public Equipment getMostExpensiveEquipment() {
        int highestCost = 0;
        Equipment mostExpensiveEquipment = null;
        for (Equipment e: labInventory) {
            if (e.getRunningCost() > highestCost) {
                highestCost = e.getRunningCost();
                mostExpensiveEquipment = e;
            }
        }
        return mostExpensiveEquipment;
    }

    //REQUIRES: User list of equipment must not be null
    //EFFECTS: returns equipment in the inventory with the greatest number of users
    public Equipment getMostUsedEquipment() {
        int mostUsers = 0;
        Equipment mostUsedEquipment = null;
        for (Equipment e: labInventory) {
            if (e.getUsers().size() > mostUsers) {
                mostUsers = e.getUsers().size();
                mostUsedEquipment = e;
            }
        }
        return mostUsedEquipment;
    }

    //EFFECTS: returns the lab inventory as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("labInventory", labInventoryToJson());
        return json;
    }

    //EFFECTS: returns the equipment in the lab inventory as a JSON array
    private JSONArray labInventoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Equipment e: labInventory) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }
}
