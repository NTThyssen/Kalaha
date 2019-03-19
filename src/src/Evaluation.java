package src.src;

public class Evaluation {

	boolean player;		//true = player 1. false = player 2
	int[] gamestate;
	
	public Evaluation(boolean player, int[] gamestate) {
		this.player = player;
		this.gamestate = gamestate;
	}
	
	public int evaluateGamestate(){
		
		int points = gamestate[7]-gamestate[13];
		
		if(!player) {
			points = points * -1;
		}
		
		return points;
	}
}
