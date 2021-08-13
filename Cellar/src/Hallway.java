/*
 * Hallway.java
 * 
 * Version:
 * $Id: Hallway.java,v 1.2 2014/11/30 07:30:35 lxs8146 Exp $
 * Revisions:
 * $Log: Hallway.java,v $
 * Revision 1.2  2014/11/30 07:30:35  lxs8146
 * Fourth revision: Reformatted code and added javadocs
 *
 * Revision 1.1 2014/11/27 19:34:14 lxs8146
 * Second revision: Did a crapload of implementation
 */

/**
 * Class definition for a hallway that connects two rooms in the dungeon.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Hallway {

    private String name;
    private Room end;
    private Room otherEnd;

    /**
     * Construct a hallway.
     * 
     * @param name The name of the hallway
     * @param end A room the hall is connected to
     * @param otherEnd The other room the hall is connected to
     */
    public Hallway(String name, Room end, Room otherEnd) {
        this.name = name;
        this.end = end;
        this.otherEnd = otherEnd;
        this.end.addHall(this);
        this.otherEnd.addHall(this);
    }

    /**
     * Return the room at the other end of the hall
     * 
     * @param room One of the rooms the hall is connected to
     * @return the other room the hall is connected to
     */
    public Room oppositeEnd(Room room) {
        if ( room.equals(end) )
            return otherEnd;
        return end;
    }

    /**
     * Return a String representation of the hall
     * 
     * @return the hallway's name
     */
    public String toString() {
        return name;
    }
}
