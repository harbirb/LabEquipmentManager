package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the button to load data from file
public class SaveFileTool {
    private JButton button;

    private EquipmentManagerGUI manager;

    //EFFECTS: constructs the button and adds it to the parent JComponent
    public SaveFileTool(JComponent parent, EquipmentManagerGUI manager) {
        this.manager = manager;
        button = new JButton("Save to file");
        parent.add(button);
        button.addActionListener(new SaveFileHandler());
    }

    //Represents the actionListener for the SaveFile button
    private class SaveFileHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: calls function to save the current inventory to file
        @Override
        public void actionPerformed(ActionEvent e) {
            manager.saveInventory();
            manager.displayMainMenu();
        }
    }
}
