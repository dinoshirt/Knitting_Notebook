package ui;

import model.KnittingProject;
import model.Note;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener is shared by the text field and the hire button.
class AddNoteListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField noteInput;
    private KnittingProject currentProject;
    private DefaultListModel notesList;

    public AddNoteListener(JButton button, NotePanel notePanel, KnittingProject kp) {
        this.button = button;
        this.noteInput = notePanel.getNoteInput();
        this.currentProject = kp;
        this.notesList = notePanel.getNotesList();
    }

    //Required by ActionListener.
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

    @Override
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    //Required by DocumentListener.
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }

    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }
}
