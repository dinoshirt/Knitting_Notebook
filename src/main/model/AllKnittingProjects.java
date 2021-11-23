package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//This class stores all knitting projects in a list.
public class AllKnittingProjects implements Writable {
    private List<KnittingProject> allKnittingProjects;

    //EFFECTS: Constructs a new empty list of all knitting projects
    public AllKnittingProjects() {
        this.allKnittingProjects = new ArrayList<>();
    }

    //EFFECTS: get all knitting projects
    public List<KnittingProject> getAllKnittingProjects() {
        return this.allKnittingProjects;
    }

    //MODIFIES: this
    //EFFECTS: adds the given knitting project to the list of all knitting projects
    public void addKnittingProject(KnittingProject projectToAdd) {
        allKnittingProjects.add(projectToAdd);
        EventLog.getInstance().logEvent(new Event("Added to Notebook: " + projectToAdd.getProjectName()));
    }

//    //MODIFIES: this
//    //EFFECTS: deletes the given knitting project from the list of all knitting projects
//    public void deleteKnittingProject(KnittingProject projectToDelete) {
//        allKnittingProjects.remove(projectToDelete);
//    }
//

    //MODIFIES: this
    //EFFECTS: deletes the project that matches the given name. if no such project exists, do nothing.
    //public void deleteKnittingProject(String name) {}

    //EFFECTS: returns the names of all knitting projects
    public List<String> listProjectNames() {
        List<String> allProjectNames = new ArrayList<>();
        KnittingProject currentProject;

        for (int i = 0; i < allKnittingProjects.size(); i++) {
            currentProject = allKnittingProjects.get(i);
            allProjectNames.add(currentProject.getProjectName());
        }
        return allProjectNames;
    }

    //REQUIRES: allKnittingProjects should not be empty
    //EFFECTS: gets the project that matches the given name.
    // if no such project exists, return a dummy knitting project.
    public KnittingProject getKnittingProject(String name) {
        KnittingProject currentProject;
        KnittingProject dummyProject = new KnittingProject("dummy");
        dummyProject.getYarns().addSupply("dummy yarn");
        dummyProject.getNeedles().addSupply("dummy needles");
        for (int i = 0; i < this.allKnittingProjects.size(); i++) {
            currentProject = this.allKnittingProjects.get(i);
            if (currentProject.getProjectName().equals(name)) {
                return currentProject;
            }
        }
        return dummyProject;
    }

    //EFFECTS: returns the notebook (contianing all projects) as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("all projects", allKnittingProjectsToJson());
        return json;
    }

    // EFFECTS: returns all knitting projects in this notebook as a JSON array
    private JSONArray allKnittingProjectsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (KnittingProject kp : allKnittingProjects) {
            jsonArray.put(kp.toJson());
        }

        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: orders all projects alphabetically
    //public void orderAlphabetically() {}
}
