package blackjack;

import java.util.Scanner;

public class Game{
    //Implementing the necessary variables for both the player and the AI
    public Deck deck;
    
    public Cards[] playerCards;
    public int playerTotal;

    public Cards[] houseCards; //I named it to house for now in case I want to add more players
    public int houseTotal;

    public Game(){
        deck = new Deck(); //Creating a new deck every instance the game runs

        //I wouldn't want both the house or the player to have more than 10 cards
        playerCards = new Cards[10];
        houseCards = new Cards[10];

    }

    //If there's an ace in their hand, ask the user what value they would want that ace to be
    public int askForAceValue(){
        Scanner terminal = new Scanner(System.in);
        int aceValue = 0;

        while (aceValue != 1 && aceValue != 11){
            System.out.print("What would you like your ace value to be? (Choose 1 or 11): ");
            aceValue = terminal.nextInt();

            //After the user types their choice (Include an invalid net and make them re type if their choice isn't 1 or 11)
            if (aceValue != 1 && aceValue != 11){
                System.out.print("Invalid number! Please choose 1 or 11: ");
            }
        }

        return aceValue;
    }

    public int handValue(Cards[] hand){
        int total = 0;
        
        //They will only be able to choose twice.
        int aceCount = 0; //I'm glad GPT pointed this out because the ace can be 11 or 1, might have a prompt to make the user choose whether they want the ace to be 1 or 11
        //Scanner terminal = new Scanner(System.in);

        for (Cards card : hand){
            //Just in case the card DOES equal null, hehe programming
            if (card != null){
                total += card.getCardNum();

            }

            if (card != null && card.getRank().equals("Ace")){
                aceCount++;
            }
        }

        //Ask the user again to give them a second chance
        while (aceCount > 0){
            int aceValue = askForAceValue();
            
            //In the case that Ace was already counted as 1, we reset it
            total += aceValue - 1;
            aceCount--;
        
        }
        
        return total;
    }

    //Beginning of the game
    public void gameStart(){
        //I took my first break here xd
    }

    public static void main(String[] args) {
        

        
    }
}