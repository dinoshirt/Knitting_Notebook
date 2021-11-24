package ui;

import model.KnittingProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Removes yarn.
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

class RemoveNeedleListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private KnittingProject currentProject;
    private DefaultListModel needleNameList;
    private JList needleJList;

    // EFFECTS: creates the RemoveNeedleListener
    public RemoveNeedleListener(JButton button, NeedlePanel needlePanel, KnittingProject kp, JList needleJList) {
        this.button = button;
        this.currentProject = kp;
        this.needleNameList = needlePanel.getNeedleList();
        this.needleJList = needleJList;
    }

    // MODIFIES: needlePanel, currentProject
    // EFFECTS: finds out which needle was selected, and removes it.
    public void actionPerformed(ActionEvent e) {

        String selectedNeedle = (String) needleJList.getSelectedValue();
        currentProject.getNeedles().removeSupply(selectedNeedle, currentProject);


        int index = needleJList.getSelectedIndex();
        needleNameList.remove(index);

        int size = needleNameList.getSize();

        if (size == 0) { //Nobody's left, disable firing.
            button.setEnabled(false);

        } else { //Select an index.
            if (index == needleNameList.getSize()) {
                //removed item in last position
                index--;
            }

            needleJList.setSelectedIndex(index);
            needleJList.ensureIndexIsVisible(index);
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
