package ui;

import model.Equipment;
import model.LabInventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpEditor extends JFrame {
    private JFrame frame;
    private JTextField newExpense;
    private JTextField newStatus;
    private JTextField newUser;
    private JButton expenseButton;
    private JButton statusButton;
    private JButton userButton;

    private JButton removeButton;
    private Equipment equip;

    public PopUpEditor(Equipment equip, EquipmentManagerGUI manager, DefaultListModel listModel, int index) {
        LabInventory inventory = manager.getInventory();
        newExpense = new JTextField(20);
        expenseButton = new JButton("Add Expense");
        AddExpenseListener addExpenseListener = new AddExpenseListener();
        newExpense.addActionListener(addExpenseListener);
        expenseButton.addActionListener(addExpenseListener);
        newStatus = new JTextField(20);
        statusButton = new JButton("Update Status");
        UpdateStatusListener updateStatusListener = new UpdateStatusListener();
        newStatus.addActionListener(updateStatusListener);
        statusButton.addActionListener(updateStatusListener);
        newUser = new JTextField(20);
        userButton = new JButton("Add User");
        AddUserListener addUserListener = new AddUserListener();
        newUser.addActionListener(addUserListener);
        userButton.addActionListener(addUserListener);
        this.equip = equip;
        removeButton = new JButton("REMOVE EQUIPMENT");
        createPopUp();
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.removeEquipment(equip);
                manager.displayMainMenu();
            }
        });
    }

    private void createPopUp() {
        frame = new JFrame(equip.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        JPanel textBoxes = new JPanel();
        textBoxes.setLayout(new GridLayout(3,0));
        textBoxes.add(newExpense);
        textBoxes.add(newStatus);
        textBoxes.add(newUser);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(3,0));
        buttonPane.add(expenseButton);
        buttonPane.add(statusButton);
        buttonPane.add(userButton);
        add(textBoxes, BorderLayout.EAST);
        add(buttonPane, BorderLayout.WEST);
        add(removeButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class AddExpenseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int expense = Integer.parseInt(newExpense.getText());
            equip.addCost(expense);
            newExpense.requestFocusInWindow();
            newExpense.setText("");
        }
    }

    private class UpdateStatusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String status = newStatus.getText();
            equip.setStatus(status);
            newStatus.requestFocusInWindow();
            newStatus.setText("");
        }
    }

    private class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = newUser.getText();
            equip.addUser(user);
            newUser.requestFocusInWindow();
            newUser.setText("");
        }
    }


}
