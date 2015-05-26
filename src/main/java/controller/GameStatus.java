package controller;

import java.io.Serializable;

import model.*;

/**
 * this class is used as DTO between Client and Server, when the game is start
 * 
 * @author Nicola
 *
 */

public class GameStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	private Player playerPlay;
	private boolean hasMoved; 
	private boolean hasAttacked; 
	private boolean solvedDangerous; // ha pescato la carta settore pericoloso
	private boolean mustDiscardItem; 
	private boolean noiseInAnySector; // ha pescato la carta noise in any sector
	private Game game;

	/**
	 * @param game
	 *            , reference to model
	 * @param playerPlay
	 *            , reference at player that has to play
	 */

	public GameStatus(Game game, Player playerPlay) {
		this.playerPlay = playerPlay;
		this.game = game;
		hasMoved = false;
		hasAttacked = false;
		solvedDangerous = false;
		mustDiscardItem = false;
		noiseInAnySector = false;
	}

	/**
	 * @return the playerPlay
	 */
	public Player getPlayerPlay() {
		return playerPlay;
	}

	/**
	 * @return the move
	 */
	public boolean isMove() {
		return hasMoved;
	}

	/**
	 * @return the attack
	 */
	public boolean isAttack() {
		return hasAttacked;
	}

	/**
	 * @return the solveSectorDuty
	 */
	public boolean isSolveSectorDuty() {
		return solvedDangerous;
	}

	/**
	 * @return the discardItemDuty
	 */
	public boolean isDiscardItemDuty() {
		return mustDiscardItem;
	}

	/**
	 * @return the noiseInAnySector
	 */
	public boolean isNoiseInAnySector() {
		return noiseInAnySector;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param playerPlay
	 *            the playerPlay setted
	 */
	public void setPlayerPlay(Player playerPlay) {
		this.playerPlay = playerPlay;
	}

	/**
	 * @param move
	 *            the move setted
	 */
	public void setMove(boolean move) {
		this.hasMoved = move;
	}

	/**
	 * @param attack
	 *            the attack setted
	 */
	public void setAttack(boolean attack) {
		this.hasAttacked = attack;
	}

	/**
	 * @param solveSectorDuty
	 *            the solveSectorDuty setted
	 */
	public void setSolveSectorDuty(boolean solveSectorDuty) {
		this.solvedDangerous = solveSectorDuty;
	}

	/**
	 * @param discardItemDuty
	 *            the discardItemDuty setted
	 */
	public void setDiscardItemDuty(boolean discardItemDuty) {
		this.mustDiscardItem = discardItemDuty;
	}

	/**
	 * @param noiseInAnySector
	 *            the noiseInAnySector setted
	 */
	public void setNoiseInAnySector(boolean noiseInAnySector) {
		this.noiseInAnySector = noiseInAnySector;
	}

	/**
	 * @param game
	 *            the game setted
	 */
	public void setGame(Game game) {
		this.game = game;
	}
}
