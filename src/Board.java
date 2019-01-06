import java.util.ArrayList;

public class Board {

    String[] board;

    Deck commonDeck;
    Hand playerHand;
    ArrayList<PlayerStack> playerStacks;
    ArrayList<PlayerStack> computerStacks;
    ArrayList<Card> playerDeck;
    ArrayList<Card> computerDeck;
    ArrayList<GameStack> games;

    public Board(Deck commonDeck, Hand playerHand, ArrayList<PlayerStack> playerStacks, ArrayList<PlayerStack> computerStacks, ArrayList<Card> playerDeck, ArrayList<Card> computerDeck, ArrayList<GameStack> games){
        this.commonDeck = commonDeck;
        this.playerHand = playerHand;
        this.playerStacks = playerStacks;
        this.computerStacks = computerStacks;
        this.playerDeck = playerDeck;
        this.computerDeck = computerDeck;
        this.games = games;

        board[0] =
    }

    public void printBoard(){

    }

    public String printComputerStacks(){
        for(int i = 0; i < computerStacks.size(); i++){
            System.out.println();
        }
    }
}
