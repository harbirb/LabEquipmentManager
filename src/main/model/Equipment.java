package model;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private String name;            // name of the equipment
    private String status;          // current status of the equipment
    private List<String> userHistory;  // list of all past users of the equipment (duplicates allowed)
    private int runningCost;        // cumulative amount spent on the equipment in CAD


    //REQUIRES: name has a non-zero length, status has a non-zero length, upfrontCost > 0
    //EFFECTS: constructs equipment with a name, a status, upfront cost in CAD, and an empty list of user history

    public Equipment(String name, String status, int upfrontCost) {
        this.name = name;
        this.status = status;
        this.runningCost = upfrontCost;
        this.userHistory = new ArrayList<>();
    }

    //REQUIRES: status has a non-zero length
    //MODIFIES: this
    //EFFECTS: updates the operational status of the equipment to the given newStatus
    public void setStatus(String newStatus) {
        this.status = newStatus;
    }
    //REQUIRES: user has a non-zero length
    //MODIFIES: this
    //EFFECTS: adds a user to the list of past users, duplicate entries allowed

    public void addUser(String user) {
        userHistory.add(user);
    }
    //REQUIRES: cost > 0
    //MODIFIES: this
    //EFFECTS: adds the expense amount in CAD, to the running cost of the equipment

    public void addCost(int expense) {
        runningCost += expense;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getUsers() {
        return userHistory;
    }

    public int getRunningCost() {
        return runningCost;
    }
}
