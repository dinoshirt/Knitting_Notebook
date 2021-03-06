package ui;

import model.AllKnittingProjects;
import model.KnittingProject;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
// This class describes the console ui that the user can use on knitting projects
// This class references code from here: CPSC210/TellerApp and CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class NotebookApp {
    private static final String JSON_STORE = "./data/notebook.json";
    private AllKnittingProjects allCurrentKnittingProjects;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

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
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays main menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create new knitting project");
        System.out.println("\tv -> view all knitting projects");
        System.out.println("\te -> edit an existing knitting project");
        System.out.println("\ts -> save projects to file");
        System.out.println("\tl -> load projects from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command from main menu
    private void processCommand(String command) {
        if (command.equals("c")) {
            createKnittingProject();
        } else if (command.equals("v")) {
            viewAllKnittingProjects();
        } else if (command.equals("e")) {
            editKnittingProject();
        } else if (command.equals("s")) {
            saveNotebook();
        } else if (command.equals("l")) {
            loadNotebook();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves all projects in the notebook to file
    private void saveNotebook() {
        try {
            jsonWriter.open();
            jsonWriter.write(allCurrentKnittingProjects);
            jsonWriter.close();
            System.out.println("Saved " + allCurrentKnittingProjects.listProjectNames() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads notebook (and all projects within it) from file
    private void loadNotebook() {
        try {
            allCurrentKnittingProjects = jsonReader.read();
            System.out.println("Loaded " + allCurrentKnittingProjects.listProjectNames() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new named knitting project and adds it to all current knitting projects
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
            if (fieldChosen.equals("yarn")) {
                removeYarn(projectChosen);
            } else if (fieldChosen.equals("needle")) {
                removeNeedle(projectChosen);
            } else if (fieldChosen.equals("note")) {
                System.out.println("can't remove notes right now, sorry! Will implement later.");
            }
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this, projectChosen
    //EFFECTS: removes a yarn from the project if user inputs an existing yarn. If not, then will not remove anything.
    private void removeYarn(KnittingProject projectChosen) {
        int startingSupplies;
        int postRemovalSupplies;
        System.out.println("Please input the name of the item you would like to remove: ");
        String itemName = input.next();

        startingSupplies = projectChosen.getYarns().getSupplies().size();
        projectChosen.getYarns().removeSupply(itemName, projectChosen);
        postRemovalSupplies = projectChosen.getYarns().getSupplies().size();

        System.out.println(projectChosen.getYarns());
        if (startingSupplies == postRemovalSupplies) {
            System.out.println(itemName + " was NOT removed!");
        } else {
            System.out.println(itemName + " was removed!");
        }

    }

    //MODIFIES: this, projectChosen
    //EFFECTS: removes a needle from the project if needle is present in project. If not, doesn't remove anything.
    private void removeNeedle(KnittingProject projectChosen) {
        int startingSupplies;
        int postRemovalSupplies;
        System.out.println("Please input the name of the item you would like to remove: ");
        String itemName = input.next();

        startingSupplies = projectChosen.getNeedles().getSupplies().size();
        projectChosen.getNeedles().removeSupply(itemName, projectChosen);
        postRemovalSupplies = projectChosen.getNeedles().getSupplies().size();

        System.out.println(projectChosen.getNeedles());
        if (startingSupplies == postRemovalSupplies) {
            System.out.println(itemName + " was NOT removed!");
        } else {
            System.out.println(itemName + " was removed!");
        }

    }

    //MODIFIES: this, projectChosen
    //EFFECTS: adds a new yarn, needle, or note to the project
    private void addNewItem(String fieldChosen, KnittingProject projectChosen) {
        System.out.println("Please input the name of the item you would like to add: ");
        String itemName = input.next();

        if (fieldChosen.equals("yarn")) {
            projectChosen.getYarns().addSupply(itemName, projectChosen);
            System.out.println(projectChosen.getYarns());
        } else if (fieldChosen.equals("needle")) {
            projectChosen.getNeedles().addSupply(itemName, projectChosen);
            System.out.println(projectChosen.getNeedles());
        } else if (fieldChosen.equals("note")) {
            Note createdNote = new Note();
            createdNote.addToBody(itemName);
            projectChosen.getNotes().addNote(createdNote, projectChosen);
            System.out.println(createdNote.getDateTimeAndBody());
        }
        System.out.println("new " + fieldChosen + " was added!");

    }

    //EFFECTS: display the current yarn, needles, and notes in the project
    private void displayProjectInfo(KnittingProject selectedProject) {
        System.out.print("\n yarn:" + selectedProject.getYarns());
        System.out.print("\n needle:" + selectedProject.getNeedles());
        System.out.print("\n note:" + selectedProject.getNotes().showAllNotes());
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
