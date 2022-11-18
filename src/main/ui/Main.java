package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new EquipmentManagerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run the application: file not found");
        }
    }
}
