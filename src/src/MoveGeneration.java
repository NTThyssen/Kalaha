package src;

public class MoveGeneration {
    private Gamestate gamestate;
    private boolean[] validMoves;
    private boolean playerTurn;

    public MoveGeneration(Gamestate inputGame, boolean player) {
        this.gamestate = inputGame;
        this.playerTurn = player;
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

    public Gamestate[] generateGameStates(){
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
            tempGameState.getBoard()[14-chosenPit] = 0;
        }

        if(chosenPit != goodGoal){
            tempGameState.setPlayer(!playerTurn);
        }else{
            System.out.println("player gets an extra turn.");
        }
        return tempGameState;
    }

    private void printGame(Gamestate gameToPrint){
        System.out.println(" "+ gameToPrint.getBoard()[1]+ " " + gameToPrint.getBoard()[2]+ " "+ gameToPrint.getBoard()[3]+ " "+ gameToPrint.getBoard()[4]+ " "+ gameToPrint.getBoard()[5]+ " "+ gameToPrint.getBoard()[6]);
        System.out.println(gameToPrint.getBoard()[0]+"           " + gameToPrint.getBoard()[7]);
        System.out.println(" "+ gameToPrint.getBoard()[6+7]+ " " + gameToPrint.getBoard()[5+7]+ " "+ gameToPrint.getBoard()[4+7]+ " "+ gameToPrint.getBoard()[3+7]+ " "+ gameToPrint.getBoard()[2+7]+ " "+ gameToPrint.getBoard()[1+7]);
    }


}
