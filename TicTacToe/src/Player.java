
/*
 * Player.java
 *
 * Version:
 * $Id: Player.java,v 1.4 2014/10/01 22:06:30 lxs8146 Exp $
 * Revisions:
 * $Log: Player.java,v $
 * Revision 1.4  2014/10/01 22:06:30  lxs8146
 * Seventh revision: Fixed bug in updateKnowledge method of GoodPlayer
 *
 * Revision 1.3  2014/09/29 22:26:24  lxs8146
 * Fifth revision: Modified implementations to improve GoodPlayer
 *
 * Revision 1.2  2014/09/29 04:08:26  lxs8146
 * Second revision: Fixed bugs regarding Player's type variable
 *
 * Revision 1.1  2014/09/29 02:34:29  lxs8146
 * Initial revision
 *
 */

/**
 * An abstract class to represent a player.
 * 
 * @author Lucas Stefanic
 */

public abstract class Player {

    private String letter;
    private String description;
    private Board gameBoard;
    private String[] lastMove;
    
    /**
     * Construct a Player.
     * 
     * @param piece The letter the Player plays on the board.
     * @param type The type of Player (bad, random, good, or human).
     * @param board The board the Player plays on.
     */
    public Player(String piece, String type, Board board) {
        letter = piece;
        description = type;
        gameBoard = board;
        lastMove = new String[2];
        lastMove[0] = "";
        lastMove[1] = "";
    }
    
    /**
     * Return a move to make.
     * 
     * @return An array with the coordinates of the move to make.
     */
    public abstract String[] move();
    
    /**
     * Return the letter of the Player.
     * 
     * @return The letter of the Player.
     */
    public String getLetter() {
        return letter;
    }
    
    /**
     * Return the type of player.
     * @return The type of player (bad, random, good, or human).
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Return the board the Player plays on.
     * 
     * @return The board the Player plays on.
     */
    public Board getBoard() {
        return gameBoard;
    }
    
    /**
     * Return the last move the Player made.
     * 
     * @return A String[] with the coordinates of the last move.
     */
    public String[] getLastMove() {
        return lastMove;
    }
    
    /**
     * Update lastMove whenever the Player makes a move.
     * 
     * @param playerMove The coordinates of the most recent move.
     */
    public void setLastMove(String[] playerMove) {
        lastMove = playerMove;
    }
}
