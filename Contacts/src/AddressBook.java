/*
 * AddressBook.java
 * 
 * Version:
 * $Id: AddressBook.java,v 1.3 2014/11/19 21:44:20 lxs8146 Exp $
 * Revisions:
 * $Log: AddressBook.java,v $
 * Revision 1.3 2014/11/19 21:44:20 lxs8146
 * Third revision: Added filter field implementation and comments
 * 
 * Revision 1.2 2014/11/19 05:51:41 lxs8146
 * Second revision: Completed implementation
 * 
 * Revision 1.1 2014/11/14 23:52:29 lxs8146
 * Initial commit
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class used for a GUI contact manager.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class AddressBook extends JPanel {

    /**
     * Data structures used to internally manage the data
     */
    private ArrayList<String> unsorted = new ArrayList<String>();
    private ArrayList<String> sorted = new ArrayList<String>();
    private ArrayList<String> unsortedFiltered;
    private ArrayList<String> sortedFiltered;
    private ArrayList<String> data;
    private String filter = "";
    private static String[] array = new String[0];

    /**
     * GUI components
     */
    private JPanel north = new JPanel();
    private JPanel east = new JPanel();
    private JPanel south = new JPanel();
    private JPanel center = new JPanel();
    private JTextField filterBox = new JTextField(50);
    private JCheckBox sortBox = new JCheckBox("Sort");
    private JTextField textBox = new JTextField(40);
    private JButton update = new JButton("Update Entry");
    private JList<String> list = new JList<String>();
    private static JMenuBar bar = new JMenuBar();
    private static JMenu menu = new JMenu("Action");
    private static JMenuItem add = new JMenuItem("Add Contact");
    private static JMenuItem delete = new JMenuItem("Delete Contact");
    private static JMenuItem exit = new JMenuItem("Exit");

    /**
     * Read data in from Contacts, construct all components of the GUI, and add
     * necessary listeners.
     */
    public AddressBook() {
        for ( int i = 0 ; i < Contacts.peopleNames.length ; i++ ) {
            String entry = "";
            entry += Contacts.peopleNames[i];
            entry += ": ";
            entry += Contacts.peopleNumbers[i];
            unsorted.add(entry);
            sorted.add(entry);
        }
        Collections.sort(sorted);
        unsortedFiltered = ListFilter.filter(unsorted,filter);
        sortedFiltered = ListFilter.filter(sorted,filter);
        data = unsortedFiltered;
        setLayout(new BorderLayout());
        east.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());
        filterBox.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ( e.getKeyChar() != '\b' )
                    filter += e.getKeyChar();
                else
                    filter = filter.substring(0,filter.length() - 1);
                unsortedFiltered = ListFilter.filter(unsorted,filter);
                sortedFiltered = ListFilter.filter(sorted,filter);
                list.setListData(data.toArray(array));
            }
        });
        north.add(filterBox);
        sortBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( sortBox.isSelected() )
                    data = sortedFiltered;
                else
                    data = unsortedFiltered;
                list.setListData(data.toArray(array));
            }
        });
        east.add(sortBox);
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( list.getSelectedIndex() != -1 ) {
                    String s = list.getSelectedValue();
                    unsorted.set(unsorted.indexOf(s),textBox.getText());
                    sorted.set(sorted.indexOf(s),textBox.getText());
                    Collections.sort(sorted);
                    unsortedFiltered = ListFilter.filter(unsorted,filter);
                    sortedFiltered = ListFilter.filter(sorted,filter);
                    list.setListData(data.toArray(array));
                }
            }
        });
        south.add(textBox);
        south.add(update);
        list.setListData(data.toArray(array));
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if ( list.getSelectedIndex() != -1 ) {
                    textBox.setEnabled(false);
                    textBox.setText(list.getSelectedValue());
                    textBox.setEnabled(true);
                }
            }
        });
        center.add(new JScrollPane(list));
        add(north,BorderLayout.NORTH);
        add(east,BorderLayout.EAST);
        add(south,BorderLayout.SOUTH);
        add(center,BorderLayout.CENTER);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s =
                        JOptionPane.showInputDialog("Enter a new " + "contact");
                unsorted.add(s);
                sorted.add(s);
                Collections.sort(sorted);
                unsortedFiltered = ListFilter.filter(unsorted,filter);
                sortedFiltered = ListFilter.filter(sorted,filter);
                list.setListData(data.toArray(array));
            }
        });
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( list.getSelectedIndex() != -1 ) {
                    String s = list.getSelectedValue();
                    unsorted.remove(s);
                    sorted.remove(s);
                    unsortedFiltered.remove(s);
                    sortedFiltered.remove(s);
                    list.setListData(data.toArray(array));
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(add);
        menu.add(delete);
        menu.add(exit);
        bar.add(menu);
    }

    /**
     * Create the GUI window and add components to it.
     * 
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Address Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AddressBook content = new AddressBook();
        frame.setContentPane(content);
        frame.setJMenuBar(bar);
        frame.pack();
        frame.setVisible(true);
    }
}
