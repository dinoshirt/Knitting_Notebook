package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

//This represents a list of notes, that may be associated with a knitting project.
public class Notes {
    private ArrayList<Note> listOfNotes;
    private KnittingProject knittingProject;

    //EFFECTS: constructs a new empty list of notes
    public Notes(KnittingProject kp) {
        listOfNotes = new ArrayList<>();
        this.knittingProject = kp;
    }

    //EFFECTS: show all note date and body together as a string. Notes are separated by ";"
    public String showAllNotes() {
        Note currentNote;
        String allNotes = "";

        for (int i = 0; i < listOfNotes.size(); i++) {
            currentNote = listOfNotes.get(i);

            allNotes = allNotes + currentNote.getDateTimeAndBody() + ';';
        }
        return allNotes;
    }

    //EFFECTS: gets all notes
    public ArrayList<Note> getNotes() {
        return listOfNotes;
    }

    //MODIFIES: this
    //EFFECTS: adds a note to the back of the list
    public void addNote(Note n, KnittingProject kp) {
        listOfNotes.add(n);
        EventLog.getInstance().logEvent(new Event("Added a note to project " + kp.getProjectName()));
    }

    // EFFECTS: converts each note into a JSONObject, and returns all notes as a JSONArray
    public JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();

        for (int i = 0; i < this.getNotes().size(); i++) {
            LocalDateTime noteDate = this.getNotes().get(i).getDateTime();
            String stringDate = noteDate.toString();
            json.put("date and time", stringDate);
            json.put("body", this.getNotes().get(i).getBody());

            jsonArray.put(json);
        }

        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: delete a note written on a given date. If no note was written on that date, do nothing.
    //public void deleteNote(Date d) {}

    //MODIFIES: this
    //EFFECTS: orders the notes from most recently added to oldest.
    //public void orderNewestFirst() {}

    //MODIFIES: this
    //EFFECTS: orders the notes from oldest to newest.
    //public void orderOldestFirst() {}



}
