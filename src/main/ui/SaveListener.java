package ui;

import model.AllKnittingProjects;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//This listener is shared by the text field and the hire button.
class SaveListener implements ActionListener {
    private boolean alreadyEnabled = false;
    private JButton button;
    //private JTextField projectName;
    private AllKnittingProjects currentProjects;
    //private DefaultListModel projectNameList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/notebook.json";

    public SaveListener(JButton button, ProjectPanel projectPanel) {
        this.button = button;
        this.currentProjects = projectPanel.getCurrentProjects();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    //Required by ActionListener.
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(currentProjects);
            jsonWriter.close();
            System.out.println("Saved " + currentProjects.listProjectNames() + " to " + JSON_STORE);
        } catch (FileNotFoundException a) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }
}
