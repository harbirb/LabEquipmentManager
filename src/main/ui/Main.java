package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

//Represents the main class, to launch the EquipmentManagerApp
public class Main {

    //EFFECTS: creates an instance of EquipmentManagerApp
    public static void main(String[] args) {
        try {
            new EquipmentManagerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run the application: file not found");
        }
    }
}
