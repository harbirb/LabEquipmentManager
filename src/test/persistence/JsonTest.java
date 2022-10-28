package persistence;

import model.Equipment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEquipment(String name, String status,
                                  List<String> userHistory, int runningCost, Equipment equipment) {
        assertEquals(name, equipment.getName());
        assertEquals(status, equipment.getStatus());
        assertEquals(userHistory, equipment.getUsers());
        assertEquals(runningCost, equipment.getRunningCost());

    }
}
