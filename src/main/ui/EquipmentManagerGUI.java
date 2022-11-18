package ui;

import model.LabInventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EquipmentManagerGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private JsonWriter jsonWriter;
    private LabInventory inventory;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/labInventory.json";

    public EquipmentManagerGUI() throws IOException {
        super("Equipment Manager");
        inventory = new LabInventory();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        displayWelcomeScreen();
    }

    private void displayWelcomeScreen() throws IOException {
        JComponent w = new WelcomeScreen();
        //this.getContentPane().add(w);
        this.setContentPane(w);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel fileOptions = new JPanel();
        fileOptions.setLayout(new GridLayout(1,0));
        fileOptions.setSize(new Dimension(0, 0));
        add(fileOptions, BorderLayout.SOUTH);
        LoadFileTool lft = new LoadFileTool(fileOptions, this);
        CreateInventoryTool cit = new CreateInventoryTool(fileOptions, this);
        setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: loads lab inventory from file
    public void loadInventory() {
        try {
            inventory = jsonReader.read();
            System.out.println("Loaded the saved lab inventory from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: saves the current lab inventory to file
    public void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventory);
            jsonWriter.close();
            System.out.println("Saved the lab inventory to :" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }
    }


    public void displayMainMenu() {
        this.getContentPane().removeAll();
        this.repaint();
        EquipmentList el = new EquipmentList(this);
        el.createAndShowGUI(this);
    }

    private void displayMenuOptions() {
        JPanel menuOptions = new JPanel();



    }

    public static void main(String[] args) {
        try {
            new EquipmentManagerGUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public LabInventory getInventory() {
        return this.inventory;
    }
}
