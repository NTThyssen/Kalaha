package src;

public class Evaluation {

	//boolean player;		//true = player 1. false = player 2
	Gamestate gamestate;
	
	public Evaluation(boolean player, Gamestate gamestate) {
		this.gamestate = gamestate;
	}
	
	public int evaluateGamestate(){

		int points = gamestate.getValueFromIndex(7)-gamestate.getValueFromIndex(13);
		if(gamestate.getPlayer()) {
			if(gamestate.getValueFromIndex(7) > 36) {
				points += 100;
			}
		} else {
			if(gamestate.getValueFromIndex(13) > 36){
				points +=100;
			}
		}

		if(!gamestate.getPlayer()) {
			points = points * -1;
		}

		return points;
	}
}
