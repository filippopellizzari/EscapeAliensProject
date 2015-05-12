package controller;

import java.util.List;

import model.*;

public class GameController {
	Game game;
	int turn;
	int playerPlay;
	int numberOfPlayer;
	ControlHuman controlHuman;
	ControlAlien controlAlien;
	public GameController(Game game, int numberOfPlayer) {
		this.game=game;
		controlHuman=new ControlHuman();
		controlAlien=new ControlAlien();
		this.turn=0;
		this.numberOfPlayer=numberOfPlayer;
		playerPlay=1;
	}
	public void play() {
		while(controlEndGame()==false) {
			//activePlayer(playerPlay);		non so ancora come farla
			playerPlay++;			//prossimo giocatore
			if(playerPlay==9) playerPlay=1;
		}
	}
	/*activePlayer() {
		
	}*/
	boolean controlEndGame() {
		if(turn>39) return true;
		boolean endTurn=true;
		List<Coordinate> hatchSectorToControl=game.getGameMap().getHatchSectors();		//prende le coordinate degli hatch
		for(int i=0;i<6;i++) if(game.getGameMap().getSector(hatchSectorToControl.get(i)).isCrossable()==true) endTurn=false;			//controlla settori accessibili
		if(endTurn==true) return true;		//tutti gli hatch sono chiusi
		List<Player> player=game.getPlayerInGame();
		for(int i=0; i<numberOfPlayer;i++) {
			if(player.get(i).getTypePlayer()==TypePlayer.Human)
				if(player.get(i).isAlive()==true) return false;
		}
		return false;		//nessun giocatore umano vivo
	}
	/*notify() {
		
	}*/
}
