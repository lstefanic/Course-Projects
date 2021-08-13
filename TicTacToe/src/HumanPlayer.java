
/*
 * HumanPlayer.java
 *
 * Version:
 * $Id: HumanPlayer.java,v 1.4 2014/10/01 21:13:31 lxs8146 Exp $
 * Revisions:
 * $Log: HumanPlayer.java,v $
 * Revision 1.4  2014/10/01 21:13:31  lxs8146
 * Sixth revision: Fixed bugs in HumanPlayer and GoodPlayer
 *
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

import java.util.Scanner;

/**
 * A human-controlled TicTacToe player.
 * 
 * @author Lucas Stefanic
 */

public class HumanPlayer extends Player {

    private static Scanner keyboardInput = new Scanner(System.in);
    
    /**
     * Construct a human Player.
     * @param piece The letter that the Player will put on the board.
     * @param type The type of player (which, in this case, will be human).
     * @param board The board the Player plays on.
     */
    public HumanPlayer(String piece, String type, Board board) {
        super(piece,type,board);
    }
    
    /**
     * Return a move to make. A human player moves in a space specified by human
     * input.
     * 
     * @return An array with the coordinates of the move to make.
     */
    public String[] move() {
        String[] result = new String[2];
        while ( true ) {
            System.out.print("Player " + getLetter() + ": Enter the col and row"
                + " for a move (-1 to quit): ");
            String col = keyboardInput.next();
            String row = keyboardInput.next();
            int c = Integer.parseInt(col);
            int r = Integer.parseInt(row);
            if ( c < -1 || r < -1 )
            System.out.println("invalid column and row: " + col + ", " + row);
            else if ( c >= getBoard().getSize() || r >= getBoard().getSize() )
            System.out.println("invalid column and row: " + col + ", " + row);
            else if ( !getBoard().isOpen(r,c) )
            System.out.println("invalid column and row: " + col + ", " + row);
            else {
                result[0] = col;
                result[1] = row;
                setLastMove(result);
                return result;
            }
        }
    }
}
