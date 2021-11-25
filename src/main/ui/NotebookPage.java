package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// Displays the projects panel on top, and shows project details (project yarn, needle, notes) as JPanels below it.
// This class references code from here: SplitPaneDemoProject.java
// Link: https://docs.oracle.com/javase/tutorialJWS/samples/uiswing/SplitPaneDemoProject

public class NotebookPage extends JPanel
        implements ListSelectionListener {

    //EFFECTS: Constructs a JPanel that shows projects on top and details in bottom
    public NotebookPage() {
        ProjectPanel projectPanel = new ProjectPanel();
        //KnittingProject startProject = projectPanel.getCurrentProjects().getAllKnittingProjects().get(0);

        JSplitPane splitPaneOne;
        JSplitPane splitPaneTwo;
        JSplitPane splitPaneThree;

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

    }


}
