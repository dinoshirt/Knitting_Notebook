package model;

import java.util.Date;

//This represents a note used to document progress on a knitting project.
// A note has the date created, and associated text that the user wants to write down.
public class Note {

    private final Date addDate;
    private String noteBody;

    //EFFECTS: Constructs a new empty Note. The date attached is the date the note is created.
    public Note() {
        this.addDate = new Date();
        this.noteBody = "";
    }

    //EFFECTS: Returns the date the note was created
    public Date getDate() {
        return this.addDate;
    }

    //EFFECTS: Returns the body of the note
    public String getBody() {
        return this.noteBody;
    }

    //EFFECTS: Returns the addDate, followed by the noteBody
    public String getDateAndBody() {
        return addDate + noteBody;
    }

    //MODIFIES: this
    //EFFECTS: Add text to the body of the note. If text already exists in the body, also add a space.
    public void addToBody(String additionalText) {
        if (this.getBody().equals("")) {
            this.noteBody = ": " + this.noteBody + additionalText;
        } else {
            this.noteBody = this.noteBody + " " + additionalText;
        }
    }

    //MODIFIES: this
    //EFFECTS: Replace the text in the notebody with new text
    public void replaceBody(String replacementText) {
        this.noteBody = replacementText;
    }

}
