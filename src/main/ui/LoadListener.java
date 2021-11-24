package ui;

import model.AllKnittingProjects;
import model.KnittingProject;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// This class loads previously saved notebook information (projects and info).
class LoadListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private AllKnittingProjects currentProjects;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/notebook.json";
    private DefaultListModel projectNameList;
    private ProjectPanel projectPanel;

    // EFFECTS: creates the LoadListener
    public LoadListener(JButton button, ProjectPanel projectPanel) {
        this.button = button;
        this.currentProjects = projectPanel.getCurrentProjects();
        jsonReader = new JsonReader(JSON_STORE);
        this.projectNameList = projectPanel.getProjectNameList();
        this.projectPanel = projectPanel;

    }

    // MODIFIES: projectPanel
    // EFFECTS: reads the previously saved notebook state, and loads the projects into projectPanel after resetting it.
    public void actionPerformed(ActionEvent e) {
        try {
            currentProjects = jsonReader.read();
            projectPanel.resetCurrentProjects(currentProjects);

            for (KnittingProject kp: currentProjects.getAllKnittingProjects()) {
                if (!projectNameList.contains(kp.getProjectName())) {
                    projectNameList.addElement(kp.getProjectName());
                }

            }

            System.out.println("Loaded " + projectPanel.getCurrentProjects().listProjectNames() + " from " + JSON_STORE);
        } catch (IOException a) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
