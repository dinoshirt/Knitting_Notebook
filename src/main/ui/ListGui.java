package ui;

import model.AllKnittingProjects;
import model.KnittingProject;

import java.awt.*;
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

    private DefaultListModel projectNameList;

    private JPanel projectPage;

    private JPanel panelRight;
    private JPanel panelMiddle;
    private YarnPanel panelLeft;

    public JTextField getProjectName() {
        return projectName;
    }

    public JPanel getProjectPage() {
        return this.projectPage;
    }

    public JList getProjectJList() {
        return this.list;
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

        KnittingProject testProject = new KnittingProject("test project");
        currentProjects.addKnittingProject(testProject);
        projectPage = new JPanel();

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
        AddProjectListener addProjectListener = new AddProjectListener(addButton, this);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addProjectListener);
        addButton.setEnabled(false);

        projectName.addActionListener(addProjectListener);
        projectName.getDocument().addDocumentListener(addProjectListener);

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
        JButton loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener(loadButton, this);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);
        loadButton.setEnabled(true);
        return loadButton;
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

    public void projectPanel() {

    }


    public ListGui() {
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

    public void resetCurrentProjects(AllKnittingProjects loadedProjects) {
        this.currentProjects = loadedProjects;
    }

    public void valueChanged(ListSelectionEvent e) {
//        JList list = (JList)e.getSource();
//        int index = list.getSelectedIndex();
//        KnittingProject indexedProject = currentProjects.getAllKnittingProjects().get(index);
        String nameOfSelectedProject = (String) list.getSelectedValue();
        System.out.println(currentProjects.listProjectNames());
        KnittingProject indexedProject = currentProjects.getKnittingProject(nameOfSelectedProject);
        //System.out.println(indexedProject.getProjectName());

        panelRight.removeAll();
//        panelRight.revalidate();
//        panelRight.repaint();
        panelLeft.resetYarnFields(indexedProject);
//        panelLeft.revalidate();
//        panelLeft.repaint();
        panelMiddle.removeAll();
//        panelMiddle.revalidate();
//        panelMiddle.repaint();


//        panelRight = new NotePanel(indexedProject);
//        panelMiddle = new NeedlePanel(indexedProject);
//        panelLeft = new YarnPanel(indexedProject);

        //String nameOfSelectedProject = (String) list.getSelectedValue();

        //KnittingProject selectedProject = currentProjects.getKnittingProject(nameOfSelectedProject);

        //ListSelectionModel lsm = (ListSelectionModel) list.getSelectionModel();


        //String nameOfSelectedProject = (String) list.getSelectedValue();

        //KnittingProject selectedProject = currentProjects.getKnittingProject(nameOfSelectedProject);
        //System.out.println(nameOfSelectedProject);
        //projectPage = new NotebookPage(selectedProject);
        //System.out.println(selectedProject.getNeedles());
    }


}
