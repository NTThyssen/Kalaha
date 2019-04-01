package src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello kalaha");
        Player humanplayer = new Human();
        Player AI1 = new AI();
        KalahaGame game = new KalahaGame(humanplayer, AI1);
        game.startGame();
    }
}
