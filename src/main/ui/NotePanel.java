package ui;

import model.KnittingProject;
import model.Note;
import model.Notes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// Shows notes in the project and lets users add notes.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class NotePanel extends JPanel
        implements ListSelectionListener {
    private Notes notes;
    private DefaultListModel notesList;
    private JList noteJList;
    private JScrollPane noteScrollPane;
    private JTextField noteInput;
    private static final String addString = "Add New Note";

    private JButton addButton;
    private AddNoteListener addNoteListener;

    public Notes getNotes() {
        return this.notes;
    }

    public DefaultListModel getNotesList() {
        return this.notesList;
    }

    public JTextField getNoteInput() {
        return this.noteInput;
    }

    //EFFECTS: constructs a NotePanel consisting of a list of notes, and add note capabilities.
    public NotePanel(KnittingProject kp) {
        super(new BorderLayout());

        initializeFields(kp);
        initializeNotesList();

        add(noteScrollPane, BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(kp), BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: Returns an add note button and adds an AddNoteListener to it.
    //          Also adds listener to noteInput.
    public JButton makeAddButtonAndText(KnittingProject kp) {
        addButton = new JButton(addString);
        addNoteListener = new AddNoteListener(addButton, this, kp);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addNoteListener);
        addButton.setEnabled(false);

        noteInput.addActionListener(addNoteListener);
        noteInput.getDocument().addDocumentListener(addNoteListener);

        return addButton;
    }

    // EFFECTS: Returns all buttons (add and remove) and textfields in one JPanel.
    public JPanel initializeButtonsAndTextFields(KnittingProject kp) {
        JButton addButton = makeAddButtonAndText(kp);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(addButton);
        buttonPane.add(noteInput);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    // MODIFIES: this
    // EFFECTS: initializes the notes with the given project's notes. Creates a new text field.
    public void initializeFields(KnittingProject project) {
        notes = project.getNotes();
        noteInput = new JTextField(10);
    }

    // MODIFIES: this
    // EFFECTS: resets the notes, noteList with the given project's information.
    //          resets the addButton and noteInput action listeners so it will modify kp.
    public void resetNotesFields(KnittingProject kp) {
        this.notes = kp.getNotes();

        notesList.removeAllElements();
        for (Note n : notes.getNotes()) {
            notesList.addElement(n.getDateTimeAndBody());
        }

        addButton.removeActionListener(addNoteListener);
        noteInput.removeActionListener(addNoteListener);
        noteInput.getDocument().removeDocumentListener(addNoteListener);

        addNoteListener = new AddNoteListener(addButton, this, kp);

        addButton.addActionListener(addNoteListener);
        noteInput.addActionListener(addNoteListener);
        noteInput.getDocument().addDocumentListener(addNoteListener);

    }

    // MODIFIES: this
    // EFFECTS: Initializes notesList and adds notes into it from the notes in notes.
    //          Notes are displayed as time stamp and note body combined together.
    //          Initializes the noteJList and puts it in a scroll pane.
    public void initializeNotesList() {
        //Create the list and put it in a scroll pane.
        notesList = new DefaultListModel();
//        Note testNote = new Note();
//        testNote.addToBody("test note");
//        notesList.addElement(testNote.getDateTimeAndBody());

        for (Note n : notes.getNotes()) {
            notesList.addElement(n.getDateTimeAndBody());
        }

        noteJList = new JList(notesList);
        noteJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        noteJList.setSelectedIndex(0);
        noteJList.addListSelectionListener(this);
        noteJList.setVisibleRowCount(3);

        noteScrollPane = new JScrollPane(noteJList);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
}
