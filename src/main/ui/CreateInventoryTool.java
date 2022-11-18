package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateInventoryTool {
    private JButton button;

    private EquipmentManagerGUI manager;

    public CreateInventoryTool(JComponent parent, EquipmentManagerGUI manager) {
        button = new JButton("Create new inventory?");
        this.manager = manager;
        parent.add(button);
        button.addActionListener(new CreateNewInventoryHandler());
    }

    private class CreateNewInventoryHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            manager.displayMainMenu();
        }
    }
}

