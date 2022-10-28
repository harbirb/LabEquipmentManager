package persistence;

import model.Equipment;
import model.LabInventory;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            //LabInventory li = new LabInventory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass;
        }
    }

    @Test
    void testWriterEmptyLabInventory() {
        try {
            LabInventory li = new LabInventory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLabInventory.json");
            writer.open();
            writer.write(li);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLabInventory.json");
            li = reader.read();
            assertEquals(0, li.getLabInventory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralWorkroom() {
        try {
            LabInventory li = new LabInventory();
            Equipment massSpec = new Equipment("Mass Spectrometer", "offline", 20000);
            massSpec.addUser("Kyle");
            Equipment centrifuge = new Equipment("Centrifuge", "online", 3000);
            centrifuge.addUser("James");
            centrifuge.addUser("Lilly");
            li.addEquipment(massSpec);
            li.addEquipment(centrifuge);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLabInventory.json");
            writer.open();
            writer.write(li);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLabInventory.json");
            li = reader.read();

            List<Equipment> equipmentList = li.getLabInventory();
            assertEquals(2, equipmentList.size());
            List<String> userHistory1 = new ArrayList<>();
            userHistory1.add("Kyle");
            checkEquipment("Mass Spectrometer", "offline", userHistory1, 20000, equipmentList.get(0));
            List<String> userHistory2 = new ArrayList<>(Arrays.asList("James", "Lilly"));
            checkEquipment("Centrifuge", "online", userHistory2, 3000, equipmentList.get(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}