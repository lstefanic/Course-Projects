
/*
 * Road.java
 *
 * Version:
 * $Id: Road.java,v 1.1 2014/10/31 21:26:51 lxs8146 Exp $
 * Revisions:
 * $Log: Road.java,v $
 * Revision 1.1  2014/10/31 21:26:51  lxs8146
 * Initial revision
 *
 */

/**
 * This class represents a road segment of a certain length and name.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Road {

    private int length;
    private String road;
    
    /**
     * Construct a Road object.
     * 
     * @param length Road length
     * @param road Road name
     */
    public Road(int length, String road) {
        this.length = length;
        this.road = road;
    }
    
    /**
     * Get the road length.
     * 
     * @return road length
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Set the road length.
     * 
     * @param length The new length for the road.
     */
    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * Get the road name.
     * 
     * @return road name
     */
    public String getRoad() {
        return road;
    }
    
    /**
     * Set the road name.
     * 
     * @param road The new name for the road.
     */
    public void setRoad(String road) {
        this.road = road;
    }
}
