package src;

import java.util.*;

public class AlphaBeta {

    private boolean player;     //true = player 1.   false = player 2
    private Evaluation eva;
    private int searchDepth;

    public AlphaBeta(boolean player, int searchDepth) {
        this.player = player;
        this.searchDepth = searchDepth;
    }

    public int runAlphaBeta(int alpha, int beta, Gamestate gamestate, int currentDepth) {

        //if node is leaf
        if (gamestate.gameFinished() || currentDepth >= searchDepth) {
            this.eva = new Evaluation(player, gamestate);
            return eva.evaluateGamestate();
        }

        MoveGeneration moveGenerator = new MoveGeneration(gamestate);
        ArrayList<Gamestate> childnodes = moveGenerator.generateGameStates();

        int nextNode = 0;

        //if current node is MAX
        if (player == gamestate.player) {
            while (alpha < beta && nextNode < childnodes.size()) {
                if (childnodes.get(nextNode) != null) {
                    int V = runAlphaBeta(alpha, beta, childnodes.get(nextNode++), ++currentDepth);
                    if (V > alpha) {
                        alpha = V;
                    }
                } else {
                    nextNode++;
                }
            }
            return alpha;

            //if current node is MIN
        } else {
            while (alpha < beta && nextNode < childnodes.size()) {
                if(childnodes.get(nextNode) != null){
                    int V = runAlphaBeta(alpha, beta, childnodes.get(nextNode++), ++currentDepth);
                    if (V < beta) {
                        beta = V;
                    }
                } else {
                    nextNode++;
                }
            }
            return beta;
        }
    }
}
