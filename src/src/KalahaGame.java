package src;
import java.util.Scanner;

public class KalahaGame {
    public int[] getPits() {
        return pits;
    }

    //this class represents a kalaha game, with functionality for
    //two players taking turns
    //keeping score
    //announcing a winner
    private int[] pits = new int[14];
    private Gamestate gamestate = new Gamestate(pits, true);
    private Player player1, player2;
    private boolean gameOver = false;
    public KalahaGame(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        //AI aiPlayer = new AI(pits);
    }

    //run this method to start a game.
    public void startGame(){
        //filling pits with balls.
        printGame();
        while(!gameOver){
            takeTurn();
            printGame();
        }
    }

    //method that takes a single turn, for one player.
    public void takeTurn() {
        int currentPit;
        if(gamestate.player){
            System.out.println("Player 1, Choose a field on your side.");
            currentPit = player1.makeMove(gamestate);
        }else{
            System.out.println("Player 2, Choose a field on your side.");
            currentPit = 14-player2.makeMove(gamestate);

        }
        int pitAfterMovement = moveBalls(currentPit);
        //rules
        if(pitAfterMovement != gamestate.getMyGoal()){
            //last ball has not been placed in the goal.
            //and the turns are switched.
            lastBallCheck(pitAfterMovement);
            gamestate.switchTurn();
        }
        checkGameState();
    }
    
    private void lastBallCheck(int pitAfterMovement) {
        if(gamestate.getBoard()[pitAfterMovement] == 1){
            //last ball has been placed in empty pit
            gamestate.getBoard()[pitAfterMovement] = 0;
            gamestate.getBoard()[gamestate.getMyGoal()] += gamestate.getBoard()[14-pitAfterMovement]+1;
            gamestate.getBoard()[14-pitAfterMovement] = 0;
        }
    }

    //distributes balls according to the rules
    //and returns the last pit a ball was dropped in.
    private int moveBalls(int currentPit){
        int ballsToPlace = gamestate.getBoard()[currentPit];
        gamestate.getBoard()[currentPit] = 0;
        while(ballsToPlace != 0){
            currentPit--;
            //loop around the playing board.
            if(currentPit == -1){
                currentPit = 13;
            }
            //keep putting balls in the next pit until no balls are left.
            if(currentPit != gamestate.getOpposingGoal()){
                gamestate.getBoard()[currentPit]++;
                ballsToPlace--;
            }
        }
        return currentPit;
    }
    //method that makes sure inputted numbers are valid
    //maybe delete?
    private int getProperInput(){
        Scanner input = new Scanner(System.in);
        int in;
        do{
            in = input.nextInt();
        }while((in <0 || in > 6));
        return in;
    }
    //Checks if the game has been won.
    private void checkGameState() {
        if(pits[gamestate.player1Goal] >= 36){
            gameOver = true;
            System.out.println("player 1 has won the game.");
        }else if(pits[gamestate.player2Goal] >= 36){
            gameOver = true;
            System.out.println("player 2 has won the game.");
        }
    }
    //Prints the game state in a readable fashion.
    private void printGame(){
        System.out.println(" "+ pits[1]+ " " + pits[2]+ " "+ pits[3]+ " "+ pits[4]+ " "+ pits[5]+ " "+ pits[6]);
        System.out.println(pits[gamestate.player1Goal]+"           " + pits[gamestate.player2Goal]);
        System.out.println(" "+ pits[6+gamestate.player2Goal]+ " " + pits[5+gamestate.player2Goal]+ " "+ pits[4+gamestate.player2Goal]+ " "+ pits[3+gamestate.player2Goal]+ " "+ pits[2+gamestate.player2Goal]+ " "+ pits[1+gamestate.player2Goal]);
    }
}
