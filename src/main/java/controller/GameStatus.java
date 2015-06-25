package controller;

import java.io.Serializable;

import model.*;

/**
 * This class contains the status of a player during his turn; booleans
 * attributes indicate if a player has already do a type of action or if a
 * player must do something
 * 
 * @author Nicola
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
	private boolean mustDraw; // must draw dangerous sector card
	private boolean mustDiscardItem;// must discard fourth itemCard
	private boolean mustNoise; // must select random coordinate for
								// "noise in any sector"

	/**
	 * 
	 * @param game
	 * @param player
	 */
	public GameStatus(Game game, Player player) {
		this.player = player;
		this.game = game;
		moved = false;
		attacked = false;
		mustDraw = false;
		mustDiscardItem = false;
		mustNoise = false;
	}

	/**
	 * @return the moved
	 */
	public boolean isMoved() {
		return moved;
	}

	/**
	 * @param moved
	 *            the moved to set
	 */
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	/**
	 * @return the attacked
	 */
	public boolean isAttacked() {
		return attacked;
	}

	/**
	 * @param attacked
	 *            the attacked to set
	 */
	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	/**
	 * @return the sedated
	 */
	public boolean isSedated() {
		return sedated;
	}

	/**
	 * @param sedated
	 *            the sedated to set
	 */
	public void setSedated(boolean sedated) {
		this.sedated = sedated;
	}

	/**
	 * @return the mustDraw
	 */
	public boolean isMustDraw() {
		return mustDraw;
	}

	/**
	 * @param mustDraw
	 *            the mustDraw to set
	 */
	public void setMustDraw(boolean mustDraw) {
		this.mustDraw = mustDraw;
	}

	/**
	 * @return the mustDiscardItem
	 */
	public boolean isMustDiscardItem() {
		return mustDiscardItem;
	}

	/**
	 * @param mustDiscardItem
	 *            the mustDiscardItem to set
	 */
	public void setMustDiscardItem(boolean mustDiscardItem) {
		this.mustDiscardItem = mustDiscardItem;
	}

	/**
	 * @return the mustNoise
	 */
	public boolean isMustNoise() {
		return mustNoise;
	}

	/**
	 * @param mustNoise
	 *            the mustNoise to set
	 */
	public void setMustNoise(boolean mustNoise) {
		this.mustNoise = mustNoise;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
}
