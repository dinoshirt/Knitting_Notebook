package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// This class references code from here: CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// This class references a library from here: stleary/JSON-java
// Link: https://github.com/stleary/JSON-java
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads notebook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AllKnittingProjects read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAllKnittingProjects(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses all knitting projects from JSON object and returns it
    private AllKnittingProjects parseAllKnittingProjects(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        AllKnittingProjects akp = new AllKnittingProjects();
        addKnittingProjects(akp, jsonObject);
        return akp;
    }

    // MODIFIES: all knitting projects
    // EFFECTS: parses knitting projects from JSON object and adds them to all knitting projects
    private void addKnittingProjects(AllKnittingProjects akp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("all projects");
        for (Object json : jsonArray) {
            JSONObject nextKnittingProject = (JSONObject) json;
            addKnittingProject(akp, nextKnittingProject);
        }
    }

    // MODIFIES: akp
    // EFFECTS: parses knitting project from JSON object and adds it to all knitting projects
    private void addKnittingProject(AllKnittingProjects akp, JSONObject jsonObject) {
        String name = jsonObject.getString("project name");

        KnittingProject kp = new KnittingProject(name);
        parseYarnsAndAdd(kp, jsonObject);
        parseNeedlesAndAdd(kp, jsonObject);
        parseNotesAndAdd(kp, jsonObject);
        akp.addKnittingProject(kp);
    }

    // MODIFIES: kp
    // EFFECTS: parses yarns from JSON object and adds it to yarns in kp
    private void parseYarnsAndAdd(KnittingProject kp, JSONObject jsonObject) {
        for (int i = 0; i < jsonObject.getJSONArray("yarns").length(); i++) {
            String yarn = jsonObject.getJSONArray("yarns").getString(i);
            kp.getYarns().addSupply(yarn);
        }
    }

    // MODIFIES: kp
    // EFFECTS: parses needles from JSON object and adds it to needles in kp
    private void parseNeedlesAndAdd(KnittingProject kp, JSONObject jsonObject) {
        for (int i = 0; i < jsonObject.getJSONArray("needles").length(); i++) {
            String needle = jsonObject.getJSONArray("needles").getString(i);
            kp.getNeedles().addSupply(needle);
        }
    }

    // MODIFIES: kp
    // EFFECTS: gets notes from JSON object and adds it to the notes in kp
    private void parseNotesAndAdd(KnittingProject kp, JSONObject jsonObject) {
        for (int i = 0; i < jsonObject.getJSONArray("notes").length(); i++) {
            String stringDate = jsonObject.getJSONArray("notes").getJSONObject(i).getString("date and time");
            LocalDateTime gottenDateAndTime = LocalDateTime.parse(stringDate);
            String body = jsonObject.getJSONArray("notes").getJSONObject(i).getString("body");

            Note newNote = new Note(gottenDateAndTime, body);
            kp.getNotes().addNote(newNote);

        }
    }
}
