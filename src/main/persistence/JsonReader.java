package persistence;


import model.Equipment;
import model.LabInventory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the lab inventory from JSON data stored in a data file
// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {

    private String source;

    //EFFECTS: constructs a reader to read the data from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads the workroom from a file and returns it, throws an IO Exception if an error occurs
    //in reading data from the file
    public LabInventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLabInventory(jsonObject);
    }

    //EFFECTS: reads the source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses through the lab inventory from the JSON object and returns it
    private LabInventory parseLabInventory(JSONObject jsonObject) {
        LabInventory li = new LabInventory();
        addEquipments(li, jsonObject);
        return li;
    }

    //MODIFIES: li
    //EFFECTS: parses equipments from the JSON object and adds each one to the lab inventory
    private void addEquipments(LabInventory li, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("labInventory");
        for (Object json : jsonArray) {
            JSONObject nextEquipment = (JSONObject) json;
            addEquipment(li, nextEquipment);
        }
    }

    //MODIFIES: li
    //EFFECTS: parses equipment from the JSON object and adds each one to the lab inventory
    private void addEquipment(LabInventory li, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String status = jsonObject.getString("status");
        JSONArray userHistory = jsonObject.getJSONArray("userHistory");
        int runningCost = jsonObject.getInt("runningCost");
        Equipment equipment = new Equipment(name, status, runningCost);
        for (Object user: userHistory) {
            equipment.addUser((String) user);
        }
        li.addEquipment(equipment);
    }

}
