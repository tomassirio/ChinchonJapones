import java.util.*;

public class GameStack {

    private ArrayList<Card> stack;
    private int topCard;

    public GameStack(){
        stack = new ArrayList<>();
    }

    public void addCard(Card c){
        if(stack.isEmpty()){
            stack.add(c);
            topCard = 1;
        }else{
            if(c.getSuit() != Card.JOKER){
                if(c.getValue() != topCard +1)
                    throw new IllegalArgumentException("Card´s value should be superior than the one from the stack");
                else{
                    stack.add(c);
                    topCard++;
                }

            }else{
                stack.add(c);
                topCard++;
            }
        stack.add(c);
        }
    }

    public int getTopCard(){
        return topCard;
    }
}
