
/*
 * TicTacToe.java
 *
 * Version:
 * $Id: TicTacToe.java,v 1.6 2014/10/01 22:08:31 lxs8146 Exp $
 * Revisions:
 * $Log: TicTacToe.java,v $
 * Revision 1.6  2014/10/01 22:08:31  lxs8146
 * Eighth revision: Fixed bug in checkForWin method of TicTacToe
 *
 * Revision 1.5  2014/09/29 22:26:24  lxs8146
 * Fifth revision: Modified implementations to improve GoodPlayer
 *
 * Revision 1.4  2014/09/29 05:18:06  lxs8146
 * Fourth operation: Fixed many bugs
 *
 * Revision 1.3  2014/09/29 04:27:05  lxs8146
 * Third revision: Fixed bugs in TicTacToe constructor
 *
 * Revision 1.2  2014/09/29 04:08:26  lxs8146
 * Second revision: Fixed bugs regarding Player's type variable
 *
 * Revision 1.1  2014/09/29 02:34:30  lxs8146
 * Initial revision
 *
 */

/**
 * A simulation of a TicTacToe game.
 * 
 * @author Lucas Stefanic
 */

public class TicTacToe {

    private Board board;
    private boolean complete;
    private Player X;
    private Player O;
    private Player current;
    
    /**
     * Construct a TicTacToe game to be played in main method.
     * 
     * @param x Describes the type of player X (random, bad, good, or human).
     * @param o Describes the type of player O (random, bad, good, or human).
     * @param size Board size (int from 1-3).
     */
    public TicTacToe(String x, String o, int size) {
        board = new Board(size);
        complete = false;
        switch(x) {
            case "bad":
                X = new BadPlayer("X",x,board);
                break;
            case "random":
                X = new RandomPlayer("X",x,board);
                break;
            case "good":
                X = new GoodPlayer("X",x,board,O);
                break;
            default:
                X = new HumanPlayer("X",x,board);
                break;
        }
        switch(o) {
            case "bad":
                O = new BadPlayer("O",o,board);
                break;
            case "random":
                O = new RandomPlayer("O",o,board);
                break;
            case "good":
                O = new GoodPlayer("O",o,board,X);
                break;
            default:
                O = new HumanPlayer("O",o,board);
                break;
        }
        current = X;
    }
    
    /**
     * Play a game of TicTacToe.
     * 
     * @param args Specifies the types of the players and board size (optional).
     */
    public static void main(String[] args) {
        
        // terminate program if arguments are invalid
        if ( invalidArguments(args) ) {
            System.err.println("Usage: java TicTacToe player-X player-O [size]" 
                + "\n" + "where player-X and player-O are one of:" + "\n\t" 
                + "human bad good random" + "\n" + "and [size] is optional in "
                + "the range from 1 to 3");
            return;
        }
        
        // prepare to play the game
        TicTacToe game;
        if (args.length == 2)
        game = new TicTacToe(args[0],args[1],1);
        else
        game = new TicTacToe(args[0],args[1],Integer.parseInt(args[2]));
        game.printBoard();
        String[] move = new String[2];
        
        // play the game
        while ( !game.complete ) {
            game.prePrintMove();
            move = game.current.move();
            game.printMove(move);
            game.checkForWin();
            game.switchPlayer();
            game.printBoard();
        }
    }
    
    /**
     * Check the validity (or lack thereof) of the command-line arguments.
     * 
     * @param args Command-line arguments passed to main method.
     * @return true if the arguments are invalid.
     */
    private static boolean invalidArguments(String[] args) {
        
        // check the number of arguments
        if ( args.length < 2 || args.length > 3 )
        return true;
        
        // check the validity of the first 2 arguments
        String[] types = {"random","bad","good","human"};
        boolean memberOf;
        for ( int i = 0 ; i < 2 ; i++ ) {
            memberOf = false;
            for ( String type : types )
            if ( args[i].equals(type) )
            memberOf = true;
            if ( !memberOf )
            return true;
        }
        
        // check the validity of the third argument (if present)
        if ( args.length == 3 )
        if ( Integer.parseInt(args[2]) < 1 || Integer.parseInt(args[2]) > 3 )
        return true;
        
        return false;
    }
    
    /**
     * Print a String representation of the board.
     */
    public void printBoard() {
        System.out.println("\n" + board);
    }
    
    /**
     * Print a description of the upcoming move (e.g. bad player X moving...).
     */
    public void prePrintMove() {
        System.out.println(current.getDescription() + " player "
            + current.getLetter() + " moving...");
    }
    
    /**
     * Print a description of the move just made (e.g. Player puts X at (0, 0).)
     * Also print the appropriate message if a human player quits.
     * 
     * @param move Array with coordinates of the move just made.
     */
    public void printMove(String[] move) {
        if ( move[0].equals("-1") || move[1].equals("-1") ) {
            System.out.println(current.getLetter() + " quits the game");
            complete = true;
        }
        else {
            board.put(current.getLetter(),move);
            System.out.println("Player puts " + current.getLetter() + " at (" 
                + move[0] + ", " + move[1] + ").");
        }
    }
    
    /**
     * Check if a player won with the move just made. Print the appropriate
     * message if the game is now over.
     */
    public void checkForWin() {
        
        // check rows
        boolean rowWin;
        for ( int row = 0 ; row < board.getSize() ; row++ ) {
            rowWin = true;
            for ( int col = 0 ; col < board.getSize() ; col++ )
            if ( !board.get(col,row).equals(current.getLetter()) )
            rowWin = false;
            if ( rowWin ) {
                System.out.println(current.getLetter() + " wins in row: "
                    + row);
                complete = true;
                return;
            }
        }
        
        // check columns
        boolean colWin;
        for ( int col = 0 ; col < board.getSize() ; col++ ) {
            colWin = true;
            for ( int row = 0 ; row < board.getSize() ; row++ )
            if ( !board.get(col,row).equals(current.getLetter()) )
            colWin = false;
            if ( colWin ) {
                System.out.println(current.getLetter() + " wins in column: "
                    + col);
                complete = true;
                return;
            }
        }
        
        // check diagonals
        boolean diagonal_1Win = true;
        for ( int i = 0 ; i < board.getSize() ; i++ ) {
            if ( !board.get(i,i).equals(current.getLetter()) )
            diagonal_1Win = false;
        }
        boolean diagonal_2Win = true;
        for ( int i = 0 ; i < board.getSize() ; i++ ) {
            if ( !board.get(i,board.getSize()-1-i).equals(current.getLetter()) )
            diagonal_2Win = false;
        }
        if ( diagonal_1Win || diagonal_2Win ) {
            System.out.println(current.getLetter() + " wins on the diagonal!");
            complete = true;
            return;
        }
        
        // check if the board is full (i.e. the game is a tie)
        if ( board.isFull() ) {
            System.out.println("Its a tie, no one wins.");
            complete = true;
        }
    }
    
    /**
     * Switch between Player X and Player O before the next turn.
     */
    public void switchPlayer() {
        if ( current == X )
        current = O;
        else
        current = X;
    }
}
