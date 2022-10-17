package model;

import model.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EquipmentTest {
    private Equipment equipment;

    @BeforeEach
    void runBefore() {
        equipment = new Equipment("Centrifuge", "online", 1000);
    }

    @Test
    void testConstructor() {
        assertEquals("Centrifuge", equipment.getName());
        assertEquals("online", equipment.getStatus());
        assertEquals(1000, equipment.getRunningCost());
    }

    @Test
    void testSetStatusOnce() {
        equipment.setStatus("offline");
        assertEquals("offline", equipment.getStatus());
    }

    @Test
    private void testSetStatusMultipleTimes() {
        equipment.setStatus("out of order");
        equipment.setStatus("out for repair");
        assertEquals("out for repair", equipment.getStatus());
    }

    @Test
    void testAddUser() {
        equipment.addUser("James Smith");
        assertEquals(1, equipment.getUsers().size());
        assertTrue(equipment.getUsers().contains("James Smith"));
    }

    @Test
    void testAddUserMultipleUsers() {
        equipment.addUser("James Smith");
        equipment.addUser("Mary Jane");
        equipment.addUser("Harry Potter");
        assertEquals(3, equipment.getUsers().size());
        assertEquals("James Smith", equipment.getUsers().get(0));
        assertEquals("Mary Jane", equipment.getUsers().get(1));
        assertEquals("Harry Potter", equipment.getUsers().get(2));
    }

    private void testAddUserWithDuplicates() {
        equipment.addUser("James Smith");
        equipment.addUser("Mary Jane");
        equipment.addUser("James Smith");
        assertEquals(3, equipment.getUsers().size());
        assertEquals("James Smith", equipment.getUsers().get(0));
        assertEquals("Mary Jane", equipment.getUsers().get(1));
        assertEquals("James Smith", equipment.getUsers().get(2));
    }

    @Test
    void testAddCostOnce() {
        equipment.addCost(500);
        assertEquals(1500, equipment.getRunningCost());
    }

    @Test
    void testAddCostMultipleTimes() {
        equipment.addCost(500);
        equipment.addCost(100);
        equipment.addCost(200);
        assertEquals(1800, equipment.getRunningCost());
        equipment.addCost(200);
        assertEquals(2000, equipment.getRunningCost());
    }
}

