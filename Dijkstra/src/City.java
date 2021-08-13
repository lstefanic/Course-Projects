
/*
 * City.java
 *
 * Version:
 * $Id: City.java,v 1.1 2014/10/31 21:26:51 lxs8146 Exp $
 * Revisions:
 * $Log: City.java,v $
 * Revision 1.1  2014/10/31 21:26:51  lxs8146
 * Initial revision
 *
 */

/**
 * A city has a name and a collection of data (predecessor, cost) that is used
 * to implement the shortest path algorithm.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class City implements Comparable<City> {

    private int cost;
    private String name;
    private String predecessor;
    
    /**
     * Construct the city with a given name. By default the predecessor should
     * be null and the cost should be Integer.MAX_VALUE.
     * 
     * @param name The city name
     */
    public City(String name) {
        this.name = name;
        predecessor = null;
        cost = Integer.MAX_VALUE;
    }
    
    /**
     * Get the name of the city.
     * 
     * @return The city name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the name of the city.
     * 
     * @param name The city's new name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the predecessor associated with the city.
     * 
     * @return The predecessor city
     */
    public String getPredecessor() {
        return predecessor;
    }
    
    /**
     * Set the predecessor to the given city.
     * 
     * @param predecessor The predecessor city
     */
    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }
    
    /**
     * Get the cost associated with the city.
     * 
     * @return The cost to travel to the city.
     */
    public int getCost() {
        return cost;
    }
    
    /**
     * Set the cost for a given city.
     * 
     * @param cost The cost to travel to the city
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    /**
     * Compare two cities to see how they are ordered. The primary quantity to
     * compare is cost. If costs are equal, compare the city names (normal
     * lexicographic ordering).
     * 
     * @return An integer whose value is
     * zero if both Cities' costs and names are equal;
     * negative if this cost is less than the other cost, or if the costs are
     * equal and if this name is less than the other name;
     * positive if this cost is greater than the other cost, or if the costs are
     * equal and if this name is greater than the other name.
     */
    public int compareTo(City other) {
        if ( cost != other.cost )
        return cost - other.cost;
        return name.compareTo(other.name);
    }
    
    /**
     * Compares this city to the other object. They are equal if the costs and
     * names are equal.
     * 
     * @return true if both objects are Cities, and they have the same cost and
     * name
     */
    public boolean equals(Object other) {
        return other instanceof City && compareTo((City)other) == 0;
    }
}
