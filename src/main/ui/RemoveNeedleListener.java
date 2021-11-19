package ui;

import model.KnittingProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This listener is shared by the text field and the hire button.
class RemoveNeedleListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private KnittingProject currentProject;
    private DefaultListModel needleNameList;
    private JList needleJList;

    public RemoveNeedleListener(JButton button, NeedlePanel needlePanel, KnittingProject kp, JList needleJList) {
        this.button = button;
        this.currentProject = kp;
        this.needleNameList = needlePanel.getNeedleList();
        this.needleJList = needleJList;
    }

    //Required by ActionListener.
    public void actionPerformed(ActionEvent e) {

        String selectedNeedle = (String) needleJList.getSelectedValue();
        currentProject.getNeedles().remove(selectedNeedle);


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


    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }


}
