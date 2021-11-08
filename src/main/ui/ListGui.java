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
    private JTextField projectName;

    private static final String JSON_STORE = "./data/notebook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DefaultListModel listModel;

    public void initializeFields() {
        currentProjects = new AllKnittingProjects();
        currentProjects.addKnittingProject(new KnittingProject("test project"));
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public JScrollPane initializeList() {
        //Create the list and put it in a scroll pane.
        listModel = new DefaultListModel();
        listModel.addElement("test project");
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    public JPanel initializeButtons() {
        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        projectName = new JTextField(10);
        projectName.addActionListener(addListener);
        projectName.getDocument().addDocumentListener(addListener);


        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(projectName);
        //buttonPane.add(hireButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    public ListGui() {
        super(new BorderLayout());
        initializeFields();
        initializeList();

        add(initializeList(), BorderLayout.CENTER);
        add(initializeButtons(), BorderLayout.PAGE_END);
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

    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = projectName.getText();
            KnittingProject addedProject = new KnittingProject(name);
            currentProjects.addKnittingProject(addedProject);
            listModel.addElement(name);

            //int index = currentProjects.getAllKnittingProjects().size() - 1;

            //Reset the text field.
            projectName.requestFocusInWindow();
            projectName.setText("");

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

}
