package model;


import java.util.List;

//This class represents a knitting project. A project is identified by its project name.
// The project can have associated supplies (like yarns and needles), or notes.
public class KnittingProject {

    private String projectName;
    private Yarns yarns;
    private Needles needles;
    private Notes notes;

    //EFFECTS: Construct a new knitting project with the given name.
    //The project will start with no yarns, needles, or notes.
    public KnittingProject(String newProjectName) {
        this.projectName = newProjectName;
        this.yarns = new Yarns();
        this.needles = new Needles();
        this.notes = new Notes();
    }

    //EFFECTS: returns the names of the project
    public String getProjectName() {
        return this.projectName;
    }

    //EFFECTS: returns the names of all yarns
    public List<String> getYarns() {
        return this.yarns.getSupplies();
    }

    //EFFECTS: returns the names of all needles
    public List<String> getNeedles() {
        return this.needles.getSupplies();
    }

    //EFFECTS: returns all notes
    public Notes getNotes() {
        return this.notes;
    }



}
