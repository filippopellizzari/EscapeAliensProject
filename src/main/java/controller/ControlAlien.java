package controller;

import model.*;

public class ControlAlien extends ControlPlayer{
	
	public ControlAlien(int playerPlay, Game game) {
		super(game, playerPlay);
	}
	
	@Override
	public boolean checkMove(Coordinate coordinate, int numberOfPlayer) {
		return false;
	}
	
	@Override
	public void round() {		
	}
}
