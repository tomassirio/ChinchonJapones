public class Card {
    public final static int SPADES = 0;
    public final static int HEARTS = 1;
    public final static int DIAMONDS = 2;
    public final static int CLUBS = 3;
    public final static int JOKER = 4;

    public final static int ACE = 1;
    public final static int JACK = 11;
    public final static int QUEEN = 12;
    public final static int KING = 13;


    private final int suit;
    private final int value;

    public Card(){
        suit = JOKER;
        value = 1;
    }

    public Card(int newValue, int newSuit){
        if(newSuit != SPADES && newSuit != HEARTS && newSuit != DIAMONDS && newSuit != CLUBS && newSuit != JOKER)
            throw new IllegalArgumentException("Illegal playing card Suit");
        if(newSuit != JOKER && (newValue < 1 || newValue > 13))
            throw new IllegalArgumentException("Illegal playing card Value");

        value = newValue;
        suit = newSuit;
    }

    public int getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }

    public String getSuitAsString(){
        switch(suit){
            case SPADES: return "Spades";
            case HEARTS: return "Hearts";
            case DIAMONDS: return "Diamonds";
            case CLUBS: return "Clubs";
            default: return "Joker";
        }
    }

    public String getValueAsString(){
        if(suit == JOKER)
            return "" + value;
        else{
            switch(value){
                case 1: return "Ace";
                case 2: return "2";
                case 3: return "3";
                case 4: return "4";
                case 5: return "5";
                case 6: return "6";
                case 7: return "7";
                case 8: return "8";
                case 9: return "9";
                case 10: return "10";
                case 11: return "Jack";
                case 12: return "Queen";
                default: return "King";
            }
        }
    }

    public String toString(){
        if(suit == JOKER){
            if(value == 1)
                return "Joker";
            else
                return "Joker #" + value;
        }
        else
            return getValueAsString() + " of " + getSuitAsString();
    }

    public String resumedToString(){
        if(suit == JOKER)
            return "JOK";
        else{
            switch(suit){
                case 0: return value + " S";
                case 1: return value + " H";
                case 2: return value + " D";
                default: return value + " C";
            }
        }
    }

    public void printCard(){
        System.out.println(" ---------"); //10 posiciones
        if(value >= 10)
            System.out.println("| " + value + "      |");
        else
            System.out.println("| " + value + "       |");
        System.out.println("|         |");
        System.out.println("|         |");
        if(value >= 10)
            System.out.println("|   " + resumedToString() + "  |");

        else
            System.out.println("|   " + resumedToString() + "   |");
        System.out.println("|         |");
        System.out.println("|         |");
        if(value >= 10)
            System.out.println("|      " + value + " |");
        else
            System.out.println("|       " + value + " |");
        System.out.println(" ---------"); //10 posiciones

    }
}
