package src;

import java.util.ArrayList;

public class AI implements Player{

    public int makeMove(Gamestate gameState){

        MoveGeneration mover = new MoveGeneration(gameState);
        ArrayList<Gamestate> initialGameStates = mover.generateGameStates();
        int[] abValues = new int[initialGameStates.size()];
        for(int i = 0; i < initialGameStates.size(); i++){
            if(initialGameStates.get(i) != null) {
                System.out.println("game " + i);
                AlphaBeta ab = new AlphaBeta(!initialGameStates.get(i).player, 15); //skal måske ikke bruge !
                abValues[i] = ab.runAlphaBeta(Integer.MIN_VALUE, Integer.MAX_VALUE, initialGameStates.get(i), 0);
                System.out.println("index: " + (6-i) + " value: " + abValues[i]);
            } else {
                System.out.println("index: " + (6-i) + " invalid");
                abValues[i]=Integer.MIN_VALUE;
            }
        }
        int index = findMAX(abValues);
        index = 6- index;
        System.out.println("best choice was: " + index);

        return index;
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
        return index;
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
