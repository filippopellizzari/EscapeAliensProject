package controller;

import model.*;

public class ControlHuman extends ControlPlayer{
	Game game;
	public ControlHuman(int playerPlay, Game game) {
		super(game, playerPlay);
		this.game=game;
	}
	@Override
	public boolean checkMove(Coordinate coordinate, int numberOfPlayer) {
		return false;
	}
	@Override
	public void round() {
		
	}

}
