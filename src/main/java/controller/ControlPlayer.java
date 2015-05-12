package controller;

import model.*;

public abstract class ControlPlayer {
	protected Sector sectorOfPlayer;
	protected Game game;
	public ControlPlayer(Game game, int numberOfPlayer) {
		this.game = game;
	}
	public void checkPosition(int numberOfPlayer) {
		this.sectorOfPlayer=game.getPlayers().get(numberOfPlayer).getCurrentSector();
	}
	public abstract boolean checkMove(Coordinate coordinate, int numberOfPlayer);
	public abstract void round();
}
