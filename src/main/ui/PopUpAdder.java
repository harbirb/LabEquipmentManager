package ui;

import model.Equipment;
import model.LabInventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpAdder extends JFrame {
    private JFrame frame;
    private JTextField upfrontCost;
    private JTextField status;
    private JTextField name;
    private JButton addEquipmentButton;
    private EquipmentManagerGUI manager;
    private LabInventory inventory;


    public PopUpAdder(EquipmentManagerGUI manager) {
        inventory = manager.getInventory();
        this.manager = manager;
        upfrontCost = new JTextField(20);
        AddEquipmentListener addEquipmentListener = new AddEquipmentListener();
        upfrontCost.addActionListener(addEquipmentListener);
        status = new JTextField(20);
        status.addActionListener(addEquipmentListener);
        name = new JTextField(20);
        name.addActionListener(addEquipmentListener);
        addEquipmentButton = new JButton("ADD EQUIPMENT");
        addEquipmentButton.addActionListener(addEquipmentListener);
        createPopUp();
    }

    private void createPopUp() {
        frame = new JFrame("New Equipment Entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        JPanel textBoxes = new JPanel();
        textBoxes.setLayout(new GridLayout(3,2,30,10));
        textBoxes.add(new JLabel("Equipment name"));
        textBoxes.add(name);
        textBoxes.add(new JLabel("Equipment Status"));
        textBoxes.add(status);
        textBoxes.add(new JLabel("Upfront Equipment Cost"));
        textBoxes.add(upfrontCost);
        add(textBoxes, BorderLayout.CENTER);
        add(addEquipmentButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class AddEquipmentListener  implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String equipName = name.getText();
            String equipStatus = status.getText();
            int equipUpfrontCost = Integer.parseInt(upfrontCost.getText());
            Equipment newEquipment = new Equipment(equipName, equipStatus, equipUpfrontCost);
            inventory.addEquipment(newEquipment);
            dispose();
            manager.displayMainMenu();

        }
    }
}
