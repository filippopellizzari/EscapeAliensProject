package controller;

import model.*;

public class GameController {
	Game game;
	int turn;
	ControlHuman controlHuman;
	ControlAlien controlAlien;
	public GameController(Game game) {
		this.game=game;
		controlHuman=new ControlHuman();
		controlAlien=new ControlAlien();
		this.turn=0;
	}
	public void play() {
		while(controlEndGame()==false) {
			
		}
	}
	activePlayer() {
		
	}
	controlEndGame() {
		
	}
	notify() {
		
	}
}
