/*
 * CheatFrame.java
 * 
 * Version:
 * $Id: CheatFrame.java,v 1.3 2014/11/23 08:40:51 lxs8146 Exp $
 * Revisions:
 * $Log: CheatFrame.java,v $
 * Revision 1.3  2014/11/23 08:40:51  lxs8146
 * Third revision: Fixed formatting
 *
 * Revision 1.2 2014/11/23 08:37:08 lxs8146
 * Second revision: Completed implementation
 * 
 * Revision 1.1 2014/11/21 21:26:50 lxs8146
 * Initial commit
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

import java.util.ArrayList;

/**
 * Class defintion for the cheating window in a concentration card game.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class CheatFrame extends JFrame {

    private JPanel content = new JPanel();

    /**
     * Construct a CheatFrame object.
     * 
     * @param cardButtons An ArrayList of CardButtons that are all showing their
     * numbers.
     * @param size The size (of one side) of the board (measured in cards).
     */
    public CheatFrame(ArrayList<CardButton> cardButtons, int size) {
        super("Lucas Stefanic: Concentration Game (Cheat)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        content.setLayout(new GridLayout(size,size));
        for ( int i = 0 ; i < 16 ; i++ )
            content.add(cardButtons.get(i));
        setContentPane(content);
    }
}
