package ui;

import model.AllKnittingProjects;
import model.KnittingProject;
import model.*;

import java.util.Scanner;
//Code adapted from TellerApp

public class NotebookApp {
    private AllKnittingProjects allCurrentKnittingProjects;
    private Scanner input;

    // EFFECTS: runs the notebook application
    public NotebookApp() {
        runNotebook();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runNotebook() {
        boolean keepGoing = true;
        String command = null;

        init();
        System.out.println("\nYou've opened the notebook!");
        while (keepGoing) {
            displayMenu();
            command = input.next();
            //command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes allKnittingProjects
    private void init() {
        allCurrentKnittingProjects = new AllKnittingProjects();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create new knitting project");
        System.out.println("\tv -> view all knitting projects");
        System.out.println("\te -> edit an existing knitting project");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createKnittingProject();
        } else if (command.equals("v")) {
            viewAllKnittingProjects();
        } else if (command.equals("e")) {
            editKnittingProject();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new named knitting project
    private void createKnittingProject() {
        System.out.print("Enter new project name: ");
        String projectName = input.next();

        KnittingProject newKnittingProject = new KnittingProject(projectName);
        allCurrentKnittingProjects.addKnittingProject(newKnittingProject);

        System.out.println("The new knitting project named " + projectName + " was created");

    }

    // EFFECTS: displays all knitting projects
    private void viewAllKnittingProjects() {
        System.out.print("Here is a list of all knitting projects: ");

        System.out.print(allCurrentKnittingProjects.listProjectNames());

    }

    // EFFECTS: displays fields that can be modified by user
    private void displayFieldOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\tyarn -> edit yarns");
        System.out.println("\tneedle -> edit needles");
        System.out.println("\tnote -> edit notes");
    }

    // EFFECTS: displays actions that the user can perform on the selected field
    private void displayEditingOptions(String fieldSelected) {
        if (fieldSelected.equals("yarn") || fieldSelected.equals("needle") || fieldSelected.equals("note")) {
            System.out.println("\nSelect from:");
            System.out.println("\ta -> add new " + fieldSelected);
            System.out.println("\tr -> remove " + fieldSelected);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command about editing a field
    private void processEditCommand(String command, String fieldChosen, KnittingProject projectChosen) {
        if (command.equals("a")) {
            addNewItem(fieldChosen, projectChosen);
        } else if (command.equals("r")) {
            removeItem(fieldChosen, projectChosen);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: projectChosen when yarn or needle is fieldChosen
    //EFFECTS: removes a yarn or needle in the project. Will tell user that notes cannot be removed.
    private void removeItem(String fieldChosen, KnittingProject projectChosen) {
        System.out.println("Please input the name of the item you would like to remove: ");
        String itemName = input.next();

        if (fieldChosen.equals("yarn")) {
            projectChosen.getYarns().remove(itemName);
            System.out.println(projectChosen.getYarns());
            System.out.println(itemName + " was removed!");
        } else if (fieldChosen.equals("needle")) {
            projectChosen.getNeedles().remove(itemName);
            System.out.println(projectChosen.getNeedles());
            System.out.println(itemName + " was removed!");
        }
        if (fieldChosen.equals("note")) {
            System.out.println("can't remove notes right now, sorry! Will implement later.");
        }
    }

    //MODIFIES: projectChosen
    //EFFECTS: adds a new yarn, needle, or note to the project
    private void addNewItem(String fieldChosen, KnittingProject projectChosen) {
        System.out.println("Please input the name of the item you would like to add: ");
        String itemName = input.next();

        if (fieldChosen.equals("yarn")) {
            projectChosen.getYarns().add(itemName);
            System.out.println(projectChosen.getYarns());
        } else if (fieldChosen.equals("needle")) {
            projectChosen.getNeedles().add(itemName);
            System.out.println(projectChosen.getNeedles());
        } else if (fieldChosen.equals("note")) {
            Note createdNote = new Note();
            createdNote.addToBody(itemName);
            projectChosen.getNotes().addNote(createdNote);
            System.out.println(createdNote.getDateAndBody());
        }
        System.out.println(itemName + " was added!");

    }

    //EFFECTS: display the current yarn, needles, and notes in the project
    private void displayProjectInfo(KnittingProject selectedProject) {
        System.out.print("\n yarn:" + selectedProject.getYarns());
        System.out.print("\n needles:" + selectedProject.getNeedles());
        System.out.print("\n notes:" + selectedProject.getNotes().showAllNotes());
    }

    // MODIFIES: this
    // EFFECTS: edits a selected KnittingProject
    private void editKnittingProject() {
        System.out.print("\n Please select from the following projects the project you would like to edit: ");
        System.out.print(allCurrentKnittingProjects.listProjectNames() + " ");
        System.out.print("\n If you try to select a project that doesn't exist, a dummy project will be returned. ");


        String projectName = input.next();
        KnittingProject selectedProject;

        selectedProject = allCurrentKnittingProjects.getKnittingProject(projectName);

        System.out.print(selectedProject.getProjectName());
        System.out.println(" has been selected. This project has the following information: ");
        displayProjectInfo(selectedProject);

        System.out.print("\n what field would you like to edit?");
        displayFieldOptions();

        String fieldName = input.next();

        System.out.print("\n You've chosen " + fieldName + ". What would you like to do?");
        displayEditingOptions(fieldName);

        String editAction = input.next();

        processEditCommand(editAction, fieldName, selectedProject);

    }
}
