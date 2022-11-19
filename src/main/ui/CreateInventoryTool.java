package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the button to load data from file
public class CreateInventoryTool {
    private JButton button;
    private EquipmentManagerGUI manager;

    //EFFECTS: constructs the button and adds button to the parent JComponent
    public CreateInventoryTool(JComponent parent, EquipmentManagerGUI manager) {
        button = new JButton("Create new inventory?");
        this.manager = manager;
        parent.add(button);
        button.addActionListener(new CreateNewInventoryHandler());
    }

    //Represents the actionListener for the createInventory button
    private class CreateNewInventoryHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: calls function to show the main menu of the application with an empty inventory
        @Override
        public void actionPerformed(ActionEvent e) {
            manager.displayMainMenu();
        }
    }
}

