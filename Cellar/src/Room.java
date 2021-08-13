/*
 * Room.java
 * 
 * Version:
 * $Id: Room.java,v 1.3 2014/11/30 07:30:32 lxs8146 Exp $
 * Revisions:
 * $Log: Room.java,v $
 * Revision 1.3  2014/11/30 07:30:32  lxs8146
 * Fourth revision: Reformatted code and added javadocs
 *
 * Revision 1.2 2014/11/30 05:30:07 lxs8146
 * Third revision: Fixed bugs and improved GUI appearance
 * 
 * Revision 1.1 2014/11/27 19:34:13 lxs8146
 * Second revision: Did a crapload of implementation
 */

import java.util.ArrayList;

/**
 * Class definition for a room in the dungeon.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Room {

    private int number;
    private ArrayList<Hallway> hallways;
    private ArrayList<Item> items;
    private Trap trap;
    private boolean hasSword;
    private boolean hasAmulet;

    /**
     * Construct a room.
     * 
     * @param n The number of the room
     */
    public Room(int n) {
        number = n;
        hallways = new ArrayList<Hallway>();
        items = new ArrayList<Item>();
        trap = null;
        hasSword = false;
        hasAmulet = false;
    }

    /**
     * Get the room number
     * 
     * @return the room's number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get the halls connected to the room
     * 
     * @return a list of halls connected to the room
     */
    public ArrayList<Hallway> getHalls() {
        return hallways;
    }

    /**
     * Get the items lying around the room
     * 
     * @return a list of items found in the room
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Get the trap in the room
     * 
     * @return the room's trap (if there is one)
     */
    public Trap getTrap() {
        return trap;
    }

    /**
     * Return true if the Sword is in the room
     * 
     * @return true if the room has the Sword
     */
    public boolean hasSword() {
        return hasSword;
    }

    /**
     * Return true if the Amulet is in the room
     * 
     * @return true if the room has the Amulet
     */
    public boolean hasAmulet() {
        return hasAmulet;
    }

    /**
     * Connect a hall to the room
     * 
     * @param h The hall to be connected to the room
     */
    public void addHall(Hallway h) {
        hallways.add(h);
    }

    /**
     * Put another item in the room
     * 
     * @param i The item to add to the room
     */
    public void addItem(Item i) {
        items.add(i);
    }

    /**
     * Put the Sword in the room
     */
    public void addSword() {
        addItem(new Item("Sword"));
        hasSword = true;
    }

    /**
     * Put the Amulet in the room
     */
    public void addAmulet() {
        addItem(new Item("Amulet"));
        hasAmulet = true;
    }

    /**
     * Take an item out of the room when the player picks it up
     * 
     * @param i The item the player picks up
     */
    public void removeItem(Item i) {
        if ( i.isSword() )
            hasSword = false;
        if ( i.isAmulet() )
            hasAmulet = false;
        items.remove(i);
    }

    /**
     * Put a trap in the room
     * 
     * @param t The trap to put in the room
     */
    public void setTrap(Trap t) {
        trap = t;
    }

    /**
     * Return true if the room has a trap
     * 
     * @return true if there is a trap in the room
     */
    public boolean hasTrap() {
        return trap != null;
    }

    /**
     * Test whether some other object is the same as the room
     * 
     * @param o The object to test
     * @return true if the object is a room with the same number as this room
     */
    public boolean equals(Object o) {
        return o instanceof Room && ((Room)o).number == number;
    }

    /**
     * Return a String representation of the room
     * 
     * @return a String with the word "Room" and the room's number
     */
    public String toString() {
        return "Room " + number;
    }
}
