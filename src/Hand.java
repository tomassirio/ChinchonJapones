import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;

    public Hand(){
        hand = new ArrayList<Card>();
    }

    public void Clear(){
        hand.clear();
    }

    public void removeCard(Card c){
        hand.remove(c);
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public void getCard(int position){
        if(position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: " + position);
        hand.remove(position);
    }

    public int getHandSize(){
        return hand.size();
    }

    public Card checkCard(int i){
        return hand.get(i);
    }

    public void sortBySuit(){
        ArrayList<Card> newHand = new ArrayList<Card>();
        while(hand.size() > 0){
            int pos = 0;
            Card c = hand.get(0);
            for(int i = 1; i < hand.size(); i++){
                Card c1 = hand.get(i);
                if(c1.getSuit() < c.getSuit() || (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue())){
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }

    public void printHand(){
        for(int i = 0; i < hand.size(); i++){
            hand.get(i).printCard();
        }
    }




}
