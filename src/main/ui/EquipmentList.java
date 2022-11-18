package ui;

import model.Equipment;
import model.LabInventory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

public class EquipmentList extends JPanel implements ListSelectionListener {

    private JList inventoryList;
    private DefaultListModel listModel;

    private static final String editString = "Edit";

    public EquipmentList(EquipmentManagerGUI manager) {
        super(new BorderLayout());
        LabInventory inventory = manager.getInventory();
        listModel = new DefaultListModel();
        for (Equipment e: inventory.getLabInventory()) {
            listModel.addElement(e.getName());
        }

        inventoryList = new JList(listModel);
        inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //inventoryList.setSelectedIndex(0);
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
    

    public void createAndShowGUI(JFrame frame) {
        //Create and set up the content pane.
        this.setOpaque(true);
        frame.add(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

//    public void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
    }
}
