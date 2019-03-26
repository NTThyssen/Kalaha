package src;

public class Evaluation {

	boolean player;		//true = player 1. false = player 2
	Gamestate gamestate;
	final int pointsPerBallOnYourSide = 1;
	final int pointsPerBallInYourPit = 5;
	
	public Evaluation(boolean player, Gamestate gamestate) {
		this.gamestate = gamestate;
		this.player = player;
	}
	
	public int evaluateGamestate(){

		int points = 0;

			if(gamestate.getValueFromIndex(0) > 36) {
				points +=10000;
			}
			for(int i = 1; i < 7; i++){
				points+=gamestate.getBoard()[i]*pointsPerBallOnYourSide;
			}
			points+=(gamestate.getValueFromIndex(0)*pointsPerBallInYourPit);


			if(gamestate.getValueFromIndex(7) > 36){
				points -=10000;
			}
			for(int i = 8; i < 14; i++){
				points-=gamestate.getBoard()[i]*pointsPerBallOnYourSide;
			}
			points-=(gamestate.getValueFromIndex(7)*pointsPerBallInYourPit);


		if(player != gamestate.getPlayer()) {
			points = points * -1;
		}

		return points;
	}
}
