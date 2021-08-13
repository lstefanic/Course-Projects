
/*
 * BadPlayer.java
 *
 * Version:
 * $Id: BadPlayer.java,v 1.4 2014/09/29 22:26:24 lxs8146 Exp $
 * Revisions:
 * $Log: BadPlayer.java,v $
 * Revision 1.4  2014/09/29 22:26:24  lxs8146
 * Fifth revision: Modified implementations to improve GoodPlayer
 *
 * Revision 1.3  2014/09/29 05:18:07  lxs8146
 * Fourth operation: Fixed many bugs
 *
 * Revision 1.2  2014/09/29 04:08:26  lxs8146
 * Second revision: Fixed bugs regarding Player's type variable
 *
 * Revision 1.1  2014/09/29 02:34:30  lxs8146
 * Initial revision
 *
 */

/**
 * A bad TicTacToe player.
 * 
 * @author Lucas Stefanic
 */

public class BadPlayer extends Player {
    
    /**
     * Construct a bad Player.
     * @param piece The letter that the Player will put on the board.
     * @param type The type of player (which, in this case, will be bad).
     * @param board The board the player plays on.
     */
    public BadPlayer(String piece, String type, Board board) {
        super(piece,type,board);
    }
    
    /**
     * Return a move to make. A bad player moves in the first open space.
     * 
     * @return An array with the coordinates of the move to make.
     */
    public String[] move() {
        String[] result = new String[2];
        for ( int row = 0 ; row < getBoard().getSize() ; row++ )
        for ( int col = 0 ; col < getBoard().getSize() ; col++ )
        if ( getBoard().isOpen(row,col) ) {
            result[0] = Integer.toString(col);
            result[1] = Integer.toString(row);
            setLastMove(result);
            return result;
        }
        return result;
    }
}
