package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener is shared by the text field and the hire button.
class AddNeedleListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private JTextField needleInput;
    private KnittingProject currentProject;
    private DefaultListModel needleNameList;

    public AddNeedleListener(JButton button, NeedlePanel needlePanel, KnittingProject kp) {
        this.button = button;
        this.needleInput = needlePanel.getNeedleInput();
        this.currentProject = kp;
        this.needleNameList = needlePanel.getNeedleList();
    }

    //Required by ActionListener.
    public void actionPerformed(ActionEvent e) {
        String name = needleInput.getText();
        //KnittingProject addedProject = new KnittingProject(name);
        currentProject.getNeedles().add(name);
        needleNameList.addElement(name);

        //int index = currentProjects.getAllKnittingProjects().size() - 1;

        //Reset the text field.
        needleInput.requestFocusInWindow();
        needleInput.setText("");

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
