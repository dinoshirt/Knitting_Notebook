package model;

import java.util.ArrayList;
import java.util.List;

public class AllKnittingProjects {
    private List<KnittingProject> allKnittingProjects;

    public AllKnittingProjects() {
        this.allKnittingProjects = new ArrayList<>();
    }

    //EFFECTS: list all knitting projects
    public List<KnittingProject> getAllKnittingProjects() {
        return this.allKnittingProjects;
    }

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

    //MODIFIES: this
    //EFFECTS: adds the given knitting project to the list of all knitting projects
    public void addKnittingProject(KnittingProject projectToAdd) {
        allKnittingProjects.add(projectToAdd);
    }

    //MODIFIES: this
    //EFFECTS: deletes the project that matches the given name. if no such project exists, do nothing.
    public void deleteKnittingProject(String name) {
        KnittingProject currentProject;
        for (int i = 0; i < allKnittingProjects.size(); i++) {
            currentProject = allKnittingProjects.get(i);
            if (currentProject.getProjectName() == name) {
                allKnittingProjects.remove(currentProject);
            }
        }
    }

    //SPECIFIES: allKnittingProjects should not be empty
    //EFFECTS: gets the project that matches the given name.
    // if no such project exists, return a dummy knitting project.
    public KnittingProject getKnittingProject(String name) {
        KnittingProject currentProject;
        KnittingProject projectOfInterest = new KnittingProject("dummy");
        for (int i = 0; i < allKnittingProjects.size(); i++) {
            currentProject = allKnittingProjects.get(i);
            if (currentProject.getProjectName() == name) {
                projectOfInterest = currentProject;
            }
        }
        return projectOfInterest;
    }

    //MODIFIES: this
    //EFFECTS: orders all projects alphabetically
    public void orderAlphabetically() {}
}
