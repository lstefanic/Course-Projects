/*
 * Cellar.java
 * 
 * Version:
 * $Id: Cellar.java,v 1.4 2014/11/30 07:30:30 lxs8146 Exp $
 * Revisions:
 * $Log: Cellar.java,v $
 * Revision 1.4  2014/11/30 07:30:30  lxs8146
 * Fourth revision: Reformatted code and added javadocs
 *
 * Revision 1.3 2014/11/30 05:30:06 lxs8146
 * Third revision: Fixed bugs and improved GUI appearance
 * 
 * Revision 1.2 2014/11/27 19:34:12 lxs8146
 * Second revision: Did a crapload of implementation
 * 
 * Revision 1.1 2014/11/14 23:54:35 lxs8146
 * Initial commit
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

import java.io.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Class definition for the dungeon crawler GUI.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Cellar extends JFrame implements Observer {

    private CellarModel model;
    private JPanel content = new JPanel(new GridLayout(3,1,0,20));
    private JPanel playerWrap = new JPanel(new BorderLayout());
    private JPanel player = new JPanel(new GridLayout(2,2));
    private JLabel status = new JLabel("Your Status",SwingConstants.CENTER);
    private JList<String> info = new JList<String>();
    private JLabel stats = new JLabel("Stats",SwingConstants.CENTER);
    private JPanel inventory = new JPanel();
    private JLabel playerItems = new JLabel("Items",SwingConstants.CENTER);
    private JCheckBox sword = new JCheckBox("Sword");
    private JList<Item> others = new JList<Item>();
    private JButton drop = new JButton("Drop Item");
    private JPanel roomWrap = new JPanel(new BorderLayout());
    private JPanel room = new JPanel(new GridLayout(2,3));
    private JLabel current = new JLabel("Room 0",SwingConstants.CENTER);
    private JPanel hallPanel = new JPanel();
    private JLabel halls = new JLabel("Halls",SwingConstants.CENTER);
    private JList<Hallway> hallways = new JList<Hallway>();
    private JButton moveDown = new JButton("Move Down");
    private JPanel itemPanel = new JPanel();
    private JLabel roomItems = new JLabel("Items",SwingConstants.CENTER);
    private JList<Item> protections = new JList<Item>();
    private JButton pickup = new JButton("Pick Up");
    private JPanel present = new JPanel();
    private JLabel inRoom = new JLabel("Present in room",SwingConstants.CENTER);
    private JCheckBox monster = new JCheckBox("Monster");
    private JCheckBox sword2 = new JCheckBox("Sword");
    private JCheckBox amulet = new JCheckBox("Amulet");
    private JPanel messageWrap = new JPanel(new BorderLayout());
    private JPanel messageBox = new JPanel();
    private JLabel messages = new JLabel("Messages",SwingConstants.CENTER);
    private JTextArea output = new JTextArea(10,75);

    /**
     * Construct the GUI and some of the content.
     * 
     * @param file The configuration file passed in from the main method
     */
    public Cellar(File file) {
        super("Lucas Stefanic (lxs8146)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        model = new CellarModel(file);
        model.addObserver(this);
        stats.setLabelFor(info);
        sword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sword.setSelected(!sword.isSelected());
            }
        });
        drop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( others.getSelectedIndex() != -1 )
                    model.dropItem(others.getSelectedValue());
                else
                    JOptionPane.showMessageDialog(null,
                            "Select something to drop first");
            }
        });
        inventory.add(sword);
        inventory.add(others);
        inventory.add(drop);
        playerItems.setLabelFor(inventory);
        player.add(stats);
        player.add(playerItems);
        player.add(info);
        player.add(inventory);
        player.setBorder(BorderFactory.createLineBorder(Color.black));
        status.setFont(new Font("SansSerif",Font.BOLD,20));
        status.setLabelFor(player);
        playerWrap.add(status,BorderLayout.NORTH);
        playerWrap.add(player,BorderLayout.CENTER);
        moveDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( hallways.getSelectedIndex() != -1 )
                    model.moveDownHall(hallways.getSelectedValue());
                else
                    JOptionPane.showMessageDialog(null,
                            "Select a hall to move down first");
            }
        });
        hallPanel.add(hallways);
        hallPanel.add(moveDown);
        halls.setLabelFor(hallPanel);
        pickup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( protections.getSelectedIndex() != -1 )
                    model.pickupItem(protections.getSelectedValue());
                else
                    JOptionPane.showMessageDialog(null,
                            "Select something to pick up first");
            }
        });
        itemPanel.add(protections);
        itemPanel.add(pickup);
        roomItems.setLabelFor(itemPanel);
        monster.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                monster.setSelected(!monster.isSelected());
            }
        });
        sword2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sword2.setSelected(!sword2.isSelected());
            }
        });
        amulet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                amulet.setSelected(!amulet.isSelected());
            }
        });
        present.add(monster);
        present.add(sword2);
        present.add(amulet);
        inRoom.setLabelFor(present);
        room.add(halls);
        room.add(roomItems);
        room.add(inRoom);
        room.add(hallPanel);
        room.add(itemPanel);
        room.add(present);
        room.setBorder(BorderFactory.createLineBorder(Color.black));
        current.setFont(new Font("SansSerif",Font.BOLD,20));
        current.setLabelFor(room);
        roomWrap.add(current,BorderLayout.NORTH);
        roomWrap.add(room,BorderLayout.CENTER);
        output.setEditable(false);
        messageBox.add(new JScrollPane(output));
        messages.setFont(new Font("SansSerif",Font.BOLD,20));
        messages.setLabelFor(messageBox);
        messageWrap.add(messages,BorderLayout.NORTH);
        messageWrap.add(messageBox,BorderLayout.CENTER);
        content.add(playerWrap);
        content.add(roomWrap);
        content.add(messageWrap);
        setContentPane(content);
        update(new Observable(),new Object());
        pack();
        setVisible(true);
    }

    /**
     * Change the content of the GUI based on the current state of the game.
     * 
     * @param t An Observable object -- unused
     * @param o An Object -- unused
     */
    public void update(Observable t, Object o) {
        drop.setEnabled(true);
        moveDown.setEnabled(true);
        pickup.setEnabled(true);
        sword.setEnabled(true);
        monster.setEnabled(true);
        sword2.setEnabled(true);
        amulet.setEnabled(true);
        String[] infoData = new String[2];
        infoData[0] =
                "Experience level: "
                        + Integer.toString(model.getPlayer().getLevel());
        infoData[1] =
                "Life force: "
                        + Integer.toString(model.getPlayer().getHealth());
        info.setListData(infoData);
        sword.setSelected(model.getPlayer().hasSword());
        others.setListData(model.getPlayer().getItems().toArray(new Item[0]));
        current.setText(model.currentRoom().toString());
        hallways.setListData(model.currentRoom().getHalls()
                .toArray(new Hallway[0]));
        protections.setListData(model.currentRoom().getItems()
                .toArray(new Item[0]));
        monster.setSelected(model.getMonster() != null
                && model.getMonster().isAlive()
                && model.currentRoom().equals(model.getMonster().getRoom()));
        sword2.setSelected(model.currentRoom().hasSword());
        amulet.setSelected(model.currentRoom().hasAmulet());
        output.setText(model.getMessages());
        if ( model.getPlayer().hasAmulet() ) {
            output.append("You picked up the Amulet!\nYou win!");
            drop.setEnabled(false);
            moveDown.setEnabled(false);
            pickup.setEnabled(false);
            sword.setEnabled(false);
            monster.setEnabled(false);
            sword2.setEnabled(false);
            amulet.setEnabled(false);
        }
        else if ( model.getPlayer().isDead() ) {
            output.append("You lost all your health and died.\nYou lose :(");
            drop.setEnabled(false);
            moveDown.setEnabled(false);
            pickup.setEnabled(false);
            sword.setEnabled(false);
            monster.setEnabled(false);
            sword2.setEnabled(false);
            amulet.setEnabled(false);
        }
        content.validate();
    }

    /**
     * Start a new dungeon crawler game
     * 
     * @param args Contains only of the name of the configuration file for the
     * game -- invalid otherwise
     */
    public static void main(String[] args) {
        if ( args.length != 1 ) {
            System.err.println("Usage: java Cellar config_file_name");
            return;
        }
        File input = new File(args[0]);
        if ( !input.exists() ) {
            System.err.println("Usage: java Cellar config_file_name");
            return;
        }
        Cellar game = new Cellar(input);
    }
}
