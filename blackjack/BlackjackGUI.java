package blackjack;

import javax.swing.*;

//imports

public class BlackjackGUI {
    public static Game game;
    public static JLabel playerCardsLabel;
    public static JLabel houseCardsLabel;
    public static JLabel messageLabel;
    
    public static void main(String[] args) {
        //Create the window for the game
        JFrame frame = new JFrame("Gambling addiction simulator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); //Set the size of the window

        //Create a panel for the components
        JPanel panel = new JPanel();
        frame.add(panel);

        //Welcome label
        JLabel welcome = new JLabel("Welcome to Blackjack!");
        panel.add(welcome);

        //Player and House hand labels
        playerCardsLabel = new JLabel("Player's Hand: ");
        houseCardsLabel = new JLabel("House's Hand: ");
        playerCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        houseCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(playerCardsLabel);
        panel.add(houseCardsLabel);

        //Player actions for Hit and Stand
        messageLabel = new JLabel("Click 'Hit' to draw a card or 'Stand' to end your turn.");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(messageLabel);

        //Add buttons for the player actions
        JButton hitB = new JButton("Hit");
        JButton standB = new JButton("Stand");
        hitB.setAlignmentX(Component.CENTER_ALIGNMENT);
        standB.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(hitB);
        panel.add(standB);

        //Create the game
        game = new Game();
        //updatehands();
        // --------------------------------------------

        //Add action listeners for buttons
        hitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Game.playerTurn();
                
                if (Game.playerTotal > 21){
                    messageLabel.setText("💥 BUST! You went over 21. The House always wins...");
                    disableButtons(hitB, standB);
                }
            }

        });
    
        standB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Game.displayHouseHand(true);
                Game.determineWinner();
                updateHands();
                messageLabel.setText("All bets locked.");
                disableButtons(hitB, standB);
            }
        });
    
        frame.setVisible(true);
    }

    //Update the labels to display the current hands
    public static void updateHands(){
        playerCardsLabel.setText("Player's Hand: " + formatHand(Game.playerCards) + " (Total: " + Game.playerTotal + ")");
    }

    public static String formatHand(Cards[] hand){

    }

    public static void disableButtons(JButton... buttons){
        
    }

}
