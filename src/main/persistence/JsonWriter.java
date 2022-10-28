package persistence;

import model.LabInventory;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(LabInventory li) {
        JSONObject json = li.toJson();
        saveToFile(json.toString(TAB));
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

    public void close() {
        writer.close();
    }
}
