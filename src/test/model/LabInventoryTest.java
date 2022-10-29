package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Represents the LabInventory test class
class LabInventoryTest {
    private LabInventory inventory;
    Equipment e1 = new Equipment("Centrifuge", "online", 1200);
    Equipment e2 = new Equipment("Fume Hood", "offline", 1400);

    @BeforeEach
    void runBefore() {
        inventory = new LabInventory();
        e1.addUser("James");
        e1.addUser("Henry");
        e2.addUser("Lilly");

    }
    @Test
    void testConstructor() {
        assertEquals(0, inventory.getLabInventory().size());
    }

    @Test
    void testAddEquipment() {
        assertTrue(inventory.addEquipment(e1));
        assertEquals(1, inventory.getLabInventory().size());
        assertEquals(e1, inventory.getLabInventory().get(0));
    }
    @Test
    void testAddEquipmentMultipleTimes() {
        assertTrue(inventory.addEquipment(e1));
        assertTrue(inventory.addEquipment(e2));
        assertEquals(2, inventory.getLabInventory().size());
        assertEquals(e1, inventory.getLabInventory().get(0));
        assertEquals(e2, inventory.getLabInventory().get(1));
    }
    @Test
    void testAddEquipmentDuplicates() {
        assertTrue(inventory.addEquipment(e1));
        assertTrue(inventory.addEquipment(e2));
        assertFalse(inventory.addEquipment(e2));
        assertEquals(2, inventory.getLabInventory().size());
        assertEquals(e1, inventory.getLabInventory().get(0));
        assertEquals(e2, inventory.getLabInventory().get(1));
    }

    @Test
    void testRemoveEquipment() {
        inventory.addEquipment(e1);
        inventory.addEquipment(e2);
        assertEquals(2, inventory.getLabInventory().size());
        inventory.removeEquipment(e1);
        assertEquals(1, inventory.getLabInventory().size());
        assertFalse(inventory.getLabInventory().contains(e1));
        assertEquals(e2, inventory.getLabInventory().get(0));
    }
    @Test
    void testRemoveEquipmentMultipleTimes() {
        inventory.addEquipment(e1);
        inventory.addEquipment(e2);
        assertEquals(2, inventory.getLabInventory().size());
        inventory.removeEquipment(e1);
        inventory.removeEquipment(e2);
        assertEquals(0, inventory.getLabInventory().size());
    }

    @Test
    void testSearchEquipmentByName() {
        inventory.addEquipment(e1);
        inventory.addEquipment(e2);
        String e1Name = e1.getName();
        assertEquals(e1, inventory.searchEquipmentByName(e1Name));
    }
    @Test
    void testSearchEquipmentByNameNotInInventory() {
        inventory.addEquipment(e1);
        inventory.addEquipment(e2);
        String equipmentName = "HPLC";
        assertNull(inventory.searchEquipmentByName(equipmentName));
    }

    @Test
    void testGetEquipmentStatus() {
        inventory.addEquipment(e1);
        assertEquals((e1.getName() + " : " + e1.getStatus()), inventory.getEquipmentStatus().get(0));
    }

    @Test
    void testGetEquipmentStatusMultipleEquipment() {
        inventory.addEquipment(e1);
        inventory.addEquipment(e2);
        assertEquals(2, inventory.getEquipmentStatus().size());
        assertEquals((e1.getName() + " : " + e1.getStatus()), inventory.getEquipmentStatus().get(0));
        assertEquals((e2.getName() + " : " + e2.getStatus()), inventory.getEquipmentStatus().get(1));
    }

    @Test
    void testGetMostExpensiveEquipment() {
        inventory.addEquipment(e1);
        assertEquals(e1, inventory.getMostExpensiveEquipment());
    }

    @Test
    void testGetMostExpensiveEquipmentMultipleEquipment() {
        inventory.addEquipment(e1);
        inventory.addEquipment(e2);
        assertEquals(e2, inventory.getMostExpensiveEquipment());
        e1.addCost(1000);
        assertEquals(e1, inventory.getMostExpensiveEquipment());
    }

    @Test
    void testGetMostUsedEquipment() {
        inventory.addEquipment(e2);
        assertEquals(e2, inventory.getMostUsedEquipment());
    }

    @Test
    void testGetMostUsedEquipmentMultipleEquipment() {
        inventory.addEquipment(e1);
        inventory.addEquipment(e2);
        assertEquals(e1, inventory.getMostUsedEquipment());
    }
}