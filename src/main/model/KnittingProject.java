package model;


import java.util.ArrayList;
import java.util.List;

public class KnittingProject {
    // delete or rename this class!

    private String projectName;
    private List<String> yarnNames;
    private List<String> needleNames;
    private List<Inspiration> inspiration;
    private List<Swatch> swatch;
    private Notes notes;


    public KnittingProject(String newProjectName) {
        this.projectName = newProjectName;
        this.yarnNames = new ArrayList<>();
        this.needleNames = new ArrayList<>();
        this.inspiration = new ArrayList<>();
        this.swatch = new ArrayList<>();
        this.notes = new Notes();
    }

    //EFFECTS: returns the names of the project
    public String getProjectName() {
        return this.projectName;
    }

    //EFFECTS: returns the names of all yarns
    public List<String> getYarnName() {
        return this.yarnNames;
    }

    //EFFECTS: returns the names of all needles
    public List<String> getNeedleNames() {
        return this.needleNames;
    }

    //EFFECTS: returns all notes
    public Notes getNotes() {
        return this.notes;
    }

    //SPECIFIES: itemType must be "Yarn" or "Needle"
    //MODIFIES: this
    //EFFECTS: adds a new yarn or new needle to the appropriate field.
    public void addItem(String newItemName, String itemType) {
        if (itemType == "Yarn") {
            this.yarnNames.add(newItemName);
        } else if (itemType == "Needle") {
            this.needleNames.add(newItemName);
        }
    }







}
