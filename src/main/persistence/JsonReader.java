package persistence;


import model.Equipment;
import model.LabInventory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public LabInventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLabInventory(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private LabInventory parseLabInventory(JSONObject jsonObject) {
        LabInventory li = new LabInventory();
        addEquipments(li, jsonObject);
        return li;
    }

    private void addEquipments(LabInventory li, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("labInventory");
        for (Object json : jsonArray) {
            JSONObject nextEquipment = (JSONObject) json;
            addEquipment(li, nextEquipment);
        }
    }

    private void addEquipment(LabInventory li, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String status = jsonObject.getString("status");
        JSONArray userHistory = jsonObject.getJSONArray("userHistory");
        int runningCost = jsonObject.getInt("runningCost");
        Equipment equipment = new Equipment(name, status, runningCost);
        for (Object user: userHistory) {
            equipment.addUser((String) user);
        }
        li.addEquipment(equipment);
    }

}
