package blackjack;

import java.util.*;

public class Deck{
	//Importing the logic from the cards file to create a deck from it
    public Cards[] deck;
    public int currentCardInd;

    //Actually creating the deck with an array and loop
    public Deck(){
        deck = new Cards[52]; //Due to 0 indexation, the array is of size 52
        currentCardInd = 0;

        //Assign the all the cards within the array
        int index = 0;
        for (String suit : Cards.suits){
            for (String rank : Cards.ranks){
                deck[index++] = new Cards(rank, suit, index);
            }
        }
    }

    //This I actually never done before however, I'm sure Dr.Eicholtz will go over it in class
    public void shuffleDeck(){
        //Necessary step apparently; Converting the array into a list to make it more managable to shuffle
        List<Cards> tempList = Arrays.asList(deck);
        Collections.shuffle(tempList); //Making sure to shuffle the list

        deck = tempList.toArray(new Cards[52]); //And then converting it back into an array

    }

    //One can't play the game if you don't draw the card
    public Cards drawCards(){
        if (currentCardInd >= deck.length){
            System.out.println("There's no more cards!");
            return null;
        }

        return deck[currentCardInd++];
    }

    //Just thought of this, precaution so that it returns how many cards are left at all times
    public int remainingCardCount(){
        return deck.length - currentCardInd;
    }

    //And just like the method above, display the cards lol
    public void displayDeck(){
        for (Cards card : deck){
            System.out.println(card);
        }
    }
}