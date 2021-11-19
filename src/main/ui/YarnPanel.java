package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

// Shows yarns in the project and lets users add or remove yarns.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

public class YarnPanel extends JPanel
        implements ListSelectionListener {
    private List<String> yarns;
    private DefaultListModel yarnList;
    private JList yarnJList;
    private JScrollPane yarnScrollPane;
    private JTextField yarnInput;
    private JButton addButton;
    private static final String addString = "Add New Yarn";
    private static final String removeString = "Remove Yarn";

    private JButton removeButton;
    private AddYarnListener addYarnListener;

    public List<String> getYarns() {
        return this.yarns;
    }

    public DefaultListModel getYarnList() {
        return this.yarnList;
    }

    public JTextField getYarnInput() {
        return this.yarnInput;
    }

    //EFFECTS: constructs a YarnPanel consisting of a list of yarns, and add/remove capabilities.
    public YarnPanel(KnittingProject kp) {
        super(new BorderLayout());

        initializeFields(kp);
        initializeYarnList();

        add(yarnScrollPane, BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(kp), BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: resets the yarns, yarnList with the given project's information.
    //          resets the addButton and yarnInput action listeners so it will modify kp.
    public void resetYarnFields(KnittingProject kp) {
        this.yarns = kp.getYarns();

        yarnList.removeAllElements();
        for (String y : yarns) {
            yarnList.addElement(y);
        }

        addButton.removeActionListener(addYarnListener);
        yarnInput.removeActionListener(addYarnListener);
        yarnInput.getDocument().removeDocumentListener(addYarnListener);

        addYarnListener = new AddYarnListener(addButton, this, kp);

        addButton.addActionListener(addYarnListener);
        yarnInput.addActionListener(addYarnListener);
        yarnInput.getDocument().addDocumentListener(addYarnListener);

    }

    // MODIFIES: this
    // EFFECTS: Returns an add yarn button and adds an AddYarnListener to it.
    //          Also adds listener to yarnInput.
    public JButton makeAddButtonAndText(KnittingProject kp) {
        addButton = new JButton(addString);
        addYarnListener = new AddYarnListener(addButton, this, kp);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addYarnListener);
        addButton.setEnabled(false);

        yarnInput.addActionListener(addYarnListener);
        yarnInput.getDocument().addDocumentListener(addYarnListener);

        return addButton;
    }

    // MODIFIES: this
    // EFFECTS: Returns a remove yarn button and adds an RemoveYarnListner to it.
    public void makeRemoveButton(KnittingProject kp) {
        removeButton = new JButton(removeString);
        RemoveYarnListener removeYarnListener = new RemoveYarnListener(removeButton, this, kp, yarnJList);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeYarnListener);

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
        buttonPane.add(yarnInput);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    // MODIFIES: this
    // EFFECTS: initializes the yarns with the given project's yarns. Creates a new text field.
    public void initializeFields(KnittingProject project) {
        yarns = project.getYarns();
        yarnInput = new JTextField(10);
    }

    // MODIFIES: this
    // EFFECTS: Initializes yarnList and adds yarns into it form yarns.
    //          Initializes the yarnJList and puts it in a scroll pane.
    public void initializeYarnList() {
        //Create the list and put it in a scroll pane.
        yarnList = new DefaultListModel();
        //yarnList.addElement("test yarn");

        for (String y : yarns) {
            yarnList.addElement(y);
        }

        yarnJList = new JList(yarnList);
        yarnJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        yarnJList.setSelectedIndex(0);
        yarnJList.addListSelectionListener(this);
        yarnJList.setVisibleRowCount(3);

        yarnScrollPane = new JScrollPane(yarnJList);
    }


    // REQUIRES: click on yarn.
    // MODIFIES: this
    // EFFECTS: Clicking a yarn will enable the remove button.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        removeButton.setEnabled(true);
    }
}
