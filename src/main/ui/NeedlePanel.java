package ui;

import model.KnittingProject;
import model.Needles;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

// Shows needles in the project and lets users add or remove needles.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class NeedlePanel extends JPanel
        implements ListSelectionListener {
    private Needles needles;
    private DefaultListModel needleList;
    private JList needleJList;
    private JScrollPane needleScrollPane;
    private JTextField needleInput;
    private static final String addString = "Add New Needle";
    private static final String removeString = "Remove Needle";
    private JButton addButton;
    private AddNeedleListener addNeedleListener;

    private JButton removeButton;

    public Needles getNeedles() {
        return this.needles;
    }

    public DefaultListModel getNeedleList() {
        return this.needleList;
    }

    public JTextField getNeedleInput() {
        return this.needleInput;
    }

    //EFFECTS: constructs a NeedlePanel consisting of a list of needles, and add/remove capabilities.
    public NeedlePanel(KnittingProject kp) {
        super(new BorderLayout());

        initializeFields(kp);
        initializeNeedleList();

        add(needleScrollPane, BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(kp), BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: Returns an add needle button and adds an AddNeedleListener to it.
    //          Also adds listener to needleInput.
    public JButton makeAddButtonAndText(KnittingProject kp) {
        addButton = new JButton(addString);
        addNeedleListener = new AddNeedleListener(addButton, this, kp);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addNeedleListener);
        addButton.setEnabled(false);

        needleInput.addActionListener(addNeedleListener);
        needleInput.getDocument().addDocumentListener(addNeedleListener);

        return addButton;
    }

    // MODIFIES: this
    // EFFECTS: Returns a remove needle button and adds an RemoveNeedleListener to it.
    public void makeRemoveButton(KnittingProject kp) {
        removeButton = new JButton(removeString);
        RemoveNeedleListener removeNeedleListener = new RemoveNeedleListener(removeButton,
                this,
                kp,
                needleJList);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeNeedleListener);

    }

    // EFFECTS: Returns all buttons (add and remove) and textfields in one JPanel.
    public JPanel initializeButtonsAndTextFields(KnittingProject kp) {
        JButton addButton = makeAddButtonAndText(kp);
        makeRemoveButton(kp);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(addButton);
        buttonPane.add(needleInput);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    // MODIFIES: this
    // EFFECTS: initializes the needles with the given project's needles. Creates a new text field.
    public void initializeFields(KnittingProject project) {
        needles = project.getNeedles();
        needleInput = new JTextField(10);
    }

    // MODIFIES: this
    // EFFECTS: resets the needles, needleList with the given project's information.
    //          resets the addButton and needleInput action listeners so it will modify kp.
    public void resetNeedleFields(KnittingProject kp) {
        this.needles = kp.getNeedles();

        needleList.removeAllElements();
        for (String n : needles.getSupplies()) {
            needleList.addElement(n);
        }

        addButton.removeActionListener(addNeedleListener);
        needleInput.removeActionListener(addNeedleListener);
        needleInput.getDocument().removeDocumentListener(addNeedleListener);

        addNeedleListener = new AddNeedleListener(addButton, this, kp);

        addButton.addActionListener(addNeedleListener);
        needleInput.addActionListener(addNeedleListener);
        needleInput.getDocument().addDocumentListener(addNeedleListener);

    }

    // MODIFIES: this
    // EFFECTS: Initializes needleList and adds needles into it form needles.
    //          Initializes the needleJList and puts it in a scroll pane.
    public void initializeNeedleList() {
        //Create the list and put it in a scroll pane.
        needleList = new DefaultListModel();
        needleList.addElement("test needle");

        for (String n : needles.getSupplies()) {
            needleList.addElement(n);
        }

        needleJList = new JList(needleList);
        needleJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        needleJList.setSelectedIndex(0);
        needleJList.addListSelectionListener(this);
        needleJList.setVisibleRowCount(3);

        needleScrollPane = new JScrollPane(needleJList);
    }

    // REQUIRES: click on needle.
    // MODIFIES: this
    // EFFECTS: Clicking a needle will enable the remove button.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        removeButton.setEnabled(true);
    }
}
