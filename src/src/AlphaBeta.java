package src;
import java.util.*;

public class AlphaBeta{

    private Gamestate gamestate;
    private boolean player;     //true = player 1.   false = player 2
    private Evaluation eva;

    public AlphaBeta(Gamestate gamestate, boolean player){
        this.gamestate = gamestate;
        this.player = player;
    }

    public int runAlphaBeta(int alpha, int beta, Gamestate gamestate){

        //if node is leaf
        if(gamestate.gameFinished()){
            this.eva = new Evaluation(player, gamestate);
            return eva.evaluateGamestate();
        }

        MoveGeneration moveGenerator = new MoveGeneration(gamestate, player);
        Gamestate[] childnodes = moveGenerator.generateGameStates();

        int nextNode = 0;

        //if current node is MAX
        if(player == gamestate.player){
            while(alpha < beta && nextNode < childnodes.length){
                int V = runAlphaBeta(alpha, beta, childnodes[nextNode++]);
                if(V > alpha){
                    alpha = V;
                }
            }
            return alpha;

        //if current node is MIN
        } else {
            while(alpha < beta){
                int V = runAlphaBeta(alpha, beta, childnodes[nextNode++]);
                if(V < beta){
                    beta = V;
                }
            }
            return beta;
        }
    }
}


/*package game_logic;

import java.util.ArrayList;

public class AlphaBeta {

    private GameState gameState;
    private int searchDepth;
    private boolean isWhite;

    public AlphaBeta(GameState gameState, int searchDepth, boolean isWhite) {
        this.gameState = gameState;
        this.searchDepth = searchDepth;
        this.isWhite = isWhite;
    }

    public double runAlphaBeta(double alpha, double beta, int currentDepth, MoveType moveType) {

        //If node is a leaf
        if(currentDepth == searchDepth) {
            Evaluation eva = new Evaluation(gameState, isWhite);
            return eva.evaluateState();
        }

        MoveGenerator mg = new MoveGenerator();
        ArrayList<MoveType> childNodes = mg.getAll(isWhite, gameState);
        int nextNode = 0;

        gameState.newState(moveType);

        //If current node is MAX
        if(currentDepth%2 == 0) {
            while(alpha < beta && nextNode < childNodes.size()) {
                gameState.newState(moveType);
                double V = runAlphaBeta(alpha, beta, currentDepth+1, childNodes.get(nextNode++));
                gameState.oldState(moveType);
                if(V > alpha) {
                    alpha = V;
                }
            }
            gameState.oldState(moveType);
            return alpha;

            //If current node is MIN
        } else {
            while(alpha < beta && nextNode < childNodes.size()) {
                gameState.newState(moveType);
                double V = runAlphaBeta(alpha, beta, currentDepth+1, childNodes.get(nextNode++));
                gameState.oldState(moveType);
                if(V < beta) {
                    beta = V;
                }
            }
            gameState.oldState(moveType);
            return beta;
        }
    }
} */