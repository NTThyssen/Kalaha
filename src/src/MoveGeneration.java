package src;

public class MoveGeneration {
    private int[] gameState;
    private boolean playerTurn;

    public MoveGeneration(int[] gameState){
        this.gameState = gameState;
        generateGameStates(generatesValidGameStates(gameState, true), true);

    }

    public boolean[] generatesValidGameStates(int[] gameState, boolean playerTurn){
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
            if(gameState[i] > 0){
                validMoves[i-lowerBound] = true;
            }else {
                validMoves[i-lowerBound] = false;
            }
        }

        return validMoves;
    }

    public int[][] generateGameStates(boolean[] validMoves, boolean playerTurn){
        int[][] newGameStates = new int[6][];
        for(int i = 0; i<6; i++){
            System.out.println(validMoves[i]);
            if(validMoves[i]){
                newGameStates[i] = takeTurn(playerTurn, i+1);
                printGame(newGameStates[i]);
            }
        }

        return newGameStates;
    }

    public int[] takeTurn(boolean playerTurn, int chosenPit){
        int[] tempGameState = gameState.clone();
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


    private int[] moveBalls(int[] tempGameState, int chosenPit, int goalToAvoid, int goodGoal) {
        int ballsToPlace = tempGameState[chosenPit];
        tempGameState[chosenPit] = 0;
        while(ballsToPlace != 0){
            chosenPit--;
            if(chosenPit == -1){
                chosenPit = 13;
            }
            if(chosenPit != goalToAvoid){
                tempGameState[chosenPit]++;
                ballsToPlace--;
            }
        }
        if(tempGameState[chosenPit] == 1 && (chosenPit != 0 && chosenPit != 7)){
            tempGameState[chosenPit] = 0;
            tempGameState[goodGoal] = 1 + tempGameState[14-chosenPit];
            tempGameState[chosenPit] = 0;
        }
        return tempGameState;
    }

    private void printGame(int[] gameToPrint){
        System.out.println(" "+ gameToPrint[1]+ " " + gameToPrint[2]+ " "+ gameToPrint[3]+ " "+ gameToPrint[4]+ " "+ gameToPrint[5]+ " "+ gameToPrint[6]);
        System.out.println(gameToPrint[0]+"           " + gameToPrint[7]);
        System.out.println(" "+ gameToPrint[6+7]+ " " + gameToPrint[5+7]+ " "+ gameToPrint[4+7]+ " "+ gameToPrint[3+7]+ " "+ gameToPrint[2+7]+ " "+ gameToPrint[1+7]);
    }


}
