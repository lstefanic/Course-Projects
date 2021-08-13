/**
 * CardFace.java
 *
 * File:
 * $Id: CardFace.java,v 1.2 2014/11/23 08:40:49 lxs8146 Exp $
 *
 * Revisions:
 * $Log: CardFace.java,v $
 * Revision 1.2  2014/11/23 08:40:49  lxs8146
 * Third revision: Fixed formatting
 *
 * Revision 1.1 2014/11/21 21:26:49 lxs8146
 * Initial commit
 *
 * Revision 1.1 2013/11/19 17:50:42 csci140
 * Initial revision
 *
 */

/**
 * The interface that unites Cards and CardBacks.
 *
 * @author: Arthur Nunes-Harwitt
 */

public interface CardFace {

    /**
     * Get the value indicating whether or not the card is face-up.
     *
     * @return A boolean indicating whether or not the card is face-up.
     */
    public boolean isFaceUp();

    /**
     * Get the number on the card.
     *
     * @return An integer that is the number on the card.
     */
    public int getNumber();

}
