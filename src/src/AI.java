package src;

import java.util.ArrayList;

public class AI implements Player{

    int pointsPerBallOnYourSide;
    int pointsPerBallInYourPit;

    //Default Constructor with the best performing values
    public AI(){
        this.pointsPerBallInYourPit = 5;
        this.pointsPerBallOnYourSide = 1;
    }

    //Constructor for testing variations of the AI
    public AI(int pointsPerBallInYourPit, int pointsPerBallOnYourSide){
        this.pointsPerBallInYourPit = pointsPerBallInYourPit;
        this.pointsPerBallOnYourSide = pointsPerBallOnYourSide;
    }

    public int makeMove(Gamestate gameState){

        MoveGeneration mover = new MoveGeneration(gameState);
        ArrayList<Gamestate> initialGameStates = mover.generateGameStates();
        int[] abValues = new int[initialGameStates.size()];
        for(int i = 0; i < initialGameStates.size(); i++){
            if(initialGameStates.get(i) != null) {
                AlphaBeta ab = new AlphaBeta(gameState.player, 21); //skal måske ikke bruge !
                abValues[i] = ab.runAlphaBeta(Integer.MIN_VALUE, Integer.MAX_VALUE, initialGameStates.get(i), 0, pointsPerBallInYourPit, pointsPerBallOnYourSide);
            } else {
                abValues[i]=Integer.MIN_VALUE;
            }
        }
        int index = findMAX(abValues);
        System.out.println("AI chose: " + (7-index));
        return index ;
    }

    private int findMAX(int[] abValues){
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < abValues.length; i++){
            if(abValues[i] > max){
                max = abValues[i];
                index = i;
            }
        }
        return index+1;
    }

    private int findMIN(int[] abValues){
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < abValues.length; i++){
            if(abValues[i] < min){
                min = abValues[i];
                index = i;
            }
        }
        return index;
    }

}
