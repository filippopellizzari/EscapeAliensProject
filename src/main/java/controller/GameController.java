package controller;

import java.util.List;

import model.*;

public class GameController {
	Game game;
	int turn;
	int playerPlay;						//giocatore attuale che gioca
	int numberOfPlayer;
	public GameController(Game game) {
		this.game=game;
		this.turn=0;
		this.numberOfPlayer=game.getPlayers().size();
		playerPlay=1;
	}
	public void play() {
	}
	/*activePlayer() {
	}*/
	boolean controlEndGame() {
		if(turn>39) return true;
		boolean endTurn=true;
		List<Coordinate> hatchSectorToControl=game.getMap().getHatchSectors();		//prende le coordinate degli hatch
		for(int i=0;i<6;i++) 
			if(game.getMap().getSector(hatchSectorToControl.get(i)).isCrossable()==true) 
				endTurn=false;			//controlla settori accessibili
		if(endTurn==true) 
			return true;		//tutti gli hatch sono chiusi
		List<Player> player=game.getPlayers();
		for(int i=0; i<numberOfPlayer;i++) {
			if(player.get(i).getName()==TypePlayer.Human)
				if(player.get(i).isAlive()==true) 
					return false;
		}
		return false;		//nessun giocatore umano vivo
	}
	/*notify() {
		
	}*/
}
