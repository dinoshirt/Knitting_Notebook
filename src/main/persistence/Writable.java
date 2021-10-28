package persistence;

import org.json.JSONObject;

// Write objects into JSON objects
// This class references code from here: CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This class references a library from here: stleary/JSON-java
// Link: https://github.com/stleary/JSON-java
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
