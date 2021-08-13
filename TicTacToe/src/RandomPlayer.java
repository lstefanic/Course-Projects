
/*
 * RandomPlayer.java
 *
 * Version:
 * $Id: RandomPlayer.java,v 1.3 2014/09/29 22:26:25 lxs8146 Exp $
 * Revisions:
 * $Log: RandomPlayer.java,v $
 * Revision 1.3  2014/09/29 22:26:25  lxs8146
 * Fifth revision: Modified implementations to improve GoodPlayer
 *
 * Revision 1.2  2014/09/29 04:08:26  lxs8146
 * Second revision: Fixed bugs regarding Player's type variable
 *
 * Revision 1.1  2014/09/29 02:34:30  lxs8146
 * Initial revision
 *
 */

import java.util.*;

/**
 * A TicTacToe player that makes random moves.
 * 
 * @author Lucas Stefanic
 */

public class RandomPlayer extends Player {
    
    /**
     * Construct a random Player.
     * @param piece The letter that the Player will put on the board.
     * @param type The type of player (which, in this case, will be random).
     * @param board The board the Player plays on.
     */
    public RandomPlayer(String piece, String type, Board board) {
        super(piece,type,board);
    }
    
    /**
     * Return a move to make. A random player picks a random space every time.
     * 
     * @return An array with the coordinates of the move to make.
     */
    public String[] move() {
        String[] result = new String[2];
        Random rn = new Random();
        int row;
        int col;
        while ( true ) {
            row = rn.nextInt(getBoard().getSize());
            col = rn.nextInt(getBoard().getSize());
            if ( getBoard().isOpen(row,col) ) {
                result[0] = Integer.toString(col);
                result[1] = Integer.toString(row);
                setLastMove(result);
                return result;
            }
        }
    }
}
