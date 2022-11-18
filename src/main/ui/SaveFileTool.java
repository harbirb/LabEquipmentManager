package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileTool {
    private JButton button;

    private EquipmentManagerGUI manager;

    public SaveFileTool(JComponent parent, EquipmentManagerGUI manager) {
        this.manager = manager;
        button = new JButton("Save to file");
        parent.add(button);
        button.addActionListener(new SaveFileHandler());
    }

    private class SaveFileHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            manager.saveInventory();
            manager.displayMainMenu();
        }
    }
}
