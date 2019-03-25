package src;

public class MoveGeneration {
    private Gamestate gamestate;
    private boolean playerTurn;

    public MoveGeneration(int[] inputGame){
        //this.gameState = gameState;
        gamestate = new Gamestate(inputGame, true);

        generateGameStates(generatesValidGameStates(gamestate.getBoard(), true), true);

    }

    public MoveGeneration(Gamestate inputGame) {
        this.gamestate = inputGame;
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

    public Gamestate[] generateGameStates(boolean[] validMoves, boolean playerTurn){
        Gamestate[] newGameStates = new Gamestate[6];
        for(int i = 0; i<6; i++){
            System.out.println(validMoves[i]);
            if(validMoves[i]){
                newGameStates[i] = takeTurn(playerTurn, i+1);
                printGame(newGameStates[i]);
            }
        }
        return newGameStates;
    }

    public Gamestate takeTurn(boolean playerTurn, int chosenPit){
        Gamestate tempGameState = new Gamestate(gamestate.getBoard().clone(), playerTurn);
        int[] tempBoard = tempGameState.getBoard().clone();
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
        if(tempGameState.getBoard()[chosenPit] == 1 && (chosenPit != 0 && chosenPit != 7)){
            tempGameState.getBoard()[chosenPit] = 0;
            tempGameState.getBoard()[goodGoal] = 1 + tempGameState.getBoard()[14-chosenPit];
            tempGameState.getBoard()[chosenPit] = 0;
        }
        return tempGameState;
    }

    private void printGame(Gamestate gameToPrint){
        System.out.println(" "+ gameToPrint.getBoard()[1]+ " " + gameToPrint.getBoard()[2]+ " "+ gameToPrint.getBoard()[3]+ " "+ gameToPrint.getBoard()[4]+ " "+ gameToPrint.getBoard()[5]+ " "+ gameToPrint.getBoard()[6]);
        System.out.println(gameToPrint.getBoard()[0]+"           " + gameToPrint.getBoard()[7]);
        System.out.println(" "+ gameToPrint.getBoard()[6+7]+ " " + gameToPrint.getBoard()[5+7]+ " "+ gameToPrint.getBoard()[4+7]+ " "+ gameToPrint.getBoard()[3+7]+ " "+ gameToPrint.getBoard()[2+7]+ " "+ gameToPrint.getBoard()[1+7]);
    }


}
