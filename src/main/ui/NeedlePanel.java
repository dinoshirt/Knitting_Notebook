package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class NeedlePanel extends JPanel
        implements ListSelectionListener {
    private List<String> needles;
    private DefaultListModel needleList;
    private JList needleJList;
    private JScrollPane needleScrollPane;
    private JTextField needleInput;
    private static final String addString = "Add New Needle";
    private static final String removeString = "Remove Needle";

    private JButton removeButton;

    public List<String> getNeedles() {
        return this.needles;
    }

    public DefaultListModel getNeedleList() {
        return this.needleList;
    }

    public JTextField getNeedleInput() {
        return this.needleInput;
    }


    public NeedlePanel(KnittingProject kp) {
        super(new BorderLayout());

        initializeFields(kp);
        initializeNeedleList();

        add(needleScrollPane, BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(kp), BorderLayout.PAGE_END);
    }

    public JButton makeAddButtonAndText(KnittingProject kp) {
        JButton addButton = new JButton(addString);
        AddNeedleListener addNeedleListener = new AddNeedleListener(addButton, this, kp);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addNeedleListener);
        addButton.setEnabled(false);

        needleInput.addActionListener(addNeedleListener);
        needleInput.getDocument().addDocumentListener(addNeedleListener);

        return addButton;
    }

    public void makeRemoveButton(KnittingProject kp) {
        removeButton = new JButton(removeString);
        RemoveNeedleListener removeNeedleListener = new RemoveNeedleListener(removeButton,
                this,
                kp,
                needleJList);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeNeedleListener);

    }

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

    public void initializeFields(KnittingProject project) {
        needles = project.getNeedles();
        needleInput = new JTextField(10);
    }

    public void initializeNeedleList() {
        //Create the list and put it in a scroll pane.
        needleList = new DefaultListModel();
        needleList.addElement("test needle");

        needleJList = new JList(needleList);
        needleJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        needleJList.setSelectedIndex(0);
        needleJList.addListSelectionListener(this);
        needleJList.setVisibleRowCount(3);

        needleScrollPane = new JScrollPane(needleJList);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        removeButton.setEnabled(true);
    }
}
