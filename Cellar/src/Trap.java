/*
 * Trap.java
 * 
 * Version:
 * $Id: Trap.java,v 1.2 2014/11/30 07:30:31 lxs8146 Exp $
 * Revisions:
 * $Log: Trap.java,v $
 * Revision 1.2  2014/11/30 07:30:31  lxs8146
 * Fourth revision: Reformatted code and added javadocs
 *
 * Revision 1.1 2014/11/27 19:34:13 lxs8146
 * Second revision: Did a crapload of implementation
 */

/**
 * Class definition for a trap to be found in the dungeon.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Trap {

    private String name;
    private String type;
    private int n;
    private String protection;

    /**
     * Construct a trap that weakens or warps the player.
     * 
     * @param name The name of the trap
     * @param type The type of trap (either "weaken" or "warp")
     * @param n The damage the trap deals (if type is "weaken") or the number of
     * the room the trap warps the player to
     * @param bane The name of the item that protects against the trap
     */
    public Trap(String name, String type, int n, String bane) {
        this.name = name;
        this.type = type;
        this.n = n;
        protection = bane;
    }

    /**
     * Construct a trap that destroys the player's items.
     * 
     * @param name The name of the trap
     * @param type The type of trap ("vanish")
     * @param bane The name of the item that protects against the trap
     */
    public Trap(String name, String type, String bane) {
        this(name,type,-1,bane);
    }

    /**
     * Return the type of trap
     * 
     * @return the type of trap
     */
    public String getType() {
        return type;
    }

    /**
     * Return the number associated with the trap (unused if the trap is a
     * vanish trap)
     * 
     * @return the number associated with the trap
     */
    public int getNum() {
        return n;
    }

    /**
     * Return the name of the item that protects against the trap
     * 
     * @return the name of the item that protects against the trap
     */
    public String getProtection() {
        return protection;
    }

    /**
     * Return a String representation of the trap
     * 
     * @return the trap's name
     */
    public String toString() {
        return name;
    }
}
