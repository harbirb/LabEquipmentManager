package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the button to load data from file
public class LoadFileTool {
    private JButton button;
    private EquipmentManagerGUI manager;

    //EFFECTS: constructs the button and adds button to the parent JComponent
    public LoadFileTool(JComponent parent, EquipmentManagerGUI manager) {
        button = new JButton("Load Inventory from file?");
        this.manager = manager;
        parent.add(button);
        button.addActionListener(new SaveFileClickHandler());
    }

    //Represents the actionListener for the loadFile button
    private class SaveFileClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: calls functions to load from file and show the main menu of the application
        @Override
        public void actionPerformed(ActionEvent e) {
            manager.loadInventory();
            manager.displayMainMenu();
        }
    }

}
