package ui;

import model.AllKnittingProjects;
import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Adds project.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

class AddProjectListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField projectName;
    private AllKnittingProjects currentProjects;
    private DefaultListModel projectNameList;

    //EFFECTS: creates and AddProjectListener
    public AddProjectListener(JButton button, ProjectPanel projectPanel) {
        this.button = button;
        this.projectName = projectPanel.getProjectName();
        this.currentProjects = projectPanel.getCurrentProjects();
        this.projectNameList = projectPanel.getProjectNameList();
    }

    // MODIFIES: currentProject, projectPanel
    // EFFECTS: adds user input to the currentProjects list of projects. Also adds it to projectNameList.
    public void actionPerformed(ActionEvent e) {
        String name = projectName.getText();
        KnittingProject addedProject = new KnittingProject(name);
        currentProjects.addKnittingProject(addedProject);
        projectNameList.addElement(name);

        //int index = currentProjects.getAllKnittingProjects().size() - 1;

        //Reset the text field.
        projectName.requestFocusInWindow();
        projectName.setText("");

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
