
/*
 * Board.java
 *
 * Version:
 * $Id: Board.java,v 1.3 2014/10/01 21:13:31 lxs8146 Exp $
 * Revisions:
 * $Log: Board.java,v $
 * Revision 1.3  2014/10/01 21:13:31  lxs8146
 * Sixth revision: Fixed bugs in HumanPlayer and GoodPlayer
 *
 * Revision 1.2  2014/09/29 22:26:25  lxs8146
 * Fifth revision: Modified implementations to improve GoodPlayer
 *
 * Revision 1.1  2014/09/29 02:34:31  lxs8146
 * Initial revision
 *
 */

/**
 * A class to represent the TicTacToe board.
 * 
 * @author Lucas Stefanic
 */

public class Board {

    private int size;
    private String[][] boardImage;
    
    /**
     * Construct a game board.
     * 
     * @param s Used to specify the size of the board.
     */
    public Board(int s) {
        size = 2*s + 1;
        boardImage = new String[size][size];
        for ( int row = 0 ; row < size ; row++ )
        for ( int col = 0 ; col < size ; col++ )
        boardImage[row][col] = " ";
    }
    
    /**
     * Return the size of the board.
     * 
     * @return The size of the board (i.e. the number of rows/columns 
     * (e.g. 3 for a 3x3 board)).
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Return a String representation of one square of the board.
     * 
     * @param col The column of the square.
     * @param row The row of the square.
     * @return "X", "O", or " ", depending on what's at the square.
     */
    public String get(int col, int row) {
        return boardImage[row][col];
    }
    
    /**
     * Put a letter in a square on the board.
     * 
     * @param letter The letter to put on the board.
     * @param move An array with the coordinates of the square.
     */
    public void put(String letter, String[] move) {
        int col = Integer.parseInt(move[0]);
        int row = Integer.parseInt(move[1]);
        boardImage[row][col] = letter;
    }
    
    /**
     * Check if a certain square can still be played in.
     * 
     * @param row The row of the square.
     * @param col The column of the square.
     * @return true if the square is empty.
     */
    public boolean isOpen(int row, int col) {
        
        // allow a human player to input -1 as a row or column
        if ( row == -1 || col == -1 )
        return true;
        
        return boardImage[row][col].equals(" ");
    }
    
    /**
     * Check if every square has been played in.
     * 
     * @return true if there are no empty squares.
     */
    public boolean isFull() {
        for ( int row = 0 ; row < size ; row++ )
        for ( int col = 0 ; col < size ; col++ )
        if ( boardImage[row][col].equals(" ") )
        return false;
        return true;
    }

    /**
     * Return a String representation of the board.
     * 
     * @return A String representation of the board.
     */
    public String toString() {
        String result = "";
        int row;
        for ( row = 0 ; row < size - 1 ; row++ ) {
            result += rowString(row);
            result += separator();
        }
        result += rowString(row);
        return result;
    }
    
    /**
     * Return a String representation of a given row of the board.
     * 
     * @param row The row to represent with a String.
     * @return A string to represent the row.
     */
    private String rowString(int row) {
        String result = "";
        int col;
        for ( col = 0 ; col < size - 1 ; col++ )
        result += boardImage[row][col] + "|";
        result += boardImage[row][col] + "\n";
        return result;
    }
    
    /**
     * Return a String to represent the separation between rows.
     * 
     * @return A String representation of the separation between rows.
     */
    private String separator() {
        String result = "";
        for ( int i = 0 ; i < size - 1 ; i++ )
        result += "-+";
        result += "-\n";
        return result;
    }
}
