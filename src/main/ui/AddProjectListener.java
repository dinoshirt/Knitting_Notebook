package ui;

import model.AllKnittingProjects;
import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener is shared by the text field and the hire button.
class AddProjectListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField projectName;
    private AllKnittingProjects currentProjects;
    private DefaultListModel projectNameList;

    public AddProjectListener(JButton button, ListGui listGui) {
        this.button = button;
        this.projectName = listGui.getProjectName();
        this.currentProjects = listGui.getCurrentProjects();
        this.projectNameList = listGui.getProjectNameList();
    }

    //Required by ActionListener.
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
