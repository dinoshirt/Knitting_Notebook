package ui;

import model.KnittingProject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener is shared by the text field and the hire button.
class RemoveYarnListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private KnittingProject currentProject;
    private DefaultListModel yarnNameList;
    private JList yarnJList;

    public RemoveYarnListener(JButton button, YarnPanel yarnPanel, KnittingProject kp, JList yarnJList) {
        this.button = button;
        this.currentProject = kp;
        this.yarnNameList = yarnPanel.getYarnList();
        this.yarnJList = yarnJList;
    }

    //Required by ActionListener.
    public void actionPerformed(ActionEvent e) {

        String selectedYarn = (String) yarnJList.getSelectedValue();
        currentProject.getYarns().remove(selectedYarn);


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


    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }


}
