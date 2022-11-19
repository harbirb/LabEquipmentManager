package ui;

import model.Equipment;
import model.LabInventory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

//Represents the panel which displays the Equipment currently in the Inventory
public class EquipmentList extends JPanel implements ListSelectionListener {

    private JList inventoryList;
    private DefaultListModel listModel;

    private static final String editString = "Edit";

    //EFFECTS: creates a new inventory and adds it into a scroll pane, and makes an edit button
    public EquipmentList(EquipmentManagerGUI manager) {
        super(new BorderLayout());
        LabInventory inventory = manager.getInventory();
        listModel = new DefaultListModel();
        for (Equipment e: inventory.getLabInventory()) {
            listModel.addElement(e.getName());
        }

        inventoryList = new JList(listModel);
        inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inventoryList.addListSelectionListener(this);
        inventoryList.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(inventoryList);

        JButton editButton = new JButton(editString);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = inventoryList.getSelectedIndex();
                String name = listModel.getElementAt(index).toString();
                Equipment equipmentToEdit = inventory.searchEquipmentByName(name);
                PopUpEditor editor = new PopUpEditor(equipmentToEdit, manager);
            }
        });
        add(listScrollPane, BorderLayout.CENTER);
        add(editButton, BorderLayout.SOUTH);
    }
    

    //EFFECTS: adds the panel to the JFrame, and displays the scroll pane
    public void createAndShowGUI(JFrame frame) {

        this.setOpaque(true);
        frame.add(this);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: required by implementation of ListSelectionListener
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
