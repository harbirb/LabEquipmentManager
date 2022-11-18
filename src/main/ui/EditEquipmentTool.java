package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEquipmentTool {

    private JButton button;
    private EquipmentManagerGUI manager;

    EditEquipmentTool(EquipmentManagerGUI manager, JList list) {
        this.manager = manager;
        button = new JButton("Edit");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
