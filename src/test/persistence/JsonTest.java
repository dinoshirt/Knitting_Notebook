package persistence;

import model.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkKnittingProject(String name, List<String> yarns, List<String> needles, KnittingProject kp) {
        assertEquals(name, kp.getProjectName());
        assertEquals(yarns, kp.getYarns());
        assertEquals(needles, kp.getNeedles());
        //assertEquals(notes, kp.getNotes());
    }

    protected void checkParticularNote(KnittingProject kp, int noteIndex, LocalDateTime ldt, String body) {
        Note noteOfInterest = kp.getNotes().getNotes().get(noteIndex);
        assertEquals(ldt, noteOfInterest.getDateTime());
        assertEquals(body, noteOfInterest.getBody());
    }
}
