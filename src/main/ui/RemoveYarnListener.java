package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Removes yarn.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

class RemoveYarnListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private KnittingProject currentProject;
    private DefaultListModel yarnNameList;
    private JList yarnJList;

    // EFFECTS: creates the RemoveYarnListener
    public RemoveYarnListener(JButton button, YarnPanel yarnPanel, KnittingProject kp, JList yarnJList) {
        this.button = button;
        this.currentProject = kp;
        this.yarnNameList = yarnPanel.getYarnList();
        this.yarnJList = yarnJList;
    }

    // MODIFIES: yarnPanel, currentProject
    // EFFECTS: finds out which yarn was selected, and removes it.
    public void actionPerformed(ActionEvent e) {

        String selectedYarn = (String) yarnJList.getSelectedValue();
        currentProject.getYarns().removeSupply(selectedYarn, currentProject);


        int index = yarnJList.getSelectedIndex();
        yarnNameList.remove(index);

        int size = yarnNameList.getSize();

        if (size == 0) { //Nobody's left, disable firing.
            button.setEnabled(false);

        } else { //Select an index.
            if (index == yarnNameList.getSize()) {
                //removed item in last position
                index--;
            }

            yarnJList.setSelectedIndex(index);
            yarnJList.ensureIndexIsVisible(index);
        }
    }


//    // MODIFIES: button
//    // EFFECTS: enables button
//    private void enableButton() {
//        if (!alreadyEnabled) {
//            button.setEnabled(true);
//        }
//    }


}
