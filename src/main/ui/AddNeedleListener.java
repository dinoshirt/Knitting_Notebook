package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Adds needle.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

class AddNeedleListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField needleInput;
    private KnittingProject currentProject;
    private DefaultListModel needleNameList;

    //EFFECTS: creates an AddNeedleListener
    public AddNeedleListener(JButton button, NeedlePanel needlePanel, KnittingProject kp) {
        this.button = button;
        this.needleInput = needlePanel.getNeedleInput();
        this.currentProject = kp;
        this.needleNameList = needlePanel.getNeedleList();
    }

    // MODIFIES: currentProject, needlePanel
    // EFFECTS: adds user input to the currentProject's list of needles. Also adds it to needleNameList.
    public void actionPerformed(ActionEvent e) {
        String name = needleInput.getText();
        //KnittingProject addedProject = new KnittingProject(name);
        currentProject.getNeedles().addSupply(name);
        needleNameList.addElement(name);

        //int index = currentProjects.getAllKnittingProjects().size() - 1;

        //Reset the text field.
        needleInput.requestFocusInWindow();
        needleInput.setText("");

        //Select the new item and make it visible.
        //list.setSelectedIndex(index);
        //list.ensureIndexIsVisible(index);
    }

    // MODIFIES: button
    // EFFECTS: enables the add button
    @Override
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    // Required by DocumentListener
    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    // MODIFIES: button
    // EFFECTS: enables button if text field is not empty
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    // MODIFIES: button
    // EFFECTS: enables button
    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: If text field is empty, disable the add button and return true. Otherwise return false.
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }
}
