package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener is shared by the text field and the hire button.
class AddYarnListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField yarnInput;
    private KnittingProject currentProject;
    private DefaultListModel yarnNameList;

    public AddYarnListener(JButton button, YarnPanel yarnPanel, KnittingProject kp) {
        this.button = button;
        this.yarnInput = yarnPanel.getYarnInput();
        this.currentProject = kp;
        this.yarnNameList = yarnPanel.getYarnList();
    }

    //Required by ActionListener.
    public void actionPerformed(ActionEvent e) {
        String name = yarnInput.getText();
        //KnittingProject addedProject = new KnittingProject(name);
        currentProject.getYarns().add(name);
        yarnNameList.addElement(name);

        //int index = currentProjects.getAllKnittingProjects().size() - 1;

        //Reset the text field.
        yarnInput.requestFocusInWindow();
        yarnInput.setText("");

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
