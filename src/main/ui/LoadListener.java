package ui;

import model.AllKnittingProjects;
import model.KnittingProject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//This listener is shared by the text field and the hire button.
class LoadListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private AllKnittingProjects currentProjects;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/notebook.json";
    private DefaultListModel projectNameList;
    private ListGui listGui;

    public LoadListener(JButton button, ListGui listGui) {
        this.button = button;
        this.currentProjects = listGui.getCurrentProjects();
        jsonReader = new JsonReader(JSON_STORE);
        this.projectNameList = listGui.getProjectNameList();
        this.listGui = listGui;

    }

    //Required by ActionListener.
    public void actionPerformed(ActionEvent e) {
        try {
            currentProjects = jsonReader.read();
            for (KnittingProject kp: currentProjects.getAllKnittingProjects()) {
                if (!projectNameList.contains(kp.getProjectName())) {
                    projectNameList.addElement(kp.getProjectName());
                }

            }

            System.out.println("Loaded " + currentProjects.listProjectNames() + " from " + JSON_STORE);
        } catch (IOException a) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
