package ui;

import model.Equipment;
import model.LabInventory;

import java.util.List;
import java.util.Scanner;

public class EquipmentManagerApp {
    private LabInventory inventory;
    private Equipment centrifuge;
    private Equipment massSpec;
    private Scanner input;
    private boolean selected;

    public EquipmentManagerApp() {
        runManager();
    }

    // MODIFIES: this
    // EFFECTS: procceses user input at the main menu, until user quits, or goes to equipment selection page
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
        System.out.println("BYE");

    }

    // EFFECTS: processes user commands at main-menu page
    private void processCommand(String command) {
        if (command.equals("c")) {
            printEquipmentStatus();
        } else if (command.equals("v")) {
            doViewInventory();
        } else if (command.equals("s")) {
            doShowStatistics();
        }
    }

    // EFFECTS: displays a list of main-menu options
    private void showOptions() {
        System.out.println("\nChoose an action:");
        System.out.println("\nc -> current status of all equipment");
        System.out.println("\nv -> view and select equipment from the inventory");
        System.out.println("\ns -> view statistics of inventory");
        System.out.println("\nq -> quit");
    }

    //MODIFIES: this
    //EFFECTS : initializes input, inventory items, and adds users to equipment
    private void init() {
        input = new Scanner(System.in);
        //input.useDelimiter("\n");
        centrifuge = new Equipment("Centrifuge", "online", 3000);
        massSpec = new Equipment("Mass Spectrometer", "offline", 20000);
        inventory = new LabInventory();
        inventory.addEquipment(centrifuge);
        inventory.addEquipment(massSpec);
        centrifuge.addUser("James");
        centrifuge.addUser("Lilly");
        massSpec.addUser("Kyle");
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

    // EFFECTS: displays a list of possible actions for the equipment selected
    private void showEquipmentOptions() {
        System.out.println("\nChoose an action:");
        System.out.println("\ne -> add an expense");
        System.out.println("\ns -> update status");
        System.out.println("\nu -> add a user");
        System.out.println("\nq -> quit");
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
        }
    }

    // EFFECTS: adds a user to the selected equipment's user history
    private void doAddUser(Equipment e) {
        System.out.println("Please enter a name to add to the user history");
        String userName = input.next();
        e.addUser(userName);
        System.out.println("Added " + userName + "to user history");
        showEquipmentOptions();
        equipmentCommands(e);
    }

    // EFFECTS: updates the status of the selected equipment
    private void doUpdateStatus(Equipment e) {
        System.out.println("Please enter the new status of the " + e.getName());
        String newStatus = input.next();
        e.setStatus(newStatus);
        System.out.println("New " + e.getName() + "status has been set to: " + newStatus);
        showEquipmentOptions();
        equipmentCommands(e);
    }

    // EFFECTS: adds an expense to the equipment's running cost
    private void doAddExpense(Equipment e) {
        System.out.println("Please add the new " + e.getName() + " expense");
        int expense = input.nextInt();
        e.addCost(expense);
        System.out.println("Updated cost of the " + e.getName() + " is now: $" + expense);
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