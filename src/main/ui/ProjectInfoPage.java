package ui;

import javax.swing.*;
import java.awt.*;

public class ProjectInfoPage extends JPanel {

    public ProjectInfoPage() {
        super(new BorderLayout());

        JPanel overallPane = new JPanel();
        JPanel comboBoxPane = new JPanel(); //use FlowLayout

        String[] comboBoxItems = {"All Projects", "Project Info"};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);

        comboBoxPane.add(cb);

        CardMaker createdCards = new CardMaker();
        cb.addItemListener(new ComboSelectListener(cb, createdCards));

        overallPane.add(comboBoxPane, BorderLayout.PAGE_START);
        overallPane.add(createdCards.getCards(), BorderLayout.CENTER);
    }
}
