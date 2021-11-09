package ui;

import model.AllKnittingProjects;
import model.KnittingProject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

// A GUI that shows all projects in a list, and enables actions on the projects
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class ListGui extends JPanel
        implements ListSelectionListener {
    protected JList list;
    protected AllKnittingProjects currentProjects;

    private static final String addString = "Add New Project";
    private static final String saveString = "Save Notebook";
    private static final String loadString = "Load Notebook";
    private JTextField projectName;

    private static final String JSON_STORE = "./data/notebook.json";

    private DefaultListModel projectNameList;

    public JTextField getProjectName() {
        return projectName;
    }

    public AllKnittingProjects getCurrentProjects() {
        return currentProjects;
    }

    public DefaultListModel getProjectNameList() {
        return projectNameList;
    }

    public void initializeFields() {
        projectName = new JTextField(10);
        currentProjects = new AllKnittingProjects();
        currentProjects.addKnittingProject(new KnittingProject("test project"));

    }

    public JScrollPane initializeList() {
        //Create the list and put it in a scroll pane.
        projectNameList = new DefaultListModel();
        projectNameList.addElement("test project");
        list = new JList(projectNameList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    public JButton makeAddButtonAndText() {
        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton, this);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        projectName.addActionListener(addListener);
        projectName.getDocument().addDocumentListener(addListener);

        return addButton;
    }

    public JButton makeSaveButton() {
        JButton saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener(saveButton, this);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);
        return saveButton;
    }

    public JButton makeLoadButton() {
        JButton saveButton = new JButton(loadString);
        //AddListener addListener = new AddListener(addButton);
        saveButton.setActionCommand(loadString);
        //addButton.addActionListener(addListener);
        saveButton.setEnabled(true);
        return saveButton;
    }

    public JPanel initializeButtonsAndTextFields() {
        JButton addButton = makeAddButtonAndText();
        JButton saveButton = makeSaveButton();
        JButton loadButton = makeLoadButton();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(projectName);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    public ListGui() {
        super(new BorderLayout());
        initializeFields();
        initializeList();

        add(initializeList(), BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(), BorderLayout.PAGE_END);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                //addButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                //addButton.setEnabled(true);
            }
        }
    }



}
