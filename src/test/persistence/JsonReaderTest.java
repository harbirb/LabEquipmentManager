package persistence;

import model.Equipment;
import model.LabInventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Represents the JSON reader test class
class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LabInventory li = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass;
        }
    }

    @Test
    void testReaderEmptyLabInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLabInventory.json");
        try {
            LabInventory li = reader.read();
            assertEquals(0, li.getLabInventory().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderGeneralLabInventory() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLabInventory.json");
        try {
            LabInventory li = reader.read();
            List<Equipment> equipmentList = li.getLabInventory();
            assertEquals(2, equipmentList.size());
            List<String> userHistory1 = new ArrayList<>();
            userHistory1.addAll(Arrays.asList("James", "Lilly"));
            checkEquipment("Centrifuge", "online", userHistory1, 3000, equipmentList.get(0));
            List<String> userHistory2 = new ArrayList<>();
            userHistory2.add("Kyle");
            checkEquipment("Mass Spectrometer", "offline", userHistory2, 20000, equipmentList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}