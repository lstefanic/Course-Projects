
/*
 * GoodPlayer.java
 *
 * Version:
 * $Id: GoodPlayer.java,v 1.7 2014/10/02 03:18:06 lxs8146 Exp $
 * Revisions:
 * $Log: GoodPlayer.java,v $
 * Revision 1.7  2014/10/02 03:18:06  lxs8146
 * Ninth revision: Fixed bug in GoodPlayer move method
 *
 * Revision 1.6  2014/10/01 22:06:31  lxs8146
 * Seventh revision: Fixed bug in updateKnowledge method of GoodPlayer
 *
 * Revision 1.5  2014/10/01 21:13:31  lxs8146
 * Sixth revision: Fixed bugs in HumanPlayer and GoodPlayer
 *
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

import java.util.*;

/**
 * A good TicTacToe player.
 * 
 * @author Lucas Stefanic
 */

public class GoodPlayer extends Player {

    private Player opponent;
    private HashMap<String,ArrayList<String>> goodRows;
    private HashMap<String,ArrayList<String>> goodCols;
    private HashMap<String,ArrayList<String>> goodDiagonals;
    private HashMap<String,ArrayList<String>> badRows;
    private HashMap<String,ArrayList<String>> badCols;
    private HashMap<String,ArrayList<String>> badDiagonals;
    
    /**
     * Construct a good Player.
     * @param piece The letter that the Player will put on the board.
     * @param type The type of player (which, in this case, will be good).
     * @param board The board the Player plays on.
     */
    public GoodPlayer(String piece, String type, Board board, Player other) {
        super(piece,type,board);
        opponent = other;
        goodRows = new HashMap<String,ArrayList<String>>();
        goodCols = new HashMap<String,ArrayList<String>>();
        goodDiagonals = new HashMap<String,ArrayList<String>>();
        badRows = new HashMap<String,ArrayList<String>>();
        badCols = new HashMap<String,ArrayList<String>>();
        badDiagonals = new HashMap<String,ArrayList<String>>();
    }
    
    /**
     * Return a move to make. A good player will make a winning move if one
     * exists. Otherwise, it will block the opponent's winning move, if one
     * exists. Otherwise, it will try to move in the center, then the corners,
     * then a column or row that has not been played in yet. If a move has not
     * been found by this point, a random space will be chosen.
     * 
     * @return An array with the coordinates of the move to make.
     */
    public String[] move() {
        updateKnowledge(opponent.getLastMove());
        String[] result = new String[2];
        int size = getBoard().getSize();
        
        // make a winning move (if there is one)
        for ( String col : goodCols.keySet() )
        if ( goodCols.get(col).size() == size - 1 ) {
            result[0] = col;
            for ( int row = 0 ; row < size ; row++ )
            if ( !goodCols.get(col).contains(Integer.toString(row)) ) {
                result[1] = Integer.toString(row);
                setLastMove(result);
                return result;
            }
        }
        for ( String row : goodRows.keySet() )
        if ( goodRows.get(row).size() == size - 1 ) {
            result[1] = row;
            for ( int col = 0 ; col < size ; col++ )
            if ( !goodRows.get(row).contains(Integer.toString(col)) ) {
                result[0] = Integer.toString(col);
                setLastMove(result);
                return result;
            }
        }
        for ( String diag : goodDiagonals.keySet() )
        if ( goodDiagonals.get(diag).size() == size - 1 ) {
        for ( int col = 0 ; col < size ; col++ )
        if ( !goodDiagonals.get(diag).contains(Integer.toString(col)) ) {
                result[0] = Integer.toString(col);
                if ( diag.equals("1") )
                result[1] = result[0];
                else
                result[1] = Integer.toString(size - col - 1);
                setLastMove(result);
                return result;
            }
        }
        
        // block opponent's winning move (if there is one)
        for ( String col : badCols.keySet() )
        if ( badCols.get(col).size() == size - 1 )
        for ( int row = 0 ; row < size ; row++ )
        if ( !badCols.get(col).contains(Integer.toString(row)) ) {
            if ( !getBoard().isOpen(row,Integer.parseInt(col)) )
            break;
            result[0] = col;
            result[1] = Integer.toString(row);
            setLastMove(result);
            return result;
        }
        for ( String row : badRows.keySet() )
        if ( badRows.get(row).size() == size - 1 )
        for ( int col = 0 ; col < size ; col++ )
        if ( !badRows.get(row).contains(Integer.toString(col)) ) {
            if ( !getBoard().isOpen(Integer.parseInt(row),col) )
            break;
            result[0] = Integer.toString(col);
            result[1] = row;
            setLastMove(result);
            return result;
        }
        for ( String diag : badDiagonals.keySet() )
        if ( badDiagonals.get(diag).size() == size - 1 )
        for ( int col = 0 ; col < size ; col++ )
        if ( !badDiagonals.get(diag).contains(Integer.toString(col)) ) {
            if ( diag.equals("1") && getBoard().isOpen(col,col) ) {
                result[0] = Integer.toString(col);
                result[1] = result[0];
                setLastMove(result);
                return result;
            }
            else if ( diag.equals("2") && getBoard().isOpen(size-col-1,col) ) {
                result[0] = Integer.toString(col);
                result[1] = Integer.toString(size - col - 1);
                setLastMove(result);
                return result;
            }
        }
        
        // move in the center of the board (if possible)
        if ( getBoard().isOpen(size/2,size/2) ) {
            result[0] = Integer.toString(size / 2);
            result[1] = result[0];
            setLastMove(result);
            return result;
        }
        
        // move in one of the corners (if possible)
        for ( int row = 0 ; row < size ; row += size - 1 )
        for ( int col = 0 ; col < size ; col += size - 1 )
        if ( getBoard().isOpen(row,col) ) {
            result[0] = Integer.toString(col);
            result[1] = Integer.toString(row);
            setLastMove(result);
            return result;
        }
        
        // move in an empty column (if possible)
        for ( int col = 0 ; col < size ; col++ ) {
            String c = Integer.toString(col);
            if ( !goodCols.containsKey(c) && !badCols.containsKey(c) ) {
                Random rn = new Random();
                int r = rn.nextInt(size);
                result[0] = c;
                result[1] = Integer.toString(r);
                setLastMove(result);
                return result;
            }
        }
        
        // move in an empty row (if possible)
        for ( int row = 0 ; row < size ; row++ ) {
            String r = Integer.toString(row);
            if ( !goodRows.containsKey(r) && !badRows.containsKey(r) ) {
                Random rn = new Random();
                int c = rn.nextInt(size);
                result[0] = Integer.toString(c);
                result[1] = r;
                setLastMove(result);
                return result;
            }
        }
        
        /*
         * code to move in an empty diagonal would go here, but by this point,
         * some player has moved in the center, which means that neither
         * diagonal is empty.
         */
        
        // move in a random space
        Random rn = new Random();
        int row;
        int col;
        while ( true ) {
            row = rn.nextInt(size);
            col = rn.nextInt(size);
            if ( getBoard().isOpen(row,col) ) {
                result[0] = Integer.toString(col);
                result[1] = Integer.toString(row);
                setLastMove(result);
                return result;
            }
        }
    }
    
    /**
     * Update the good Player's knowledge of the board. The player uses
     * goodRows, goodCols, and goodDiagonals to keep track of where it can win.
     * The player uses badRows, badCols, and badDiagonals to keep track of where
     * it can't. On each turn, the good Player updates this knowledge with the
     * last move it made and the last move its opponent made.
     * 
     * @param badSpot The last move the opponent made.
     */
    public void updateKnowledge(String[] badSpot) {
        String[] goodSpot = getLastMove();
        if ( !goodSpot[0].equals("") && !goodSpot[1].equals("") ) {
            
            // update goodCols
            if ( !badCols.containsKey(goodSpot[0]) ) {
                if ( !goodCols.containsKey(goodSpot[0]) )
                goodCols.put(goodSpot[0],new ArrayList<String>());
                goodCols.get(goodSpot[0]).add(goodSpot[1]);
            }
        
            // update goodRows
            if ( !badRows.containsKey(goodSpot[1]) ) {
                if ( !goodRows.containsKey(goodSpot[1]) )
                goodRows.put(goodSpot[1],new ArrayList<String>());
                goodRows.get(goodSpot[1]).add(goodSpot[0]);            
            }
        
            //update goodDiagonals
            if ( !badDiagonals.containsKey("1") ) {
                if ( goodSpot[0].equals(goodSpot[1])
                    && !goodDiagonals.containsKey("1") )
                goodDiagonals.put("1",new ArrayList<String>());
                if ( goodSpot[0].equals(goodSpot[1]) )
                goodDiagonals.get("1").add(goodSpot[0]);
            }
            if ( !badDiagonals.containsKey("2") ) {
                int col1 = Integer.parseInt(goodSpot[0]);
                int row1 = Integer.parseInt(goodSpot[1]);
                if ( col1 + row1 == getBoard().getSize() - 1
                    && !goodDiagonals.containsKey("2") )
                goodDiagonals.put("2",new ArrayList<String>());
                if ( col1 + row1 == getBoard().getSize() - 1 )
                goodDiagonals.get("2").add(goodSpot[0]);            
            }
        }
        if ( !badSpot[0].equals("") && !badSpot[1].equals("") ) {  
            
            // update badCols
            goodCols.remove(badSpot[0]);
            if ( !badCols.containsKey(badSpot[0]) )
            badCols.put(badSpot[0],new ArrayList<String>());
            badCols.get(badSpot[0]).add(badSpot[1]);
        
            // update badRows
            goodRows.remove(badSpot[1]);
            if ( !badRows.containsKey(badSpot[1]) )
            badRows.put(badSpot[1],new ArrayList<String>());
            badRows.get(badSpot[1]).add(badSpot[0]);
        
            // update badDiagonals
            if ( badSpot[0].equals(badSpot[1])
                && !badDiagonals.containsKey("1") ) {
                    goodDiagonals.remove("1");
                    badDiagonals.put("1",new ArrayList<String>());
            }
            if ( badSpot[0].equals(badSpot[1]) )
            badDiagonals.get("1").add(badSpot[0]);
            int col2 = Integer.parseInt(badSpot[0]);
            int row2 = Integer.parseInt(badSpot[1]);
            if ( col2 + row2 == getBoard().getSize() - 1
                && !badDiagonals.containsKey("2") ) {
                    goodDiagonals.remove("2");
                    badDiagonals.put("2",new ArrayList<String>());
            }
            if ( col2 + row2 == getBoard().getSize() - 1 )
            badDiagonals.get("2").add(badSpot[0]);
        }
    }
}
