package persistence;

import model.AllKnittingProjects;
import model.KnittingProject;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from here: CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AllKnittingProjects akp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyNotebook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyNotebook.json");
        try {
            AllKnittingProjects akp = reader.read();
            assertEquals(0, akp.getAllKnittingProjects().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralNotebookFirstProject() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralNotebook.json");
        try {
            AllKnittingProjects akp = reader.read();
            assertEquals(2, akp.getAllKnittingProjects().size());
            KnittingProject correctProject = new KnittingProject("Proj1");
            correctProject.getYarns().add("blue");
            correctProject.getNeedles().add("10mm");

            checkKnittingProject("Proj1",
                    correctProject.getYarns(),
                    correctProject.getNeedles(),
                    akp.getAllKnittingProjects().get(0));

            checkParticularNote(akp.getAllKnittingProjects().get(0),
                    0,
                    parse("2021-10-10T10:10:10.100"),
                    "Today I did this.");


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralNotebookSecondProject() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralNotebook.json");
        try {
            AllKnittingProjects akp = reader.read();
            assertEquals(2, akp.getAllKnittingProjects().size());
            KnittingProject correctProject = new KnittingProject("Proj2");
            correctProject.getYarns().add("blue");
            correctProject.getYarns().add("yellow");
            correctProject.getNeedles().add("10mm");
            correctProject.getNeedles().add("11mm");

            checkKnittingProject("Proj2",
                    correctProject.getYarns(),
                    correctProject.getNeedles(),
                    akp.getAllKnittingProjects().get(1));

            checkParticularNote(akp.getAllKnittingProjects().get(1),
                    1,
                    parse("2021-10-23T15:07:46.316"),
                    "Then today I did this.");


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}