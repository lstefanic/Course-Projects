/*
 * Monster.java
 * 
 * Version:
 * $Id: Monster.java,v 1.3 2014/11/30 07:30:31 lxs8146 Exp $
 * Revisions:
 * $Log: Monster.java,v $
 * Revision 1.3  2014/11/30 07:30:31  lxs8146
 * Fourth revision: Reformatted code and added javadocs
 *
 * Revision 1.2 2014/11/30 05:30:07 lxs8146
 * Third revision: Fixed bugs and improved GUI appearance
 * 
 * Revision 1.1 2014/11/27 19:34:13 lxs8146
 * Second revision: Did a crapload of implementation
 */

import java.util.Random;

/**
 * Class definition for the monster that roams the dungeon.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Monster {

    private String name;
    private Room current;
    private boolean isAlive;

    /**
     * Construct the monster.
     * 
     * @param name The monster's name
     * @param start The room the monster starts in
     */
    public Monster(String name, Room start) {
        this.name = name;
        current = start;
        isAlive = true;
    }

    /**
     * Return the current location of the monster
     * 
     * @return the room the monster is currently in
     */
    public Room getRoom() {
        return current;
    }

    /**
     * Return the monster's status
     * 
     * @return true if the monster is still alive
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Kill the monster
     */
    public void die() {
        isAlive = false;
    }

    /**
     * Move the monster to a random adjacent room
     */
    public void move() {
        int numChoices = current.getHalls().size();
        if ( numChoices > 0 ) {
            Random rn = new Random();
            int i = rn.nextInt(numChoices);
            current = current.getHalls().get(i).oppositeEnd(current);
        }
    }

    /**
     * Return a String representation of the monster
     * 
     * @return the monster's name
     */
    public String toString() {
        return name;
    }
}
