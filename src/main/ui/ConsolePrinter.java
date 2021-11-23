package ui;

import model.Event;
import model.EventLog;

public class ConsolePrinter {

    public ConsolePrinter() {
        System.out.println("PRINTING ACTIONS PERFORMED");
        EventLog el = EventLog.getInstance();
        for (Event e: el) {
            System.out.println(e.getDescription());
            System.out.println("\t");
        }
        System.out.println("THAT'S IT");
    }
}
