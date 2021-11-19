package ui;

import model.KnittingProject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CardMaker extends JPanel {

    private  JPanel cards;
    private static final String PROJECTS = "All Projects";
    private static final String INFO = "Project Info";

    public CardMaker() {
        KnittingProject testkp = new KnittingProject("test delete later");
        ListGui listGui = new ListGui();
        JPanel card1 = listGui;
        JPanel card2 = listGui.getProjectPage();

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, PROJECTS);
        cards.add(card2, INFO);
    }

    public javax.swing.JPanel getCards() {
        return cards;
    }
}
