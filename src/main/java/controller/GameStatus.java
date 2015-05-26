package controller;

import java.io.Serializable;

import model.*;

/**
 * this class is used as DTO between Client and Server, when the game is start
 * @author Nicola
 *
 */

public class GameStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	private Player playerPlay;
	private boolean move;						//ha mosso
	private boolean attack;						//ha attaccato
	private boolean solveSectorDuty;			//ha pescato la carta settore pericoloso
	private boolean discardItemDuty;					//deve scartare
	private boolean noiseInAnySector;			//ha pescato la carta noise in any sector
	private Game game;
	
	/**
	 * @param game, reference to model
	 * @param playerPlay, reference at player that has to play
	 */
	
	public GameStatus(Game game, Player playerPlay) {
		this.playerPlay = playerPlay;
		this.game = game;
		move=false;
		attack=false;
		solveSectorDuty=false;
		discardItemDuty=false;
		noiseInAnySector=false;
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
		return move;
	}
	/**
	 * @return the attack
	 */
	public boolean isAttack() {
		return attack;
	}
	/**
	 * @return the solveSectorDuty
	 */
	public boolean isSolveSectorDuty() {
		return solveSectorDuty;
	}
	/**
	 * @return the discardItemDuty
	 */
	public boolean isDiscardItemDuty() {
		return discardItemDuty;
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
	 * @param playerPlay the playerPlay to set
	 */
	public void setPlayerPlay(Player playerPlay) {
		this.playerPlay = playerPlay;
	}
	/**
	 * @param move the move to set
	 */
	public void setMove(boolean move) {
		this.move = move;
	}
	/**
	 * @param attack the attack to set
	 */
	public void setAttack(boolean attack) {
		this.attack = attack;
	}
	/**
	 * @param solveSectorDuty the solveSectorDuty to set
	 */
	public void setSolveSectorDuty(boolean solveSectorDuty) {
		this.solveSectorDuty = solveSectorDuty;
	}
	/**
	 * @param discardItemDuty the discardItemDuty to set
	 */
	public void setDiscardItemDuty(boolean discardItemDuty) {
		this.discardItemDuty = discardItemDuty;
	}
	/**
	 * @param noiseInAnySector the noiseInAnySector to set
	 */
	public void setNoiseInAnySector(boolean noiseInAnySector) {
		this.noiseInAnySector = noiseInAnySector;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}	
}
