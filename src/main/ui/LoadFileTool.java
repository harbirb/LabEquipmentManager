package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFileTool {
    private JButton button;
    private EquipmentManagerGUI manager;

    public LoadFileTool(JComponent parent, EquipmentManagerGUI manager) {
        button = new JButton("Load Inventory from file?");
        this.manager = manager;
        parent.add(button);
        button.addActionListener(new LoadFileClickHandler());
    }

    private class LoadFileClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            manager.loadInventory();
            manager.displayMainMenu();
        }
    }

}
