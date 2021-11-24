package ui;

import model.AllKnittingProjects;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// This class saves the entire notebook state.
class SaveListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    private AllKnittingProjects currentProjects;
    private JsonWriter jsonWriter;
    private ProjectPanel projectPanel;
    private static final String JSON_STORE = "./data/notebook.json";

    // EFFECTS: Creates a SaveListener
    public SaveListener(JButton button, ProjectPanel projectPanel) {
        this.button = button;

        this.projectPanel = projectPanel;
        jsonWriter = new JsonWriter(JSON_STORE);
        //jsonReader = new JsonReader(JSON_STORE);

    }

    // MODIFIES: projectPanel
    // EFFECTS: Saves the notebook information by writing it to json.
    public void actionPerformed(ActionEvent e) {
        try {
            this.currentProjects = projectPanel.getCurrentProjects();
            jsonWriter.open();
            jsonWriter.write(currentProjects);
            jsonWriter.close();
            System.out.println("Saved " + currentProjects.listProjectNames() + " to " + JSON_STORE);
        } catch (FileNotFoundException a) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }
}
