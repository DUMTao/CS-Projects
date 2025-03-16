package blackjack;

//Simply created a class so that I would handle separating the such
public class Cards{
    public String rank;
    public String suit;
    public int value;

    //I'll be using Arrays to store the information of all the variables above
    public static String[] ranks = {
        "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
    };
    public static String[] suits = {
        "Hearts", "Clubs", "Diamonds", "Spades"
    };
    public static int[] cardNumber = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};


    public Cards(String rank, String suit, int value){
        this.rank = rank;
        this.suit = suit;
        this.value = getCardNumber(rank);
    }

    public int getCardNumber(String rank){
        for (int i = 0; i < ranks.length; i++){
            if (ranks[i].equals(rank)){
                return cardNumber[i];
            }
        }
        return 0; //This should never happen but I still need a return statement lol
    }

    //Regular methods to return the singular cards
    public String getRank(){
        return rank;
    }

    public String getSuit(){
        return suit;
    }
    public int getCardNum(){
        return value;
    }

    @Override
    public String toString(){
        return rank + " of " + suit + " (" + value + " out of 21)";
    }
}