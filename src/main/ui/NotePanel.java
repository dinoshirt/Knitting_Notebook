package ui;

import model.KnittingProject;
import model.Note;
import model.Notes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class NotePanel extends JPanel
        implements ListSelectionListener {
    private Notes notes;
    private DefaultListModel notesList;
    private JList noteJList;
    private JScrollPane noteScrollPane;
    private JTextField noteInput;
    private static final String addString = "Add New Note";


    public Notes getNotes() {
        return this.notes;
    }

    public DefaultListModel getNotesList() {
        return this.notesList;
    }

    public JTextField getNoteInput() {
        return this.noteInput;
    }


    public NotePanel(KnittingProject kp) {
        super(new BorderLayout());

        initializeFields(kp);
        initializeYarnList();

        add(noteScrollPane, BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(kp), BorderLayout.PAGE_END);
    }

    public JButton makeAddButtonAndText(KnittingProject kp) {
        JButton addButton = new JButton(addString);
        AddNoteListener addNoteListener = new AddNoteListener(addButton, this, kp);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addNoteListener);
        addButton.setEnabled(false);

        noteInput.addActionListener(addNoteListener);
        noteInput.getDocument().addDocumentListener(addNoteListener);

        return addButton;
    }


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

    public void initializeFields(KnittingProject project) {
        notes = project.getNotes();
        noteInput = new JTextField(10);
    }

    public void initializeYarnList() {
        //Create the list and put it in a scroll pane.
        notesList = new DefaultListModel();
        Note testNote = new Note();
        testNote.addToBody("test note");
        notesList.addElement(testNote.getDateTimeAndBody());

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
