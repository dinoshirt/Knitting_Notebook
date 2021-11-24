package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.KnittingSupplies;

import static org.junit.jupiter.api.Assertions.*;

class KnittingProjectTest {

    private KnittingProject newKnittingProject;

    @BeforeEach
    public void runBefore() {
        String projectName = "First Project";
        newKnittingProject = new KnittingProject(projectName);
    }

    @Test
    public void nameInitiated() {
        assertEquals("First Project", newKnittingProject.getProjectName());
        assertEquals(0, newKnittingProject.getYarns().getSupplies().size());
        assertEquals(0, newKnittingProject.getNeedles().getSupplies().size());
        assertEquals(0 , newKnittingProject.getNotes().getNotes().size());

    }
    @Test
    public void getOneNoteFromProject() {
        Note oneNote = new Note();
        oneNote.replaceBody("this is one note");
        newKnittingProject.getNotes().addNote(oneNote, newKnittingProject);
        assertEquals(1 , newKnittingProject.getNotes().getNotes().size());
        assertEquals(oneNote, newKnittingProject.getNotes().getNotes().get(0));

    }

//    @Test
//    public void testGet() {
//        newKnittingProject.getYarns().addSupply("YARN", newKnittingProject);
//        newKnittingProject.getYarns().addSupply("NN", newKnittingProject);
//        System.out.println(newKnittingProject.getYarns().getSupplies());
//    }

    @Test
    public JSONObject toJSONOneNote() {
        getOneNoteFromProject();

        return newKnittingProject.toJson();
    }

}