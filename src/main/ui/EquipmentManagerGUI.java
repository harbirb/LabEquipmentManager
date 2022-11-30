package ui;

import model.EventLog;
import model.LabInventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

//Represents the GUI of the EquipmentManager
public class EquipmentManagerGUI extends JFrame {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 520;
    private JsonWriter jsonWriter;
    private LabInventory inventory;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/labInventory.json";

    //EFFECTS: initializes the main JFrame, initializes fields, and displays welcome screen
    public EquipmentManagerGUI() throws IOException {
        super("Equipment Manager");
        inventory = new LabInventory();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        displayWelcomeScreen();
    }

    //MODIFIES: this
    //EFFECTS: adds the welcome screen and the buttons (to load from file or create a new inventory).
    //EFFECTS: Displays the JFrame
    private void displayWelcomeScreen() throws IOException {
        JComponent w = new WelcomeScreen();
        this.setContentPane(w);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel fileOptions = new JPanel();
        fileOptions.setLayout(new GridLayout(1,0));
        fileOptions.setSize(new Dimension(0, 0));
        add(fileOptions, BorderLayout.SOUTH);
        new LoadFileTool(fileOptions, this);
        new CreateInventoryTool(fileOptions, this);
        setVisible(true);
        printEventLog();
    }

    private void printEventLog() {
        EventLog log = EventLog.getInstance();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (model.Event event : log) {
                    System.out.println(event);
                }
                System.exit(0);
            }
        });

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

    //MODIFIES: this
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

    //MODIFIES: this
    //EFFECTS: removes current elements of the JFrame, initializes the EquipmentList panel, and displays main menu
    public void displayMainMenu() {
        this.getContentPane().removeAll();
        this.repaint();
        EquipmentList el = new EquipmentList(this);
        el.createAndShowGUI(this);
        displayMenuOptions();
    }

    //MODIFIES: this
    //EFFECTS: creates the menu options panel, adds it to the JFrame, and displays it
    private void displayMenuOptions() {
        JPanel menuOptions = new JPanel();
        JButton addEquipment = new JButton("Add New Equipment");
        EquipmentManagerGUI manager = this;
        addEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopUpAdder(manager);
            }
        });
        SaveFileTool saveFileTool = new SaveFileTool(menuOptions, this);

        menuOptions.setLayout(new GridLayout(2, 0, 10, 10));
        menuOptions.add(addEquipment);
        this.add(menuOptions, BorderLayout.EAST);
        this.repaint();
        this.setVisible(true);
    }

    //EFFECTS: initializes an instance of EquipmentManagerGUI
    public static void main(String[] args) {
        try {
            new EquipmentManagerGUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //EFFECTS: returns the currently used Lab Inventory
    public LabInventory getInventory() {
        return this.inventory;
    }
}
