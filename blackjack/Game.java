package blackjack;

import java.util.Scanner;

public class Game{
    //Implementing the necessary variables for both the player and the AI
    public static Deck deck;
    
    public static Cards[] playerCards;
    public static int playerTotal;

    public static Cards[] houseCards; //I named it to house for now in case I want to add more players
    public static int houseTotal;

    public Game(){
        deck = new Deck(); //Creating a new deck every instance the game runs
        deck.shuffleDeck();

        //I wouldn't want both the house or the player to have more than 10 cards
        playerCards = new Cards[10];
        houseCards = new Cards[10];

    }

    //If there's an ace in their hand, ask the user what value they would want that ace to be
    public static int askForAceValue(){
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

    public static int handValue(Cards[] hand, boolean isPlayer){
        int total = 0;
        
        //They will only be able to choose once.
        int aceCount = 0; //I'm glad GPT pointed this out because the ace can be 11 or 1, might have a prompt to make the user choose whether they want the ace to be 1 or 11
        //Scanner terminal = new Scanner(System.in);

        for (Cards card : hand){
            //Just in case the card DOES equal null, hehe programming
            if (card != null){
                total += card.getCardNum();
                if (card != null && card.getRank().equals("Ace")){
                    aceCount++;
                }
            }
            
        }

        //Ask the user again to give them a second chance
        while (aceCount > 0){
            int aceValue;
            
            //Since a player shouldn't be able to choose the house's ace value, I'm implementing a boolean
            if (isPlayer){
                aceValue = askForAceValue();
            }
            else {
                aceValue = (total + 11 <= 21) ? 11 : 1;
            }

            //In the case that Ace was already counted as 1, we reset it
            if (aceValue == 1 && total + 10 <= 21){
                total += 0;
            }
            else if (aceValue == 1){
                total -= 10;
            }

            aceCount--;
        }
        
        return total;
    }
    
    public static void displayWelcomeMessageVsHouse(){
            System.out.println("===================================");
            System.out.println("|        WELCOME TO BLACKJACK      |");
            System.out.println("|        vs The House 🃏           |");
            System.out.println("===================================\n");
        }
        //Hehe... I will include MANY bots with DIFFERENT AI's in the future
        /*
         * ==================================
         * PLACE HOLDER
         * ==================================
         */
    
        //I want to make it more visually appealing :)
    public static void displayCard(Cards card){
        String suit = card.getSuit();
        String rank = card.getRank();

        String cardTop = "┌─────────┐";
        String cardMid = String.format("│  %2s  %s  │", rank, getSuitSign(suit));
        String cardBot = "└─────────┘";

        System.out.println(cardTop);
        System.out.println(cardMid);
        System.out.println(cardBot);
    }

    public static String getSuitSign(String suit){
        return switch (suit){
            case "Hearts" -> "♥";
            case "Diamonds" -> "♦";
            case "Clubs" -> "♣";
            case "Spades" -> "♠";
            default -> "?";
        };
    
    }

    //Beginning of the game
    public static void gameStart(){
        //I took my first break here xd
        displayTurnString("Player");
        playerCards[0] = deck.drawCards();
        playerCards[1] = deck.drawCards();
        playerTotal = handValue(playerCards, true);
        displayPlayerHand();
        
        displayTurnString("House");
        houseCards[0] = deck.drawCards();
        houseCards[1] = deck.drawCards();
        houseTotal = handValue(houseCards, false);
        displayHouseHand(false);
        
        playerTurn();
        determineWinner();
    }
    
    public static void displayPlayerHand(){
        System.out.println("\n🃏 Your Hand:");
    
        //Another safety net for the case that the card is null
        for (Cards card : playerCards){
            if (card != null){
                displayCard(card);
            }
        }
        System.out.println("Your hand: " + playerTotal);
    
    }
    
    @SuppressWarnings("ConvertToStringSwitch")
    public static void playerTurn(){
        Scanner terminal = new Scanner(System.in);
    
        while (true){
            System.out.println("\n------------------------------------");
            System.out.println("Your hand is: " + playerTotal);
            System.out.println("------------------------------------");
            
            String userChoice = getPlayerChoice();
    
            //Simple hit logic
            if (userChoice.equals("1")){
                for (int i = 0; i < playerCards.length; i++){
                    if (playerCards[i] == null){
                        playerCards[i] = deck.drawCards();
                        break;
                    }
                }
    
                //Recalculate the hand and ask the user for ace value
                playerTotal = handValue(playerCards, true);
    
            }
            else if (userChoice.equals("2")){
                //Turn ends immediately so :p
                displayHouseHand(true); //Reveal the house's hand after the player stands
                return;
            }
            else {
                System.out.println("Invalid choice, please enter 1 or 2");
            }
        }
    }
    
    //For this section I will be taking heavy inspiration from the GPT's guidance because I don't remember the last time I coded AI in the big hava
    public static void displayHouseHand(boolean revealAllCards){
        System.out.println("\n🎭 House's Hand:");

        for (int i = 0; i < houseCards.length; i++){
            if (houseCards[i] != null){
                if (i == 1 && !revealAllCards){
                    System.out.println("┌────────┐");
                    System.out.println("|  ???   |");
                    System.out.println("└────────┘");
                }
                else {
                    displayCard(houseCards[i]);
                }
            }
        }

        if (revealAllCards){
            System.out.println("The house has: " + houseTotal);
        }
    }
    
    //A game is never complete without it's winner
    public static void determineWinner(){
        System.out.println("\n============================");

        //Player over 21
        //Bad Gambling Luck :p
        if (playerTotal > 21){
            System.out.println("💥 BUST! You went over 21. The House always wins...");
        }
        //House over 21
        else if (houseTotal > 21){
            System.out.println("🎉 LuckyBot busts! YOU WIN! 🏆");
        }
        //Player wins by majority
        else if (playerTotal > houseTotal){
            System.out.println("🎊 Wow! You have more than the House! YOU WIN!");
        }
        //Player loses by majority
        else if (playerTotal < houseTotal){
            System.out.println("😞 The House wins! Well, you can't expect to win 'em all...");
        }
        //Incredibly rare but it happens sometimes
        else {
            System.out.println("🤝 It's a tie!");
        }

        System.out.println("============================\n");
    }
    
    public static void displayTurnString(String playerName){
        System.out.println("\n------------------------------------");
        System.out.println(playerName + "'s Turn...");
        System.out.println("------------------------------------\n");
    }
    
    public static String getPlayerChoice(){
        Scanner terminal = new Scanner(System.in);

        System.out.println("\n🔹 What do you want to do?");
        System.out.println("  [1] Hit (Draw a card)");
        System.out.println("  [2] Stand (End your turn)");
        System.out.print("👉 Enter your choice: ");
        return terminal.nextLine();
    }

    public static void main(String[] args) {
        Scanner terminal = new Scanner(System.in);
        displayWelcomeMessageVsHouse();
        
        while (true){
            Game game = new Game();
            gameStart();

            //Ask the user if they want to play again
            System.out.println();
            System.out.println("\n🔄 Do you want to play again? (yes/no)");
            System.out.print("👉 Enter your choice: ");
            String userChoice = terminal.nextLine().toLowerCase();
            
            if (userChoice.equals("yes")){
                System.out.println("\n🎶 Hey! Back again for another try!");
            }
            else if (userChoice.equals("no")){
                System.out.println("\n👋 Thanks for playing! 99% of gamblers quit before they hit big.");
                break;
            }

        }
    }

}