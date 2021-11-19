package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class YarnPanel extends JPanel
        implements ListSelectionListener {
    private List<String> yarns;
    private DefaultListModel yarnList;
    private JList yarnJList;
    private JScrollPane yarnScrollPane;
    private JTextField yarnInput;
    private static final String addString = "Add New Yarn";
    private static final String removeString = "Remove Yarn";

    private JButton removeButton;

    public List<String> getYarns() {
        return this.yarns;
    }

    public DefaultListModel getYarnList() {
        return this.yarnList;
    }

    public JTextField getYarnInput() {
        return this.yarnInput;
    }


    public YarnPanel(KnittingProject kp) {
        super(new BorderLayout());

        initializeFields(kp);
        initializeYarnList();

        add(yarnScrollPane, BorderLayout.CENTER);
        add(initializeButtonsAndTextFields(kp), BorderLayout.PAGE_END);
    }

    public JButton makeAddButtonAndText(KnittingProject kp) {
        JButton addButton = new JButton(addString);
        AddYarnListener addYarnListener = new AddYarnListener(addButton, this, kp);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addYarnListener);
        addButton.setEnabled(false);

        yarnInput.addActionListener(addYarnListener);
        yarnInput.getDocument().addDocumentListener(addYarnListener);

        return addButton;
    }

    public void makeRemoveButton(KnittingProject kp) {
        removeButton = new JButton(removeString);
        RemoveYarnListener removeYarnListener = new RemoveYarnListener(removeButton, this, kp, yarnJList);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeYarnListener);

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
        buttonPane.add(yarnInput);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    public void initializeFields(KnittingProject project) {
        yarns = project.getYarns();
        yarnInput = new JTextField(10);
    }

    public void initializeYarnList() {
        //Create the list and put it in a scroll pane.
        yarnList = new DefaultListModel();
        yarnList.addElement("test yarn");

        yarnJList = new JList(yarnList);
        yarnJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        yarnJList.setSelectedIndex(0);
        yarnJList.addListSelectionListener(this);
        yarnJList.setVisibleRowCount(3);

        yarnScrollPane = new JScrollPane(yarnJList);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        removeButton.setEnabled(true);
    }
}
