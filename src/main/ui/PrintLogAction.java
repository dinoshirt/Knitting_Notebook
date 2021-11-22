package ui;

import model.EventLog;
import model.LogException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrintLogAction extends AbstractAction {

    /**
     * Represents the action to be taken when the user wants to
     * print the event log.
     */
    PrintLogAction() {
        super("Print log to...");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        //String selected = (String) printCombo.getSelectedItem();

        try {
            LogPrinter lp;
            lp = new FilePrinter();
//                if (selected.equals(FILE_DESCRIPTOR))
//                    lp = new FilePrinter();
//                else {
//                    lp = new ScreenPrinter(AlarmControllerUI.this);
//                    desktop.add((ScreenPrinter) lp);
//                }

            lp.printLog(EventLog.getInstance());
        } catch (LogException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
