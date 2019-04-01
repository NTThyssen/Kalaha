package src;

public class Gamestate {

    int[] board;
    int player1Goal = 0;
    int player2Goal = 7;
    boolean player;

    public Gamestate(int[] board, boolean player){
        this.board = board;
        this.player = player;
       // initBoard();
    }

    public Gamestate(boolean player){
        this.player = player;
        board = new int[14];
        initBoard();
    }

    private void initBoard(){
        //filling pits with balls.
        for(int i = 1; i < 7; i ++){
            board[i] = 6;
            board[i+player2Goal] = 6;
        }
    }

    public int getOpposingGoal(){
        if(player){
            return player2Goal;
        }else{
            return player1Goal;
        }
    }
    public int getMyGoal(){
        if(player){
            return player1Goal;
        }else{
            return player2Goal;
        }
    }

    public int getMyStart(){
        if(player){
            return 1;
        }else{
            return 8;
        }
    }

    public int getMyEnd(){
        if(player){
            return 6;
        }else{
            return 13;
        }
    }


    public int[] getBoard(){
        return board;
    }

    public int getValueFromIndex(int index){
        return board[index];
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
