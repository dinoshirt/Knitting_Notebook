package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Adds yarn.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

class AddYarnListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField yarnInput;
    private KnittingProject currentProject;
    private DefaultListModel yarnNameList;

    //EFFECTS: creates an AddYarnListener
    public AddYarnListener(JButton button, YarnPanel yarnPanel, KnittingProject kp) {
        this.button = button;
        this.yarnInput = yarnPanel.getYarnInput();
        this.currentProject = kp;
        this.yarnNameList = yarnPanel.getYarnList();
    }

    // MODIFIES: currentProject, yarnPanel
    // EFFECTS: adds user input to the currentProject's list of yarns. Also adds it to yarnNameList.
    public void actionPerformed(ActionEvent e) {
        String name = yarnInput.getText();
        currentProject.getYarns().addSupply(name);
        yarnNameList.addElement(name);

        //Reset the text field.
        yarnInput.requestFocusInWindow();
        yarnInput.setText("");

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
