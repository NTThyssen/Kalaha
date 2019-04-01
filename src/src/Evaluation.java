package src;

public class Evaluation {

	boolean player;		//true = player 1. false = player 2
	Gamestate gamestate;
	int pointsPerBallOnYourSide;
	int pointsPerBallInYourPit;
	
	public Evaluation(boolean player, Gamestate gamestate, int pointsPerBallInYourPit, int pointsPerBallOnYourSide) {
		this.gamestate = gamestate;
		this.player = player;
		this.pointsPerBallInYourPit = pointsPerBallInYourPit;
		this.pointsPerBallOnYourSide = pointsPerBallOnYourSide;
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

		if(!player) {
			points = points * -1;
		}
		return points;
	}

	public int evaluateGamestate2(){




		return 0;
	}
}
