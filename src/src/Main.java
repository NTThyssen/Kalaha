package src;
public class Main {
    public static void main(String[] args){
        System.out.println("Hello kalaha");
        KalahaGame game = new KalahaGame();
        Gamestate gamestate = new Gamestate(game.getPits(), true);
        game.startGame();
        MoveGeneration mover = new MoveGeneration(gamestate, true);
        mover.generateGameStates();

    }
}
