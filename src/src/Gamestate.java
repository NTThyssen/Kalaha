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

    public int getValueFromIndex(int index){
        return board[index];
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

    public boolean gameFinished(){
        if(board[0] + board[7] == 72){
            return true;
        } else {
            return false;
        }
    }
}
