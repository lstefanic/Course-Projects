/*
 * CellarModel.java
 * 
 * Version:
 * $Id: CellarModel.java,v 1.3 2014/11/30 07:30:29 lxs8146 Exp $
 * Revisions:
 * $Log: CellarModel.java,v $
 * Revision 1.3  2014/11/30 07:30:29  lxs8146
 * Fourth revision: Reformatted code and added javadocs
 *
 * Revision 1.2 2014/11/30 05:30:06 lxs8146
 * Third revision: Fixed bugs and improved GUI appearance
 * 
 * Revision 1.1 2014/11/27 19:34:12 lxs8146
 * Second revision: Did a crapload of implementation
 */

import java.io.*;

import java.util.Observable;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class definition for the dungeon crawler model.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class CellarModel extends Observable {

    private ArrayList<Room> rooms = new ArrayList<Room>();
    private int currentRoom = 0;
    private Player player = new Player();
    private Monster monster = null;
    private String messages = "Welcome to the game!\n";

    /**
     * Construct the dungeon based on the specifications in a configuration file
     * 
     * @param file The configuration file with information on rooms, items,
     * traps, halls, and sometimes a monster
     */
    public CellarModel(File file) {
        try {
            Scanner fileInput = new Scanner(file);
            ArrayList<Trap> traps = new ArrayList<Trap>();
            int numTraps = fileInput.nextInt();
            for ( int i = 0 ; i < numTraps ; i++ ) {
                String trapName = fileInput.next();
                String trapType = fileInput.next();
                switch(trapType) {
                    case "vanish":
                        traps.add(new Trap(trapName,"vanish",fileInput.next()));
                        break;
                    case "weaken":
                        int damage = fileInput.nextInt();
                        traps.add(new Trap(trapName,"weaken",damage,fileInput
                                .next()));
                        break;
                    case "warp":
                        int room = fileInput.nextInt();
                        traps.add(new Trap(trapName,"warp",room,fileInput
                                .next()));
                        break;
                }
            }
            int numRooms = fileInput.nextInt();
            int withAmulet = fileInput.nextInt();
            for ( int i = 0 ; i < numRooms ; i++ ) {
                Room room = new Room(i);
                String trapName = fileInput.next();
                if ( !trapName.equals("none") )
                    for ( Trap trap : traps )
                        if ( trap.toString().equals(trapName) )
                            room.setTrap(trap);
                String firstItem = fileInput.next();
                if ( !firstItem.equals("none") ) {
                    if ( firstItem.equals("Sword") )
                        room.addSword();
                    else
                        room.addItem(new Item(firstItem));
                    String itemNames = fileInput.nextLine();
                    String[] itemArray = itemNames.split("\\s");
                    for ( String itemName : itemArray )
                        if ( itemName.equals("Sword") )
                            room.addSword();
                        else
                            room.addItem(new Item(itemName));
                }
                if ( i == withAmulet )
                    room.addAmulet();
                rooms.add(room);
            }
            ArrayList<Hallway> hallways = new ArrayList<Hallway>();
            int numHalls = fileInput.nextInt();
            for ( int i = 0 ; i < numHalls ; i++ ) {
                String hallName = fileInput.next();
                Room firstEnd = rooms.get(fileInput.nextInt());
                Room secondEnd = rooms.get(fileInput.nextInt());
                hallways.add(new Hallway(hallName,firstEnd,secondEnd));
            }
            if ( fileInput.nextInt() == 1 ) {
                String monsterName = fileInput.next();
                Room monsterStart = rooms.get(fileInput.nextInt());
                monster = new Monster(monsterName,monsterStart);
            }
            fileInput.close();
            setChanged();
            notifyObservers();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get the room the player is currently in
     * 
     * @return The room the player is in
     */
    public Room currentRoom() {
        return rooms.get(currentRoom);
    }

    /**
     * Get the player
     * 
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the monster
     * 
     * @return the monster (if there is one)
     */
    public Monster getMonster() {
        return monster;
    }

    /**
     * Return a String containing messages to the player
     * 
     * @return a String with messages for the player
     */
    public String getMessages() {
        return messages;
    }

    /**
     * Move down a hall. Used by the player to move through the dungeon
     * 
     * @param hall The hall to move down
     */
    public void moveDownHall(Hallway hall) {
        messages +=
                "You entered room "
                        + rooms.indexOf(hall.oppositeEnd(currentRoom())) + "\n";
        moveToRoom(rooms.indexOf(hall.oppositeEnd(currentRoom())));
        moveMonster();
        setChanged();
        notifyObservers();
    }

    /**
     * Move the player to a specific room. Used whenever the player moves down a
     * hall or walks into a warp trap
     * 
     * @param n The number of the room to move the player to
     */
    private void moveToRoom(int n) {
        currentRoom = n;
        if ( currentRoom().hasTrap() ) {
            messages +=
                    "The trap called " + currentRoom().getTrap()
                            + " was activated\n";
            if ( player.hasProtection(currentRoom().getTrap()) ) {
                player.useProtection(currentRoom().getTrap());
                player.levelUp();
                messages += "But it's okay because you had protection\n";
                messages +=
                        "Your " + currentRoom().getTrap().getProtection()
                                + " was destroyed though\n";
            }
            else
                switch(currentRoom().getTrap().getType()) {
                    case "weaken":
                        int damage = currentRoom().getTrap().getNum();
                        player.takeDamage(damage);
                        messages +=
                                "Your life force was reduced by " + damage
                                        + "\n";
                        break;
                    case "warp":
                        int room = currentRoom().getTrap().getNum();
                        messages += "You were warped to room " + room + "\n";
                        moveToRoom(room);
                        return;
                    case "vanish":
                        player.destroyAllItems();
                        messages += "All your items were destroyed\n";
                        break;
                }
        }
    }

    /**
     * Attempt by the player to take an item from the room and add it to their
     * inventory. This fails if the player can't pick up any more items
     * 
     * @param item The item to try to pick up
     */
    public void pickupItem(Item item) {
        if ( item.isSword() ) {
            player.getSword();
            currentRoom().removeItem(item);
            messages += "You picked up the Sword\n";
            moveMonster();
        }
        else if ( player.canGetItems() ) {
            player.pickup(item);
            currentRoom().removeItem(item);
            if ( !item.isAmulet() )
                messages += "You picked up the " + item + "\n";
            moveMonster();
        }
        else
            messages += "You can't pick up any more items right now\n";
        setChanged();
        notifyObservers();
    }

    /**
     * The player drops an item. It can be picked up later if the player returns
     * to the room.
     * 
     * @param item The item to drop
     */
    public void dropItem(Item item) {
        player.drop(item);
        currentRoom().addItem(item);
        messages += "You dropped the " + item + "\n";
        moveMonster();
        setChanged();
        notifyObservers();
    }

    /**
     * If there is a still-living monster in the dungeon, move it to a random
     * adjacent room.
     */
    public void moveMonster() {
        if ( monster != null && monster.isAlive() ) {
            monster.move();
            if ( monster.getRoom().equals(currentRoom()) ) {
                messages +=
                        "You encountered the monster called " + monster + "\n";
                if ( player.hasSword() ) {
                    monster.die();
                    player.levelUp();
                    messages += "You killed " + monster + "\n";
                }
                else {
                    int damage = Math.max(10,player.getHealth() / 2);
                    player.takeDamage(damage);
                    messages +=
                            "Your life force was reduced by " + damage + "\n";
                }
            }
        }
    }
}
