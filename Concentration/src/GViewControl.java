/*
 * GViewControl.java
 * 
 * Version:
 * $Id: GViewControl.java,v 1.6 2014/11/24 19:04:15 lxs8146 Exp $
 * Revisions:
 * $Log: GViewControl.java,v $
 * Revision 1.6  2014/11/24 19:04:15  lxs8146
 * Sixth revision: Closing main window now also closes cheat window
 *
 * Revision 1.5  2014/11/23 19:01:05  lxs8146
 * Fifth revision: Fixed a bug with the buttons
 *
 * Revision 1.4  2014/11/23 08:45:57  lxs8146
 * Fourth revision: Made buttons look better
 *
 * Revision 1.3 2014/11/23 08:40:48 lxs8146
 * Third revision: Fixed formatting
 * 
 * Revision 1.2 2014/11/23 08:37:07 lxs8146
 * Second revision: Completed implementation
 * 
 * Revision 1.1 2014/11/21 21:26:48 lxs8146
 * Initial commit
 */

import java.awt.event.*;
import java.awt.*;

import javax.swing.event.*;
import javax.swing.*;

import java.util.Observer;
import java.util.Observable;
import java.util.ArrayList;

/**
 * Class defintion for the graphical view and controller.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class GViewControl extends JFrame implements Observer {

    private final ConcentrationModel model;
    private JPanel content = new JPanel();
    private JPanel messageArea = new JPanel();
    private JPanel cardGrid = new JPanel(new GridLayout(4,4));
    private JPanel buttonArea = new JPanel();
    private JLabel message = new JLabel();
    private ArrayList<CardButton> cards = new ArrayList<CardButton>();
    private CheatFrame shortcut;

    private JButton reset = new JButton("Reset");
    private JButton cheat = new JButton("Cheat");
    private JButton undo = new JButton("Undo");

    /**
     * Construct a GViewControl object.
     * 
     * @param model The model for the view and controller.
     */
    public GViewControl(ConcentrationModel model) {
        super("Lucas Stefanic: Concentration Game");
        this.model = model;
        model.addObserver(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        message.setText("Moves: 0 Select the first card.");
        messageArea.add(message);
        for ( int i = 0 ; i < 16 ; i++ ) {
            final CardButton cb = new CardButton(i);
            if ( model.getCards().get(i) instanceof CardBack ) {
                cb.setBackground(Color.WHITE);
            }
            else {
                switch(model.getCards().get(i).getNumber()) {
                    case 0:
                        cb.setBackground(Color.DARK_GRAY);
                        cb.setText("0");
                        break;
                    case 1:
                        cb.setBackground(Color.BLUE);
                        cb.setText("1");
                        break;
                    case 2:
                        cb.setBackground(Color.GREEN);
                        cb.setText("2");
                        break;
                    case 3:
                        cb.setBackground(Color.CYAN);
                        cb.setText("3");
                        break;
                    case 4:
                        cb.setBackground(Color.MAGENTA);
                        cb.setText("4");
                        break;
                    case 5:
                        cb.setBackground(Color.RED);
                        cb.setText("5");
                        break;
                    case 6:
                        cb.setBackground(Color.ORANGE);
                        cb.setText("6");
                        break;
                    case 7:
                        cb.setBackground(Color.GRAY);
                        cb.setText("7");
                        break;
                }
            }
            cb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if ( cb.getBackground().equals(Color.WHITE) )
                        GViewControl.this.model.selectCard(cb.getPos());
                }
            });
            cardGrid.add(cb);
        }
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GViewControl.this.model.reset();
            }
        });
        for ( int i = 0 ; i < 16 ; i++ ) {
            CardButton cb2 = new CardButton(i);
            switch(model.cheat().get(i).getNumber()) {
                case 0:
                    cb2.setBackground(Color.DARK_GRAY);
                    cb2.setText("0");
                    break;
                case 1:
                    cb2.setBackground(Color.BLUE);
                    cb2.setText("1");
                    break;
                case 2:
                    cb2.setBackground(Color.GREEN);
                    cb2.setText("2");
                    break;
                case 3:
                    cb2.setBackground(Color.CYAN);
                    cb2.setText("3");
                    break;
                case 4:
                    cb2.setBackground(Color.MAGENTA);
                    cb2.setText("4");
                    break;
                case 5:
                    cb2.setBackground(Color.RED);
                    cb2.setText("5");
                    break;
                case 6:
                    cb2.setBackground(Color.ORANGE);
                    cb2.setText("6");
                    break;
                case 7:
                    cb2.setBackground(Color.GRAY);
                    cb2.setText("7");
                    break;
            }
            cards.add(cb2);
        }
        cheat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shortcut = new CheatFrame(cards,4);
                shortcut.setSize(getWidth() * 3 / 4,getHeight() * 3 / 4);
                shortcut.setVisible(true);
            }
        });
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GViewControl.this.model.undo();
            }
        });
        buttonArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonArea.add(reset);
        buttonArea.add(cheat);
        buttonArea.add(undo);
        content.setLayout(new BorderLayout());
        content.add(messageArea,BorderLayout.NORTH);
        content.add(cardGrid,BorderLayout.CENTER);
        content.add(buttonArea,BorderLayout.SOUTH);
        setContentPane(content);
        pack();
        setSize(500,500);
        setVisible(true);
    }

    /**
     * Update the window when the model indicates an update is required. Update
     * changes the color and string content of a CardButton based on the
     * CardFaces in the model, and it changes the text in the label based on the
     * model state.
     * 
     * @param t An Observable -- not used
     * @param o An Object -- not used
     */
    public void update(Observable t, Object o) {
        String newMessage = "";
        newMessage += "Moves: ";
        newMessage += model.getMoveCount();
        newMessage += " ";
        switch(model.howManyCardsUp()) {
            case 0:
                newMessage += "Select the first card.";
                break;
            case 1:
                newMessage += "Select the second card.";
                break;
            case 2:
                newMessage += "No Match: Undo or select a card.";
                break;
        }
        message.setText(newMessage);
        cardGrid.removeAll();
        for ( int i = 0 ; i < 16 ; i++ ) {
            final CardButton cb = new CardButton(i);
            if ( model.getCards().get(i) instanceof CardBack )
                cb.setBackground(Color.WHITE);
            else
                switch(model.getCards().get(i).getNumber()) {
                    case 0:
                        cb.setBackground(Color.DARK_GRAY);
                        cb.setText("0");
                        break;
                    case 1:
                        cb.setBackground(Color.BLUE);
                        cb.setText("1");
                        break;
                    case 2:
                        cb.setBackground(Color.GREEN);
                        cb.setText("2");
                        break;
                    case 3:
                        cb.setBackground(Color.CYAN);
                        cb.setText("3");
                        break;
                    case 4:
                        cb.setBackground(Color.MAGENTA);
                        cb.setText("4");
                        break;
                    case 5:
                        cb.setBackground(Color.RED);
                        cb.setText("5");
                        break;
                    case 6:
                        cb.setBackground(Color.ORANGE);
                        cb.setText("6");
                        break;
                    case 7:
                        cb.setBackground(Color.GRAY);
                        cb.setText("7");
                        break;
                }
            int numListeners = cb.getActionListeners().length;
            for ( int j = 0 ; j < numListeners ; j++ )
                cb.removeActionListener(cb.getActionListeners()[i]);
            cb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if ( cb.getBackground().equals(Color.WHITE) )
                        GViewControl.this.model.selectCard(cb.getPos());
                }
            });
            cardGrid.add(cb);
        }
        cardGrid.validate();
        cards.clear();
        for ( int i = 0 ; i < 16 ; i++ ) {
            CardButton cb2 = new CardButton(i);
            switch(model.cheat().get(i).getNumber()) {
                case 0:
                    cb2.setBackground(Color.DARK_GRAY);
                    cb2.setText("0");
                    break;
                case 1:
                    cb2.setBackground(Color.BLUE);
                    cb2.setText("1");
                    break;
                case 2:
                    cb2.setBackground(Color.GREEN);
                    cb2.setText("2");
                    break;
                case 3:
                    cb2.setBackground(Color.CYAN);
                    cb2.setText("3");
                    break;
                case 4:
                    cb2.setBackground(Color.MAGENTA);
                    cb2.setText("4");
                    break;
                case 5:
                    cb2.setBackground(Color.RED);
                    cb2.setText("5");
                    break;
                case 6:
                    cb2.setBackground(Color.ORANGE);
                    cb2.setText("6");
                    break;
                case 7:
                    cb2.setBackground(Color.GRAY);
                    cb2.setText("7");
                    break;
            }
            cards.add(cb2);
        }
    }

    /**
     * The main method used to play a game.
     * 
     * @param args Command line arguments -- unused
     */
    public static void main(String[] args) {
        GViewControl game = new GViewControl(new ConcentrationModel());
    }
}
