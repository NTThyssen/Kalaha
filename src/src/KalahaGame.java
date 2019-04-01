package src;
import java.util.ArrayList;
import java.util.Scanner;

public class KalahaGame {
    public int[] getPits() {
        return pits;
    }

    //this class represents a kalaha game, with functionality for
    //two players taking turns
    //keeping score
    //announcing a winner
    int playerVictory = 0; //0 = no winner, 1 = player1 wins, 2 = player2 wins
    private int[] pits = new int[14];
    int[] asf = {0, 0, 1, 0, 0, 10, 1, 0, 0, 0, 1, 7, 0, 1};
    private Gamestate gamestate = new Gamestate( true);
    private Player player1, player2;
    private boolean gameOver = false;
    public KalahaGame(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        //AI aiPlayer = new AI(pits);
    }

    //run this method to start a game.
    public int startGame(){
        //filling pits with balls.
        int turns = 0;
 //       printGame(gamestate);
        while(!gameOver){
            takeTurn();
   //         printGame(gamestate);
            turns++;
        }
        System.out.println("turns in this game: " + turns);
        return playerVictory;
    }

    //method that takes a single turn, for one player.
    public void takeTurn() {
        MoveGeneration mg = new MoveGeneration(gamestate);
        ArrayList<Gamestate> nextMoves = mg.generateGameStates();
        int currentPit;
        if(gamestate.player){
       //     System.out.println("Player 1, Choose a field on your side.");
            currentPit = player1.makeMove(gamestate);

        }else{
      //      System.out.println("Player 2, Choose a field on your side.");
            currentPit =  player2.makeMove(gamestate);
        }
        gamestate = nextMoves.get(currentPit-1);

        /*for(int i = 0; i < nextMoves.size(); i++){
            if(nextMoves.get(i) != null){
                printGame(nextMoves.get(i));
            }else{
                System.out.println("null");
            }
        }*/


        checkGameState();
    }
    //Checks if the game has been won.
    private void checkGameState() {
        if(gamestate.getBoard()[gamestate.player1Goal] > 36){
            gameOver = true;
            playerVictory = 1;
    //        System.out.println("player 1 has won the game.");
        }else if(gamestate.getBoard()[gamestate.player2Goal] > 36){
            gameOver = true;
            playerVictory = 2;
    //        System.out.println("player 2 has won the game.");
        } else if(gamestate.getBoard()[gamestate.player1Goal] == 36 &&
                    gamestate.getBoard()[gamestate.player2Goal] == 36){
            gameOver = true;
            playerVictory = 0;
        //    System.out.println("The game was a tie");
        }
    }
    //Prints the game state in a readable fashion.
    private void printGame(Gamestate gamestate){
        System.out.println(" "+ gamestate.getBoard()[1]+ " " + gamestate.getBoard()[2]+ " "+ gamestate.getBoard()[3]+ " "+ gamestate.getBoard()[4]+ " "+ gamestate.getBoard()[5]+ " "+ gamestate.getBoard()[6]);
        System.out.println(gamestate.getBoard()[gamestate.player1Goal]+"           " + gamestate.getBoard()[gamestate.player2Goal]);
        System.out.println(" "+ gamestate.getBoard()[6+gamestate.player2Goal]+ " " + gamestate.getBoard()[5+gamestate.player2Goal]+ " "+ gamestate.getBoard()[4+gamestate.player2Goal]+ " "+ gamestate.getBoard()[3+gamestate.player2Goal]+ " "+ gamestate.getBoard()[2+gamestate.player2Goal]+ " "+ gamestate.getBoard()[1+gamestate.player2Goal]);
    }
}
