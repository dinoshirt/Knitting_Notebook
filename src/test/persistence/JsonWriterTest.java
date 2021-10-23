package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static java.time.LocalDateTime.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            AllKnittingProjects akp = new AllKnittingProjects();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            AllKnittingProjects akp = new AllKnittingProjects();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyNotebook.json");
            writer.open();
            writer.write(akp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyNotebook.json");
            akp = reader.read();
            assertEquals(0, akp.getAllKnittingProjects().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            AllKnittingProjects akp = new AllKnittingProjects();
            KnittingProject kp = new KnittingProject("My first project");
            Note newNote = new Note(parse("2021-10-23T15:07:46.316"), "Event 1");
            kp.getYarns().add("worsted");
            kp.getNeedles().add("metal");
            kp.getNotes().addNote(newNote);
            akp.addKnittingProject(kp);

            JsonWriter writer = new JsonWriter("./data/testWriterOneProjectNotebook.json");
            writer.open();
            writer.write(akp);
            writer.close();

            AllKnittingProjects readAkp = new AllKnittingProjects();
            JsonReader reader = new JsonReader("./data/testWriterOneProjectNotebook.json");
            readAkp = reader.read();
            assertEquals(1, readAkp.getAllKnittingProjects().size());
            checkKnittingProject("My first project",
                    kp.getYarns(),
                    kp.getNeedles(),
                    readAkp.getAllKnittingProjects().get(0));

            checkParticularNote(readAkp.getAllKnittingProjects().get(0),
                    0,
                    parse("2021-10-23T15:07:46.316"),
                    "Event 1");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}