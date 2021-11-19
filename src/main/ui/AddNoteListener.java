package ui;

import model.KnittingProject;
import model.Note;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Adds note.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

class AddNoteListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField noteInput;
    private KnittingProject currentProject;
    private DefaultListModel notesList;

    //EFFECTS: creates an AddNoteListener
    public AddNoteListener(JButton button, NotePanel notePanel, KnittingProject kp) {
        this.button = button;
        this.noteInput = notePanel.getNoteInput();
        this.currentProject = kp;
        this.notesList = notePanel.getNotesList();
    }

    // MODIFIES: currentProject, needlePanel
    // EFFECTS: adds user input to the currentProject's list of notes.
    //          Also adds a combined timestamp with body string to notesList.
    public void actionPerformed(ActionEvent e) {
        String writtenBody = noteInput.getText();
        Note createdNote = new Note();
        createdNote.addToBody(writtenBody);
        currentProject.getNotes().addNote(createdNote);
        notesList.addElement(createdNote.getDateTimeAndBody());

        //int index = currentProjects.getAllKnittingProjects().size() - 1;

        //Reset the text field.
        noteInput.requestFocusInWindow();
        noteInput.setText("");

        //Select the new item and make it visible.
        //list.setSelectedIndex(index);
        //list.ensureIndexIsVisible(index);
    }

    // MODIFIES: button
    // EFFECTS: enables the add button
    @Override
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    // Required by DocumentListener
    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    // MODIFIES: button
    // EFFECTS: enables button if text field is not empty
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    // MODIFIES: button
    // EFFECTS: enables button
    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: If text field is empty, disable the add button and return true. Otherwise return false.
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }
}
