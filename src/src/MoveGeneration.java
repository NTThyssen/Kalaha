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
        ArrayList<Gamestate> newGameStates = new ArrayList<>();
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

        tempGameState = moveBalls(tempGameState, chosenPit);

        return checkForEmptySide(tempGameState);
    }
    public Gamestate checkForEmptySide(Gamestate gamestate){
        int p1sum = 0;
        int p2sum = 0;
        for(int i = 1; i <= 6; i++){
            p1sum+=gamestate.getBoard()[i];
            p2sum+=gamestate.getBoard()[i+gamestate.player2Goal];
        }
        if(p1sum==0){
            gamestate.getBoard()[gamestate.player2Goal]+=p2sum;
            for(int i = 1; i < 7; i ++){
                gamestate.getBoard()[i] = 0;
                gamestate.getBoard()[i+gamestate.player2Goal] = 0;
            }

        } else if(p2sum==0){
            gamestate.getBoard()[gamestate.player1Goal]+=p1sum;
            for(int i = 1; i < 7; i ++){
                gamestate.getBoard()[i] = 0;
                gamestate.getBoard()[i+gamestate.player2Goal] = 0;
            }
        }
        return gamestate;
    }

    private void lastBallCheck(int pitAfterMovement, Gamestate tempGamestate) {
        if(tempGamestate.getBoard()[pitAfterMovement] == 1 && (pitAfterMovement >= tempGamestate.getMyStart() && pitAfterMovement <= tempGamestate.getMyEnd())){
            //last ball has been placed in empty pit
            tempGamestate.getBoard()[pitAfterMovement] = 0;
            tempGamestate.getBoard()[tempGamestate.getMyGoal()] += tempGamestate.getBoard()[14-pitAfterMovement]+1;
            tempGamestate.getBoard()[14-pitAfterMovement] = 0;
        }
    }

    private Gamestate moveBalls(Gamestate tempGameState, int chosenPit) {
        int ballsToPlace = tempGameState.getBoard()[chosenPit];
        tempGameState.getBoard()[chosenPit] = 0;
        while(ballsToPlace != 0){
            chosenPit--;
            if(chosenPit == -1){
                chosenPit = 13;
            }
            if(chosenPit != gamestate.getOpposingGoal()){
                tempGameState.getBoard()[chosenPit]++;
                ballsToPlace--;
            }
        }

        lastBallCheck(chosenPit, tempGameState);

        if(chosenPit != gamestate.getMyGoal()){
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
