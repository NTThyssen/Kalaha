package src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello kalaha");

        //Evaluation test
        /*int[] asf = {5, 8, 0, 7, 7, 0, 7, 1, 7, 8, 8, 8, 9, 0};
        Evaluation eva2 = new Evaluation(true, new Gamestate(asf, true));
        System.out.println("eva2: " + eva2.evaluateGamestate()); */
        Player humanplayer = new Human();
        Player AI1 = new AI();
        //Player AI2 = new AI(1,10);
        //KalahaGame game = new KalahaGame(humanplayer, AI1);
        //KalahaGame game = new KalahaGame(AI1, AI2);
        //game.startGame();



        ArrayList<AI> allAI = new ArrayList<AI>();

        for(int i = 1; i <= 5; i++){
            for(int j = 1; j <= 5; j++){
                allAI.add(new AI(i, j));
            }
        }

        int maxWins = 0;
        int pitID = 0;
        int sideID = 0;
        int pitID2 = 0;
        int sideID2 = 0;
        int minWins = 105;

        for(AI i : allAI){
            int myWins = 0;
            for(AI j : allAI){
                KalahaGame tempGame = new KalahaGame(i, j);
                System.out.println(i.pointsPerBallInYourPit + " " + i.pointsPerBallOnYourSide + " vs " + j.pointsPerBallInYourPit + " " + j.pointsPerBallOnYourSide);
                if(tempGame.startGame() == 1){
                    myWins++;
                }
            }
            if(myWins > maxWins){
                maxWins = myWins;
                pitID = i.getPointsPerBallInYourPit();
                sideID = i.getPointsPerBallOnYourSide();
            }
            if(myWins < minWins){
                minWins = myWins;
                pitID2 = i.getPointsPerBallInYourPit();
                sideID2 = i.getPointsPerBallOnYourSide();
            }
        }

        System.out.println("The best AI was pitID=" + pitID + " sideID=" + sideID + " with " + maxWins + " wins");
        System.out.println("The worst AI was pitID=" + pitID2 + " sideID=" + sideID2 + " with " + minWins + " wins");



        /*
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
