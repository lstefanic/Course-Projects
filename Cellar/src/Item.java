/*
 * Item.java
 * 
 * Version:
 * $Id: Item.java,v 1.2 2014/11/30 07:30:35 lxs8146 Exp $
 * Revisions:
 * $Log: Item.java,v $
 * Revision 1.2  2014/11/30 07:30:35  lxs8146
 * Fourth revision: Reformatted code and added javadocs
 *
 * Revision 1.1 2014/11/27 19:34:14 lxs8146
 * Second revision: Did a crapload of implementation
 */

/**
 * Class definition for an item to be found in the dungeon.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Item {

    private String name;

    /**
     * Construct an Item.
     * 
     * @param name The name of the item
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Return true if the item is the Sword (i.e. its name is "Sword")
     * 
     * @return true if the item is the Sword
     */
    public boolean isSword() {
        return name.equals("Sword");
    }

    /**
     * Return true if the item is the Amulet (i.e. its name is "Amulet")
     * 
     * @return true if the item is the Amulet
     */
    public boolean isAmulet() {
        return name.equals("Amulet");
    }

    /**
     * Return a String representation of the item
     * 
     * @return the item's name
     */
    public String toString() {
        return name;
    }
}
