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
    private int player1Goal = 0;
    private int player2Goal = 7;
    public boolean turn = true; //true = player 1, false = player 2.
    private boolean gameOver = false;
    public KalahaGame(){
        AI aiPlayer = new AI(pits);
    }

    //run this method to start a game.
    public void startGame(){
        //filling pits with balls.
        for(int i = 1; i < 7; i ++){
            pits[i] = 6;
            pits[i+player2Goal] = 6;
        }
        printGame();
        /*while(!gameOver){
            takeTurn();
            printGame();
        }*/
    }

    //method that takes a single turn, for one player.
    public void takeTurn() {
        if(turn){
            //Player 1's turn.
            turn = false;
            System.out.println("Player 1, Choose a field on your side.");
            int currentPit = getProperInput();
            int pitAfterMovement = moveBalls(currentPit, player2Goal);
            //rules
            if(pitAfterMovement == player1Goal && (pitAfterMovement >=0 && pitAfterMovement <=6)){
                //last ball has been placed in the goal.
                //and player 1 gets another turn.
                turn = true;
            }else lastBallCheck(pitAfterMovement, player1Goal);
        }else{
            //Player 2's turn
            turn = true;
            System.out.println("Player 2, Choose a field on your side.");
            int currentPit = 14-getProperInput();
            int pitAfterMovement = moveBalls(currentPit, player1Goal);
            if(pitAfterMovement == player1Goal && (pitAfterMovement >=7 && pitAfterMovement <=13)){
                //last ball has been placed in the goal.
                //and player 1 gets another turn.
                turn = false;
            }else {
                lastBallCheck(pitAfterMovement, player2Goal);
            }
        }
        checkGameState();
    }
    
    private void lastBallCheck(int pitAfterMovement, int player2Goal) {
        if(pits[pitAfterMovement] == 1){
            //last ball has been placed in empty pit
            pits[pitAfterMovement] = 0;
            pits[player2Goal] += pits[14-pitAfterMovement]+1;
            pits[14-pitAfterMovement] = 0;
        }
    }

    //distributes balls according to the rules
    //and returns the last pit a ball was dropped in.
    private int moveBalls(int currentPit, int playerGoalToAvoid){
        int ballsToPlace = pits[currentPit];
        pits[currentPit] = 0;
        while(ballsToPlace != 0){
            currentPit--;
            //loop around the playing board.
            if(currentPit == -1){
                currentPit = 13;
            }
            //keep putting balls in the next pit until no balls are left.
            if(currentPit != playerGoalToAvoid){
                pits[currentPit]++;
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
        if(pits[player1Goal] >= 36){
            gameOver = true;
            System.out.println("player 1 has won the game.");
        }else if(pits[player2Goal] >= 36){
            gameOver = true;
            System.out.println("player 2 has won the game.");
        }
    }
    //Prints the game state in a readable fashion.
    private void printGame(){
        System.out.println(" "+ pits[1]+ " " + pits[2]+ " "+ pits[3]+ " "+ pits[4]+ " "+ pits[5]+ " "+ pits[6]);
        System.out.println(pits[player1Goal]+"           " + pits[player2Goal]);
        System.out.println(" "+ pits[6+player2Goal]+ " " + pits[5+player2Goal]+ " "+ pits[4+player2Goal]+ " "+ pits[3+player2Goal]+ " "+ pits[2+player2Goal]+ " "+ pits[1+player2Goal]);
    }
}
