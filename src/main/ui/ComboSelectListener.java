package ui;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboSelectListener implements ItemListener {
    private JComboBox comboBoxItem;
    private JPanel cards;

    public ComboSelectListener(JComboBox cb, CardMaker cards) {
        this.comboBoxItem = cb;
        this.cards = cards.getCards();
    }

    //Method came from the ItemListener class implementation,
//contains functionality to process the combo box item selecting
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
}
