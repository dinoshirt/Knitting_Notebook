package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notes {
    private ArrayList<Note> listOfNotes;

    public Notes() {
        listOfNotes = new ArrayList<>();
    }

    //EFFECTS: show all note date and body together as a string. Notes are separated by ";"
    public String showAllNotes() {
        Note currentNote;
        String allNotes = "";

        for (int i = 0; i < listOfNotes.size(); i++) {
            currentNote = listOfNotes.get(i);

            allNotes = allNotes + currentNote.getDateAndBody() + ';';
        }
        return allNotes;
    }

    //EFFECTS: gets all notes
    public ArrayList<Note> getNotes() {
        return listOfNotes;
    }

    //MODIFIES: this
    //EFFECTS: adds a note to the back of the list
    public void addNote(Note n) {
        listOfNotes.add(n);
    }


    //MODIFIES: this
    //EFFECTS: delete a note written on a given date. If no note was written on that date, do nothing.
    public void deleteNote(Date d) {
        Note currentNote;
        for (int i = 0; i < listOfNotes.size(); i++) {
            currentNote = listOfNotes.get(i);
            if (currentNote.getDate() == d) {
                listOfNotes.remove(currentNote);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: orders the notes from most recently added to oldest.
    public void orderNewestFirst() {}

    //MODIFIES: this
    //EFFECTS: orders the notes from oldest to newest.
    public void orderOldestFirst() {}



}
