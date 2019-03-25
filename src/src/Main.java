package src;
public class Main {
    public static void main(String[] args){
        System.out.println("Hello kalaha");
        KalahaGame game = new KalahaGame();
        Gamestate gamestate = new Gamestate(game.getPits(), true);
        game.startGame();
        MoveGeneration mover = new MoveGeneration(gamestate, true);
        Gamestate[] initialGameStates = mover.generateGameStates();
        int[] abValues = new int[initialGameStates.length];
        for(int i = 0; i < initialGameStates.length; i++){
            AlphaBeta ab = new AlphaBeta(initialGameStates[i].player, 5);
            abValues[i] = ab.runAlphaBeta(Integer.MIN_VALUE,Integer.MAX_VALUE , initialGameStates[i], 0);
            System.out.println(abValues[i]);
        }
    }
}
