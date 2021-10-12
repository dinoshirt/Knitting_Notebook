package ui;

import model.AllKnittingProjects;
import model.KnittingProject;

import java.util.Scanner;
//Code copied from TellerApp

public class NotebookApp {
    private KnittingProject knittingProject;
    private AllKnittingProjects allKnittingProjects;
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
    // EFFECTS: initializes accounts
    private void init() {
        allKnittingProjects = new AllKnittingProjects();
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
        allKnittingProjects.addKnittingProject(newKnittingProject);

        System.out.println("The new knitting project named " + projectName + " was created");

    }

    // EFFECTS: displays all knitting projects
    private void viewAllKnittingProjects() {
        System.out.print("Here is a list of all knitting projects: ");

        System.out.print(allKnittingProjects.listProjectNames());

    }

    // EFFECTS: displays fields that can be modified by user
    private void displayFieldOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\tyarn -> edit yarns");
        System.out.println("\tneedle -> edit needles");
        System.out.println("\tnotes -> edit notes");
    }

    // MODIFIES: this
    // EFFECTS: creates a new named knitting project
    private void editKnittingProject() {
        System.out.print("Please select from the following projects the project you would like to edit: ");
        System.out.print(allKnittingProjects.listProjectNames() + " ");

        String projectName = input.next();
        KnittingProject selectedProject;

        selectedProject = allKnittingProjects.getKnittingProject("abc");

        System.out.print(selectedProject.getProjectName());
        System.out.println(" has been selected. This project has the following information: ");

        System.out.print("\n yarn:" + selectedProject.getYarnName());
        System.out.print("\n needles:" + selectedProject.getNeedleNames());
        System.out.print("\n notes:" + selectedProject.getNotes().showAllNotes());

        System.out.print("\n what field would you like to edit?");
        displayFieldOptions();

        String fieldName = input.next();


    }
}
