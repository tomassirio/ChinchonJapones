import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Chinchon {

    private static final boolean PLAYER_TURN = true;
    private static final boolean COMPUTER_TURN = false;

    public static void main(String[] args){

        int gamesPlayed = 0;
        int gamesWon = 0;

        boolean playAgain = false;

        do{
            gamesPlayed++;
            play();
            System.out.println("Play again? ");
            //playAgain = TextIO.getInBoolean();
        }while (playAgain);
    }

    private static void play(){
        Scanner scan = new Scanner(System.in);

        Deck commonDeck = new Deck();
        Deck goalDeck = new Deck();

        commonDeck.shuffle();
        goalDeck.shuffle();

        boolean turn;
        turn = Math.random() < 0.5;

        ArrayList<Card> playerDeck = new ArrayList<>();
        ArrayList<Card> computerDeck = new ArrayList<>();

        Hand playerHand = new Hand();
        Hand computerHand = new Hand();

        List<PlayerStack> playerStack = new ArrayList<>();
        List<PlayerStack> computerStack = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            PlayerStack ps1 = new PlayerStack();
            PlayerStack ps2 = new PlayerStack();

            playerStack.add(ps1);
            computerStack.add(ps2);
        }

        List<GameStack> games = new ArrayList<>();

        //Reparte el mazo de juego a cada jugador
        while(goalDeck.cardsLeft() > 0){
            playerDeck.add(goalDeck.dealCard());
            computerDeck.add(goalDeck.dealCard());
        }

        //Entrega la primer mano a cada jugador
        for(int i = 0; i <= 3; i++){
            playerHand.addCard(commonDeck.dealCard());
            computerHand.addCard(commonDeck.dealCard());
        }

        //Empieza el juego y termina cuando alguno de los dos jugadores se queda sin cartas en su Deck
        while(playerDeck.size() > 0 && computerDeck.size() > 0){
            //Turno de la computadora
            while(turn == COMPUTER_TURN){
                System.out.println("Computer´s Turn: ");

                fillHand(computerHand, commonDeck);
                checkForAces(computerHand, games);

                for(int i = 0; i < games.size() - 1; i++){
                    if(computerDeck.get(computerDeck.size()-1).getValue() + 1 == games.get(i).getTopCard()  || computerDeck.get(computerDeck.size()-1).getSuit() == Card.JOKER){ //Verifica si la primer carta del deck es mayor por 1 que alguna de las de stacks
                        games.get(i).addCard(computerDeck.get(computerDeck.size()-1));
                        computerDeck.remove(computerDeck.size()-1);
                    }
                }

                for(int i = 0; i < computerHand.getHandSize() ; i++){
                    for(int j = 0; j < games.size() - 1; j++){
                        if(computerHand.checkCard(i).getValue() == games.get(j).getTopCard() +1 || computerHand.checkCard(i).getSuit() == Card.JOKER){
                            games.get(j).addCard(computerHand.checkCard(i));
                            computerHand.checkCard(i);
                        }
                    }
                }

                Card card = computerHand.checkCard(0);
                for(int i = 0; i < computerHand.getHandSize(); i++){
                    if(computerHand.checkCard(i).getValue() > card.getValue())
                        card = computerHand.checkCard(i);
                }

                boolean emptyStacks = false;
                int maxTopCard = 0;
                int position = 0;
                for(int i = 0; i < computerStack.size(); i++) {
                    if (computerStack.get(i).getStackSize() == 0){
                        emptyStacks = true;
                        computerStack.get(i).addCard(card);
                        computerHand.removeCard(card);
                    }else {
                        if (computerStack.get(i).getTopCard().getValue() > maxTopCard) {
                            maxTopCard = computerStack.get(i).getTopCard().getValue();
                            position = i;
                        }
                    }

                    if(!emptyStacks){
                        computerStack.get(position).addCard(card);
                        computerHand.removeCard(card);
                    }
                }
                turn = PLAYER_TURN;
            }

            //Turno del jugador
            while(turn == PLAYER_TURN){
                System.out.println("Player´s Turn: ");
                int option = 0;
                //La mano del jugador en cada turno tiene que tener 4 cartas
                fillHand(playerHand, commonDeck);
                playerHand.printHand();
                //Los aces deben bajarse automaticamente
                checkForAces(playerHand, games);

                while(option != 4){
                    System.out.println("1. Play Card to a Game Stack");
                    System.out.println("2. Play Card from your deck");
                    System.out.println("3. Play card from one of your stacks");
                    System.out.println("4. Play one of your cards to one of your stacks (Pass)");
                    option = scan.nextInt();

                    int handOption = 0;
                    int gameStackOption = 0;
                    int playerStackOption = 0;

                    switch(option){
                        case 1:{

                            System.out.println("Which of your cards do you wanna play?");
                            for(int i = 0; i < playerHand.getHandSize(); i++){
                                System.out.println(i + ". " + playerHand.checkCard(i).toString());
                            }
                            handOption = scan.nextInt();

                            System.out.println("In which of the stacks do you want to play it?");
                            for(int i = 0; i < games.size(); i++){
                                System.out.println(i + ". " + games.get(i).getTopCard());
                            }
                            gameStackOption = scan.nextInt();

                            games.get(gameStackOption).addCard(playerHand.checkCard(handOption));
                            playerHand.getCard(handOption);
                            break;
                        }

                        case 2:{
                            System.out.println("In which of the stacks do you want to play it?");
                            for(int i = 0; i < games.size(); i++){
                                System.out.println(i + ". " + games.get(i).getTopCard());
                            }
                            gameStackOption = scan.nextInt();

                            games.get(gameStackOption).addCard(playerDeck.get(playerDeck.size()-1));
                            playerDeck.remove(playerDeck.size()-1);
                            break;
                        }

                        case 3:{
                            System.out.println("Which of your stacks do you wanna play from?");
                            for(int i = 0; i < playerStack.size(); i++){
                                System.out.println(i + ". "); playerStack.get(i).printStack();
                            }
                            playerStackOption = scan.nextInt();

                            System.out.println("In which of the stacks do you want to play it?");
                            for(int i = 0; i < games.size(); i++){
                                System.out.println(i + ". " + games.get(i).getTopCard());
                            }
                            gameStackOption = scan.nextInt();

                            games.get(gameStackOption).addCard(playerStack.get(playerStackOption).getTopCard());
                            playerStack.get(playerStackOption).removeTopCard();
                            break;
                        }

                        case 4:{
                            System.out.println("Which of your cards do you wanna play?");
                            for(int i = 0; i < playerHand.getHandSize(); i++){
                                System.out.println(i + ". " + playerHand.checkCard(i).toString());
                            }
                            handOption = scan.nextInt();

                            System.out.println("In which of your stacks do you want to play it?");
                            for(int i = 0; i < playerStack.size(); i++){
                                System.out.println(i + ". "); playerStack.get(i).printStack();
                            }
                            playerStackOption = scan.nextInt();

                            playerStack.get(playerStackOption).addCard(playerHand.checkCard(handOption));
                            playerHand.getCard(handOption);
                            break;
                        }
                    }
                }
                turn = COMPUTER_TURN;
            }

        }
    }

    private static void checkForAces(Hand hand, List<GameStack> games){
        for(int i = 0; i < hand.getHandSize(); i++){
            if(hand.checkCard(i).getValue() == Card.ACE && hand.checkCard(i).getSuit() != Card.JOKER){
                GameStack game = new GameStack();
                game.addCard(hand.checkCard(i));
                hand.getCard(i);
                games.add(game);

                System.out.print("\033[H\033[2J");
                System.out.flush();

            }
        }
    }

    private static void fillHand(Hand hand, Deck deck){
        while(hand.getHandSize() < 4){
            hand.addCard(deck.dealCard());
        }
    }

    private void printGames(List<ArrayList<Card>> games){
        for(int i = 0; i < games.size(); i++){

        }
    }


}
