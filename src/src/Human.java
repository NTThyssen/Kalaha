package src;

import java.util.Scanner;

public class Human implements Player{

    public int makeMove(Gamestate gameState){

        Scanner input = new Scanner(System.in);

        return input.nextInt();
    }
}
