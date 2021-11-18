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

    private String projectName;
    private List<String> yarns;
    private List<String> needles;
    private Notes notes;

    private DefaultListModel yarnList;
    private DefaultListModel needleList;
    private DefaultListModel noteList;

    private JList yarnJList;
    private JList needleJList;
    private JList noteJList;

    private JScrollPane yarnScrollPane;
    private JScrollPane needleScrollPane;


    public void initializeFields(KnittingProject project) {
        projectName = project.getProjectName();
        yarns = project.getYarns();
        needles = project.getNeedles();
        notes = project.getNotes();

    }

    public void initializeYarnList() {
        //Create the list and put it in a scroll pane.
        yarnList = new DefaultListModel();
        yarnList.addElement("test yarn");

        yarnJList = new JList(yarnList);
        yarnJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        yarnJList.setSelectedIndex(0);
        yarnJList.addListSelectionListener(this);
        yarnJList.setVisibleRowCount(3);

        yarnScrollPane = new JScrollPane(yarnJList);
    }

    public void initializeNeedleList() {
        //Create the list and put it in a scroll pane.
        needleList = new DefaultListModel();
        needleList.addElement("test needle");

        needleJList = new JList(needleList);
        needleJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        needleJList.setSelectedIndex(0);
        needleJList.addListSelectionListener(this);
        needleJList.setVisibleRowCount(3);

        needleScrollPane = new JScrollPane(needleJList);
    }

    public NotebookPage(KnittingProject project) {
        initializeFields(project);
        initializeYarnList();
        initializeNeedleList();

        JSplitPane splitPane;

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
               yarnScrollPane, needleScrollPane);
        splitPane.setOneTouchExpandable(true);
        //splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(150, 100);
        yarnScrollPane.setPreferredSize(minimumSize);
        needleScrollPane.setPreferredSize(minimumSize);

        add(splitPane, BorderLayout.CENTER);




    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }


}
