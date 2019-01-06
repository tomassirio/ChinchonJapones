import java.util.ArrayList;

public class PlayerStack {

    ArrayList<Card> stack;

    public PlayerStack(){
        stack = new ArrayList<>();
    }

    public void addCard(Card c){
        stack.add(c);
    }

    public int getStackSize(){
        return stack.size();
    }

    public Card getTopCard(){
        if(stack.isEmpty())
            throw new IllegalArgumentException("No cards on this stack");
        return stack.get(stack.size()-1);
    }

    public void removeTopCard(){
        if(stack.isEmpty())
            throw new IllegalArgumentException("No cards on this stack");
        stack.remove(stack.size()-1);
    }

    public void printStack(){
        if(stack.isEmpty()){
            System.out.println(" ---------"); //10 posiciones
            System.out.println("|         |");
            System.out.println("|         |");
            System.out.println("|         |");
            System.out.println("|No Stack |");
            System.out.println("|         |");
            System.out.println("|         |");
            System.out.println(" ---------"); //10 posiciones
        }else if(stack.size() == 1){
            stack.get(0).printCard();
        }else{
            for(int i = 0; i < stack.size()-1; i++){
                System.out.println(" ---------"); //10 posiciones
                if(stack.get(i).getValue() >= 10)
                    System.out.println("| " + stack.get(i).resumedToString() + "    |");
                else
                    System.out.println("| " + stack.get(i).resumedToString() + "     |");
                System.out.println("|         |");
            }
            stack.get(stack.size()-1).printCard();
        }
    }
}
