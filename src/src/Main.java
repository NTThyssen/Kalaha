package src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello kalaha");
        KalahaGame game = new KalahaGame();
        game.startGame();
/*        int[] asf = {30, 1, 0, 0, 0, 0, 0, 30, 1, 0, 0, 0, 0, 0};
        //Gamestate gamestate = new Gamestate(asf , true);
        Gamestate gamestate = new Gamestate(game.getPits(), true);
        MoveGeneration mover = new MoveGeneration(gamestate, true);
        ArrayList<Gamestate> initialGameStates = mover.generateGameStates();
        int[] abValues = new int[initialGameStates.size()];
        for(int i = 0; i < initialGameStates.size(); i++){
            System.out.println("game " + i);
            AlphaBeta ab = new AlphaBeta(initialGameStates.get(i).player, 21);
            abValues[i] = ab.runAlphaBeta(Integer.MIN_VALUE,Integer.MAX_VALUE , initialGameStates.get(i), 0);
            System.out.println("index: " + i + " value: " + abValues[i]);
        }
        int min = 0;
        int index = 0;
        for(int i = 0; i < abValues.length; i++){
            if(abValues[i] < min){
                min = abValues[i];
                index = i;
            }
        }
        System.out.println("best choice was: " + index);
        /*int[] goodGameState = {1, 0, 7, 7, 7, 0, 6, 5, 7, 7, 7, 7, 7, 8};

        Evaluation eval = new Evaluation(true, new Gamestate(goodGameState, true));
        System.out.println("yooo" + eval.evaluateGamestate());*/

    }
}
