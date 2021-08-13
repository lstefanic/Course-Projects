/*
 * CardButton.java
 * 
 * Version:
 * $Id: CardButton.java,v 1.3 2014/11/23 08:40:49 lxs8146 Exp $
 * Revisions:
 * $Log: CardButton.java,v $
 * Revision 1.3  2014/11/23 08:40:49  lxs8146
 * Third revision: Fixed formatting
 *
 * Revision 1.2 2014/11/23 08:37:08 lxs8146
 * Second revision: Completed implementation
 * 
 * Revision 1.1 2014/11/21 21:26:49 lxs8146
 * Initial commit
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

/**
 * Class definition for a button that represents a card in the concentration
 * game.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class CardButton extends JButton {

    private int pos;

    /**
     * Construct a CardButton object.
     * 
     * @param pos The position or index of the represented card in the model.
     */
    public CardButton(int pos) {
        this.pos = pos;
    }

    /**
     * Get the position of the card.
     * 
     * @return An integer that is the position or index of the represented card
     * in the model.
     */
    public int getPos() {
        return pos;
    }
}
