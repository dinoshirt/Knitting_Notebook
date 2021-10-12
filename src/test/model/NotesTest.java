package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotesTest {

    private Notes testNotes;

    //TODO: make tests for delete and sort methods

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