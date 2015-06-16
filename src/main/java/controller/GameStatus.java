package controller;

import java.io.Serializable;

import model.*;

/**
 * this class contains the status of a player during his turn; booleans attributes indicate
 * if a player has already do a type of action or if a player must do something
 * 
 * @author Filippo
 *
 */

public class GameStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Player player;
	private final Game game;
	private boolean moved;
	private boolean attacked;
	private boolean sedated;
	private boolean mustDraw; //must draw dangerous sector card
	private boolean mustDiscardItem;//must discard fourth itemcard
	private boolean mustNoise; //must select random coordinate for "noise in any sector"
	

	public GameStatus(Game game, Player player) {
		this.player = player;
		this.game = game;
		moved = false;
		attacked = false;
		mustDraw = false;
		mustDiscardItem = false;
		mustNoise = false;
	}


	public Player getPlayer() {
		return player;
	}



	public Game getGame() {
		return game;
	}


	public boolean isMoved() {
		return moved;
	}


	public void setMoved(boolean moved) {
		this.moved = moved;
	}


	public boolean isAttacked() {
		return attacked;
	}


	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}


	public boolean isSedated(){
		return sedated;
	}
	
	public void setSedated(boolean sedated){
		this.sedated = sedated;
	}
	
	public boolean isMustDraw() {
		return mustDraw;
	}


	public void setMustDraw(boolean mustDraw) {
		this.mustDraw = mustDraw;
	}


	public boolean isMustDiscardItem() {
		return mustDiscardItem;
	}


	public void setMustDiscardItem(boolean mustDiscardItem) {
		this.mustDiscardItem = mustDiscardItem;
	}


	public boolean isMustNoise() {
		return mustNoise;
	}


	public void setMustNoise(boolean mustNoise) {
		this.mustNoise = mustNoise;
	}

	
}
