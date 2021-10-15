package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotesTest {

    private Notes testNotes;

    @BeforeEach
    public void runBefore() {
        testNotes = new Notes();
    }


    @Test
    public void addOneNote() {
        Note note1 = new Note();
        note1.addToBody("new note 1");
        assertEquals(0, testNotes.getNotes().size());

        testNotes.addNote(note1);
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

        assertEquals(firstNote.getDateAndBody() + ";", gottenNotes);
    }

    @Test
    public void showTwoNotes() {
        String gottenNotes;
        addOneNote();
        Note note2 = new Note();
        note2.addToBody("new note 2");
        testNotes.addNote(note2);

        gottenNotes = testNotes.showAllNotes();
        Note firstNote;
        Note secondNote;
        firstNote = testNotes.getNotes().get(0);
        secondNote = testNotes.getNotes().get(1);

        assertEquals(firstNote.getDateAndBody() + ";" + secondNote.getDateAndBody() + ";", gottenNotes);
    }

    @Test
    public void addManyNotes() {
        for(int i = 0; i < 10; i++) {
            testNotes.addNote(new Note());
        }
        assertEquals(10, testNotes.getNotes().size());
        Note note11 = new Note();
        note11.addToBody("eleventh note");
        testNotes.addNote(note11);

        for(int i = 0; i < 9; i++) {
            testNotes.addNote(new Note());
        }
        assertEquals(20, testNotes.getNotes().size());
        assertEquals(note11, testNotes.getNotes().get(10));

    }




}