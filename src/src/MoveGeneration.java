package src;

import java.util.ArrayList;

public class MoveGeneration {
    private Gamestate gamestate;
    private boolean[] validMoves;
    private boolean playerTurn;

    public MoveGeneration(Gamestate inputGame) {
        this.gamestate = inputGame;
        this.playerTurn = inputGame.player;
        validMoves = generateValidGameStates();
    }

    private boolean[] generateValidGameStates(){
        boolean[] validMoves= new boolean[6];
        int upperBound;
        int lowerBound;
        if(playerTurn) {
           upperBound = 6;
           lowerBound = 1;

        }else {
             upperBound = 13;
             lowerBound = 8;
        }
        for(int i = lowerBound; i <= upperBound; i++){
            if(gamestate.getBoard()[i] > 0){
                validMoves[i-lowerBound] = true;
            }else {
                validMoves[i-lowerBound] = false;
            }
        }

        return validMoves;
    }

    public ArrayList<Gamestate> generateGameStates(){
        //Gamestate[] newGameStates = new Gamestate[6];
        ArrayList<Gamestate> newGameStates = new ArrayList<Gamestate>();
        for(int i = 1; i<=6; i++){
        //    System.out.println(validMoves[i]);
            if(validMoves[i-1]){
               // newGameStates[i] = takeTurn(playerTurn, i+1);
                if(gamestate.player){
                    newGameStates.add(takeTurn(playerTurn, i));
                }else{
                    newGameStates.add(takeTurn(playerTurn, (gamestate.player2Goal + i)));
                }
                //printGame(newGameStates[i]);
            } else {
                newGameStates.add(null);
            }
        }
        return newGameStates;
    }

    public Gamestate takeTurn(boolean playerTurn, int chosenPit){
        Gamestate tempGameState = new Gamestate(gamestate.getBoard().clone(), playerTurn);
            int goalToAvoid, goodGoal;
            if(playerTurn){
            goalToAvoid = 7;
            goodGoal = 0;
        }else{
            goalToAvoid = 0;
            goodGoal = 7;
        }
        tempGameState = moveBalls(tempGameState, chosenPit, goalToAvoid, goodGoal);
        return tempGameState;
    }

    private void lastBallCheck(int pitAfterMovement, Gamestate tempGamestate) {
        if(tempGamestate.getBoard()[pitAfterMovement] == 1 && (pitAfterMovement >= tempGamestate.getMyStart() && pitAfterMovement <= tempGamestate.getMyEnd())){
            //last ball has been placed in empty pit
            tempGamestate.getBoard()[pitAfterMovement] = 0;
            tempGamestate.getBoard()[tempGamestate.getMyGoal()] += tempGamestate.getBoard()[14-pitAfterMovement]+1;
            tempGamestate.getBoard()[14-pitAfterMovement] = 0;
        }
    }

    private Gamestate moveBalls(Gamestate tempGameState, int chosenPit, int goalToAvoid, int goodGoal) {
        int ballsToPlace = tempGameState.getBoard()[chosenPit];
        tempGameState.getBoard()[chosenPit] = 0;
        while(ballsToPlace != 0){
            chosenPit--;
            if(chosenPit == -1){
                chosenPit = 13;
            }
            if(chosenPit != goalToAvoid){
                tempGameState.getBoard()[chosenPit]++;
                ballsToPlace--;
            }
        }

        lastBallCheck(chosenPit, tempGameState);

        if(chosenPit != goodGoal){
            tempGameState.setPlayer(!playerTurn);
        }
        return tempGameState;
    }

    private void printGame(Gamestate gameToPrint){
        System.out.println(" "+ gameToPrint.getBoard()[1]+ " " + gameToPrint.getBoard()[2]+ " "+ gameToPrint.getBoard()[3]+ " "+ gameToPrint.getBoard()[4]+ " "+ gameToPrint.getBoard()[5]+ " "+ gameToPrint.getBoard()[6]);
        System.out.println(gameToPrint.getBoard()[0]+"           " + gameToPrint.getBoard()[7]);
        System.out.println(" "+ gameToPrint.getBoard()[6+7]+ " " + gameToPrint.getBoard()[5+7]+ " "+ gameToPrint.getBoard()[4+7]+ " "+ gameToPrint.getBoard()[3+7]+ " "+ gameToPrint.getBoard()[2+7]+ " "+ gameToPrint.getBoard()[1+7]);
    }


}
