package src;

public class Gamestate {

    int[] board;
    boolean player;

    public Gamestate(int[] board, boolean player){
        this.board = board;
        this.player = player;
    }

    public int[] getBoard(){
        return board;
    }

    public void setBoard(int[]  board){
        this.board = board;
    }

    public boolean getPlayer(){
        return player;
    }

    public void setPlayer(boolean player){
        this.player = player;
    }
}
