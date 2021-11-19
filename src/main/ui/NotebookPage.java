package ui;

import model.AllKnittingProjects;
import model.KnittingProject;
import model.KnittingSupplies;
import model.Notes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class NotebookPage extends JPanel
        implements ListSelectionListener {

    private JList projectJList;
    private String nameOfSelectedProject;
    private AllKnittingProjects currentProjects;
    private KnittingProject project;
    private ListGui projectPanel;

    private JPanel panelRight;
    private JPanel panelMiddle;
    private JPanel panelLeft;


    public NotebookPage() {
        ListGui projectPanel = new ListGui();
        KnittingProject startProject = projectPanel.getCurrentProjects().getAllKnittingProjects().get(0);

//        JPanel panelRight = new NotePanel(indexedProject);
//        JPanel panelMiddle = new NeedlePanel(indexedProject);
//        JPanel panelLeft = new YarnPanel(indexedProject);


        JSplitPane splitPaneOne;
        JSplitPane splitPaneTwo;
        JSplitPane splitPaneThree;

        //String nameOfSelectedProject = (String) list.getSelectedValue();

        splitPaneOne = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                projectPanel.getPanelLeft(), projectPanel.getPanelMiddle());
        splitPaneTwo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneOne, projectPanel.getPanelRight());
        splitPaneThree = new JSplitPane(JSplitPane.VERTICAL_SPLIT, projectPanel, splitPaneTwo);
        splitPaneOne.setOneTouchExpandable(true);
        //splitPane.setDividerLocation(150);


        //Provide minimum sizes for the components in the split pane
        Dimension minimumSize = new Dimension(400, 100);
        projectPanel.getPanelLeft().setPreferredSize(minimumSize);
        projectPanel.getPanelMiddle().setPreferredSize(minimumSize);
        projectPanel.getPanelRight().setPreferredSize(minimumSize);
        projectPanel.setPreferredSize(minimumSize);

        add(splitPaneThree, BorderLayout.CENTER);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
//        JList list = (JList)e.getSource();
//        int index = list.getSelectedIndex();
//        KnittingProject indexedProject = projectPanel.getCurrentProjects().getAllKnittingProjects().get(index);
//        System.out.println(indexedProject.getProjectName());
//        YarnPanel yarnPanel = new YarnPanel(indexedProject);
//        NeedlePanel needlePanel = new NeedlePanel(indexedProject);
//        NotePanel notePanel = new NotePanel(indexedProject);
//
//        panelLeft.revalidate();
//        panelLeft.repaint();
//        panelLeft.add(yarnPanel);
//
//
//        panelMiddle.add(needlePanel);
//        panelRight.add(notePanel);


    }


}
