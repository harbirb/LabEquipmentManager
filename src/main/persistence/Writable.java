package persistence;

import org.json.JSONObject;

// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents an object that can be returned as a JSON object.
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
