package ui;

import model.Equipment;
import model.LabInventory;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Represents the Equipment Manager application
public class EquipmentManagerApp {
    private static final String JSON_STORE = "./data/labInventory.json";
    private LabInventory inventory;
    private Scanner input;
    private boolean selected;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    //EFFECTS: constructs the lab inventory and runs the application
    public EquipmentManagerApp() throws FileNotFoundException {
        inventory = new LabInventory();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runManager();
    }

    // MODIFIES: this
    // EFFECTS: processes user input at the main menu, until user quits, or goes to equipment selection page
    public void runManager() {
        boolean running = true;
        selected = false;
        String command = null;

        init();
        while (running) {
            if (!selected) {
                showOptions();
                command = input.next();
                command = command.toLowerCase();
                processCommand(command);
            }
            if (command.equals("q")) {
                running = false;
            }
        }
        System.out.println("Closing Equipment Manager App. Goodbye!");

    }

    // EFFECTS: processes user commands at main-menu page
    private void processCommand(String command) {
        if (command.equals("c")) {
            printEquipmentStatus();
        } else if (command.equals("v")) {
            doViewInventory();
        } else if (command.equals("p")) {
            doShowStatistics();
        } else if (command.equals("a")) {
            addEquipment();
        } else if (command.equals("s")) {
            saveInventory();
        } else if (command.equals("l")) {
            loadInventory();
        }
    }

    // EFFECTS: displays a list of main-menu options
    private void showOptions() {
        System.out.println("\nChoose an action:");
        System.out.println("\na -> add new equipment to the inventory");
        System.out.println("\nc -> current status of all equipment");
        System.out.println("\nv -> view and select equipment from the inventory");
        System.out.println("\np -> view statistics of inventory");
        System.out.println("\nl -> load lab inventory from file");
        System.out.println("\ns -> save current lab inventory to file");
        System.out.println("\nq -> quit");
    }

    //EFFECTS : initializes input
    // delimiter \n ensures that spaces between inputs are recognized
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    //EFFECTS: adds new equipment to the lab inventory
    private void addEquipment() {
        System.out.println("Please provide the name of the new equipment: ");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("invalid input");
            name = input.next();
        }
        System.out.println("Please provide the current status of the new equipment: ");
        String status = input.next();
        while (status.length() == 0) {
            System.out.println("invalid input");
            status = input.next();
        }
        System.out.println("Please provide the upfront cost of the new equipment: ");
        int cost = input.nextInt();
        while (cost <= 0) {
            System.out.println("invalid input");
            cost = input.nextInt();
        }
        Equipment newEquipment = new Equipment(name, status, cost);
        inventory.addEquipment(newEquipment);
        System.out.println(name + " has been added to the lab inventory!");

    }

    //EFFECTS: displays the equipment selection screen with inventory items displayed
    private void doViewInventory() {
        for (Equipment e : inventory.getLabInventory()) {
            System.out.println(e.getName());
        }
        System.out.println("Please enter the name of the equipment to select it:");
        String equipmentName = input.next();
        Equipment e = inventory.searchEquipmentByName(equipmentName);
        if (e != null) {
            selected = true;
            System.out.println("You have selected " + e.getName());
            showEquipmentOptions();
            equipmentCommands(e);
        } else {
            System.out.println("Equipment not found in inventory. Returning to main menu");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads lab inventory from file
    private void loadInventory() {
        try {
            inventory = jsonReader.read();
            System.out.println("Loaded the saved lab inventory from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: saves the current lab inventory to file
    private void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventory);
            jsonWriter.close();
            System.out.println("Saved the lab inventory to :" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }
    }


    // EFFECTS: displays a list of possible actions for the equipment selected
    private void showEquipmentOptions() {
        System.out.println("\nChoose an action:");
        System.out.println("\ne -> add an expense");
        System.out.println("\ns -> update status");
        System.out.println("\nu -> add a user");
        System.out.println("\nr -> remove from the lab inventory");
        System.out.println("\nq -> quit to main menu");
    }

    // EFFECTS : processes user commands at the equipment selection page
    private void equipmentCommands(Equipment e) {
        String command;
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("e")) {
            doAddExpense(e);
        } else if (command.equals("s")) {
            doUpdateStatus(e);
        } else if (command.equals("u")) {
            doAddUser(e);
        } else if (command.equals("q")) {
            selected = false;
        } else if (command.equals("r")) {
            removeEquipment(e);
        } else {
            System.out.println("unknown command, try again");
            equipmentCommands(e);
        }
    }

    //EFFECTS: removes the selected equipment from the lab inventory
    private void removeEquipment(Equipment e) {
        inventory.removeEquipment(e);
        System.out.println(e.getName() + " has been removed from the lab inventory");
        doViewInventory();
    }

    // EFFECTS: adds a user to the selected equipment's user history
    private void doAddUser(Equipment e) {
        System.out.println("Please enter a name to add to the user history");
        String userName = input.next();
        e.addUser(userName);
        System.out.println("Added " + userName + " to user history");
        showEquipmentOptions();
        equipmentCommands(e);
    }

    // EFFECTS: updates the status of the selected equipment
    private void doUpdateStatus(Equipment e) {
        System.out.println("Please enter the new status of the " + e.getName());
        String newStatus = input.next();
        e.setStatus(newStatus);
        System.out.println("New " + e.getName() + " status has been set to: " + newStatus);
        showEquipmentOptions();
        equipmentCommands(e);
    }

    // EFFECTS: adds an expense to the equipment's running cost
    private void doAddExpense(Equipment e) {
        System.out.println("Please add the new " + e.getName() + " expense");
        int expense = input.nextInt();
        e.addCost(expense);
        System.out.println("Updated cost of the " + e.getName() + " is now: $" + e.getRunningCost());
        showEquipmentOptions();
        equipmentCommands(e);
    }

    // EFFECTS: displays the most used equipment and most expensive equipment in the inventory
    private void doShowStatistics() {
        Equipment mostUsed = inventory.getMostUsedEquipment();
        Equipment mostExpensive = inventory.getMostExpensiveEquipment();
        System.out.println("The most used equipment is: " + mostUsed.getName() + " with "
                + mostUsed.getUsers().size() + " cumulative users");
        System.out.println("The most expensive equipment is: " + mostExpensive.getName()
                + " with a total running cost of $" + mostExpensive.getRunningCost());
    }

    // EFFECTS: displays the status of all equipment in the inventory
    private void printEquipmentStatus() {
        List<String> statusList = inventory.getEquipmentStatus();
        System.out.println("Status for all equipment: ");
        for (String status: statusList) {
            System.out.println(status);

        }
    }




}
