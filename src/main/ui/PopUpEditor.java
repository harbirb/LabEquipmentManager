package ui;

import model.Equipment;
import model.LabInventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a pop-up window to edit parameters of a selected equipment
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

    //EFFECTS: constructs a PopUpEditor, initializes fields and the RemoveButton
    public PopUpEditor(Equipment equip, EquipmentManagerGUI manager) {
        LabInventory inventory = manager.getInventory();
        newExpense = new JTextField(20);
        expenseButton = new JButton("Add Expense");
        newStatus = new JTextField(20);
        statusButton = new JButton("Update Status");
        newUser = new JTextField(20);
        userButton = new JButton("Add User");
        this.equip = equip;
        removeButton = new JButton("REMOVE EQUIPMENT");
        createPopUp();
        assignListeners();
        removeButton.addActionListener(new ActionListener() {
            //EFFECTS: removes selected equipment from the inventory and closes window
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.removeEquipment(equip);
                manager.displayMainMenu();
                dispose();
                manager.displayMainMenu();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds the relevant actionListener to each button
    private void assignListeners() {
        AddExpenseListener addExpenseListener = new AddExpenseListener();
        newExpense.addActionListener(addExpenseListener);
        expenseButton.addActionListener(addExpenseListener);
        UpdateStatusListener updateStatusListener = new UpdateStatusListener();
        newStatus.addActionListener(updateStatusListener);
        statusButton.addActionListener(updateStatusListener);
        AddUserListener addUserListener = new AddUserListener();
        newUser.addActionListener(addUserListener);
        userButton.addActionListener(addUserListener);
    }

    //MODIFIES: this
    //EFFECTS: initializes the pop-up's JFrame, assigns relevant JComponents, and displays the frame
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

    //Represents the actionListener for the newExpenseButton
    private class AddExpenseListener implements ActionListener {

        //REQUIRES: newExpense.getText() is not empty, and contains a String that can be parsed into an Integer
        //MODIFIES: this
        //EFFECTS: adds the expense to the selected equipment
        @Override
        public void actionPerformed(ActionEvent e) {
            int expense = Integer.parseInt(newExpense.getText());
            equip.addCost(expense);
            newExpense.requestFocusInWindow();
            newExpense.setText("");
        }
    }

    //Represents the actionListener for the newStatusButton
    private class UpdateStatusListener implements ActionListener {

        //REQUIRES: newStatus.getText() is not empty, and contains a String
        //MODIFIES: this
        //EFFECTS: updates the status of the selected equipment
        @Override
        public void actionPerformed(ActionEvent e) {
            String status = newStatus.getText();
            equip.setStatus(status);
            newStatus.requestFocusInWindow();
            newStatus.setText("");
        }
    }

    //Represents the actionListener for the newUserButton
    private class AddUserListener implements ActionListener {

        //REQUIRES: newUser.getText() is not empty, and contains a String
        //MODIFIES: this
        //EFFECTS: adds the user to the selected equipment's user history
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = newUser.getText();
            equip.addUser(user);
            newUser.requestFocusInWindow();
            newUser.setText("");
        }
    }


}
