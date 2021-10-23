package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteTest {

    private Note testNote;


    @BeforeEach
    public void runBefore() {
        testNote = new Note();
    }

    @Test
    public void makeDateAndBody() {
        testNote.addToBody("new stuff");
        assertEquals(testNote.getDateTime().toString() + ": new stuff", testNote.getDateTimeAndBody());
    }

    @Test
    public void addTextToEmptyNote() {
        assertEquals("", testNote.getBody());
        testNote.addToBody("new text");
        assertEquals(": new text", testNote.getBody());
    }

    @Test
    public void addTextToNonEmptyNote() {
        addTextToEmptyNote();
        testNote.addToBody("more text");
        assertEquals(": new text more text", testNote.getBody());

    }

    @Test
    public void replaceEmptyNote() {
        assertEquals("", testNote.getBody());
        testNote.replaceBody("new text");
        assertEquals("new text", testNote.getBody());
    }

    @Test
    public void replaceNonEmptyNote() {
        replaceEmptyNote();
        testNote.replaceBody("newer text");
        assertEquals("newer text", testNote.getBody());
    }

//    @Test
//    public void getDateforTesting() {
//        String gottenString = testNote.noteDateToString();
//        assertEquals("what", gottenString);
//    }




}