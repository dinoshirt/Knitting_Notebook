package ui;

import model.AllKnittingProjects;
import model.KnittingProject;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

// Shows notebook projects and lets the user modify the projects in the notebook, save, and load.
// On project click, also updates what Yarn, Needle, and NotePanels will show.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class ProjectPanel extends JPanel
        implements ListSelectionListener {
    protected JList list;
    protected AllKnittingProjects currentProjects;

    private static final String addString = "Add New Project";
    private static final String saveString = "Save Notebook";
    private static final String loadString = "Load Notebook";
    private JTextField projectName;

    private DefaultListModel projectNameList;

    private JPanel projectPage;

    private NotePanel panelRight;
    private NeedlePanel panelMiddle;
    private YarnPanel panelLeft;

    public JTextField getProjectName() {
        return projectName;
    }

    public AllKnittingProjects getCurrentProjects() {
        return currentProjects;
    }

    public DefaultListModel getProjectNameList() {
        return projectNameList;
    }

    // MODIFIES: this
    // EFFECTS: initializes a anew JTextField, JPanel for projectPage, and AllKnittingProjects.
    //          Adds a test project into currentProjects.
    public void initializeFields() {
        projectName = new JTextField(10);
        currentProjects = new AllKnittingProjects();

        KnittingProject testProject = new KnittingProject("test project");
        currentProjects.addKnittingProject(testProject);
        projectPage = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: Initializes projectNameList as a new DefaultListModel, and
    //          initializes list as a new JList containing projectNameList and returns it in a scroll pane.
    //          Adds a test project to the DefaultListModel.
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

    // MODIFIES: this
    // EFFECTS: Returns an add project button and adds a AddProjectListener to it.
    //          also adds AddProjectListener to projectName.
    public JButton makeAddButtonAndText() {
        JButton addButton = new JButton(addString);
        AddProjectListener addProjectListener = new AddProjectListener(addButton, this);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addProjectListener);
        addButton.setEnabled(false);

        projectName.addActionListener(addProjectListener);
        projectName.getDocument().addDocumentListener(addProjectListener);

        return addButton;
    }

    // EFFECTS: Returns a save notebook button and adds a SaveListener to it.
    public JButton makeSaveButton() {
        JButton saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener(saveButton, this);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);
        return saveButton;
    }

    // EFFECTS: Returns a load notebook button and adds a LoadListener to it.
    public JButton makeLoadButton() {
        JButton loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener(loadButton, this);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);
        loadButton.setEnabled(true);
        return loadButton;
    }

    // EFFECTS: Combines and returns all buttons (add, save, load) and text fields into one JPanel.
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


    // EFFECTS: Constructs a project panel that shows a list of projects and has add, save, load capabilities.
    //          Initializes the bottom right, middle, and left panels of project info as the first project.
    public ProjectPanel() {
        super(new BorderLayout());

        initializeFields();
        //initializeList();
        KnittingProject firstProject = currentProjects.getAllKnittingProjects().get(0);
        panelRight = new NotePanel(firstProject);
        panelMiddle = new NeedlePanel(firstProject);
        panelLeft = new YarnPanel(firstProject);

        add(initializeList(), BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(), BorderLayout.PAGE_END);
    }

    public JPanel getPanelRight() {
        return this.panelRight;
    }

    public JPanel getPanelMiddle() {
        return this.panelMiddle;
    }

    public JPanel getPanelLeft() {
        return this.panelLeft;
    }

    // MODIFIES: this
    // EFFECTS: resets the currentProjects with the given loadedProjects
    public void resetCurrentProjects(AllKnittingProjects loadedProjects) {
        this.currentProjects = loadedProjects;
    }

    // REQUIRES: selecting a project by clicking on it
    // MODIFIES: this
    // EFFECTS: Upon selecting a project, the panels will be updated with the selected project info.
    public void valueChanged(ListSelectionEvent e) {

        String nameOfSelectedProject = (String) list.getSelectedValue();
        //System.out.println(currentProjects.listProjectNames());
        KnittingProject indexedProject = currentProjects.getKnittingProject(nameOfSelectedProject);

        panelRight.resetNotesFields(indexedProject);

        panelLeft.resetYarnFields(indexedProject);

        panelMiddle.resetNeedleFields(indexedProject);

    }


}
