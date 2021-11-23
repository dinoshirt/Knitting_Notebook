package ui;

import model.EventLog;
import model.LogException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// This class runs the notebook application so it has a GUI
// This class references code from here: ListDemo.java
// Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html


public class Main {

    private static final String FILE_DESCRIPTOR = "...file";

    // EFFECTS: runs the GUI
    public static void main(String[] args) {
        //new NotebookApp();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //new PrintLogAction();

                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    public void run() {
                        System.out.println("In shutdown hook");
                        LogPrinter lp;
                        new ConsolePrinter();
                    }
                }, "Shutdown-thread"));
            }


        });

    }


    // EFFECTS: Displays a new NotebookPage in a JFrame
    private static void createAndShowGUI() throws IOException {
                //Create and set up the window.

        JFrame frame = new JFrame("My Knitting Notebook");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new NotebookPage();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }



}
