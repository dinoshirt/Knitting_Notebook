package ui;

import model.KnittingProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

//This class runs the notebook application



public class Main {



    public static void main(String[] args) {
        //new NotebookApp();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }



//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("ListDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        JComponent newContentPane = new ListGui();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }

    private static void createAndShowGUI() {
//        JPanel overallPane = new JPanel();
//        JPanel comboBoxPane = new JPanel(); //use FlowLayout
//
//        String[] comboBoxItems = {"All Projects", "Project Info"};
//        JComboBox cb = new JComboBox(comboBoxItems);
//        cb.setEditable(false);
//
//        comboBoxPane.add(cb);
//
//        CardMaker createdCards = new CardMaker();
//        cb.addItemListener(new ComboSelectListener(cb, createdCards));
//
//        overallPane.add(comboBoxPane, BorderLayout.PAGE_START);
//        overallPane.add(createdCards.getCards(), BorderLayout.CENTER);

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
