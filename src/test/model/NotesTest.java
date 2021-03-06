package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotesTest {

    private Notes testNotes;
    private KnittingProject testProject;

    @BeforeEach
    public void runBefore() {
        testNotes = new Notes(testProject);
        testProject = new KnittingProject("TEST");
    }


    @Test
    public void addOneNote() {
        Note note1 = new Note();
        note1.addToBody("new note 1");
        assertEquals(0, testNotes.getNotes().size());

        testNotes.addNote(note1, testProject);
        assertEquals(1, testNotes.getNotes().size());
        assertEquals(note1, testNotes.getNotes().get(0));
    }



    @Test
    public void showEmptyNotes() {
        String gottenNotes;
        gottenNotes = testNotes.showAllNotes();
        assertEquals("", gottenNotes);
    }


    @Test
    public void showOneNote() {
        String gottenNotes;
        addOneNote();
        gottenNotes = testNotes.showAllNotes();
        Note firstNote;
        firstNote = testNotes.getNotes().get(0);

        assertEquals(firstNote.getDateTimeAndBody() + ";", gottenNotes);
    }

    @Test
    public void showTwoNotes() {
        String gottenNotes;
        addOneNote();
        Note note2 = new Note();
        note2.addToBody("new note 2");
        testNotes.addNote(note2, testProject);

        gottenNotes = testNotes.showAllNotes();
        Note firstNote;
        Note secondNote;
        firstNote = testNotes.getNotes().get(0);
        secondNote = testNotes.getNotes().get(1);

        assertEquals(firstNote.getDateTimeAndBody() + ";" + secondNote.getDateTimeAndBody() + ";", gottenNotes);
    }

    @Test
    public void addManyNotes() {
        for(int i = 0; i < 10; i++) {
            testNotes.addNote(new Note(), testProject);
        }
        assertEquals(10, testNotes.getNotes().size());
        Note note11 = new Note();
        note11.addToBody("eleventh note");
        testNotes.addNote(note11, testProject);

        for(int i = 0; i < 9; i++) {
            testNotes.addNote(new Note(), testProject);
        }
        assertEquals(20, testNotes.getNotes().size());
        assertEquals(note11, testNotes.getNotes().get(10));

    }




}