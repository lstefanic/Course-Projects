/*
 * Player.java
 * 
 * Version:
 * $Id: Player.java,v 1.3 2014/11/30 07:30:32 lxs8146 Exp $
 * Revisions:
 * $Log: Player.java,v $
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
 * Class definition for the player.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Player {

    private int lifeForce;
    private int experience;
    private ArrayList<Item> inventory;
    private boolean hasSword;
    private boolean hasAmulet;

    /**
     * Construct the player.
     */
    public Player() {
        lifeForce = 100;
        experience = 1;
        inventory = new ArrayList<Item>();
        hasSword = false;
        hasAmulet = false;
    }

    /**
     * Return the player's current life force
     * 
     * @return the player's life force
     */
    public int getHealth() {
        return lifeForce;
    }

    /**
     * Return the player's experience level
     * 
     * @return the player's experience level
     */
    public int getLevel() {
        return experience;
    }

    /**
     * Return the items the player has on hand
     * 
     * @return the player's inventory
     */
    public ArrayList<Item> getItems() {
        return inventory;
    }

    /**
     * Return true if the player has the Sword
     * 
     * @return true if the player has the Sword
     */
    public boolean hasSword() {
        return hasSword;
    }

    /**
     * Return true if the player has the Amulet
     * 
     * @return true if the player has the Amulet
     */
    public boolean hasAmulet() {
        return hasAmulet;
    }

    /**
     * Weaken the player when a weaken trap is sprung
     * 
     * @param damage the amount to take away from the player's life force
     */
    public void takeDamage(int damage) {
        lifeForce -= damage;
    }

    /**
     * Level up the player
     */
    public void levelUp() {
        experience++;
    }

    /**
     * Pick up an item
     * 
     * @param item The item to pick up
     */
    public void pickup(Item item) {
        if ( item.isAmulet() )
            hasAmulet = true;
        inventory.add(item);
    }

    /**
     * Drop an item
     * 
     * @param item The item to drop
     */
    public void drop(Item item) {
        inventory.remove(item);
    }

    /**
     * Use an item to protect the player from a trap
     * 
     * @param trap The trap to protect the player from
     */
    public void useProtection(Trap trap) {
        for ( Item item : inventory )
            if ( item.toString().equals(trap.getProtection()) ) {
                inventory.remove(item);
                return;
            }
    }

    /**
     * Destroy the player's items when a vanish trap is sprung
     */
    public void destroyAllItems() {
        inventory.clear();
    }

    /**
     * Pick up the Sword
     */
    public void getSword() {
        hasSword = true;
    }

    /**
     * Return true if the player has died in the dungeon
     * 
     * @return true if the player's life force has dropped to 0 or below
     */
    public boolean isDead() {
        return lifeForce <= 0;
    }

    /**
     * Return true if the player can still pick up items
     * 
     * @return true if the number of items the player has is less than the
     * player's experience level
     */
    public boolean canGetItems() {
        return inventory.size() < experience;
    }

    /**
     * Test whether the player can be protected from a trap
     * 
     * @param trap The trap to try to protect the player from
     * @return true if the player has an item that protects against the trap
     */
    public boolean hasProtection(Trap trap) {
        for ( Item item : inventory )
            if ( item.toString().equals(trap.getProtection()) )
                return true;
        return false;
    }
}
