package controller;

import java.io.Serializable;

import model.Coordinate;
import model.ItemCardType;

/**
 * this class is used as DTO between Client and Server, when the game is start
 * @author Nicola
 *
 */

public class TurnDTO implements Serializable{
	private Coordinate coordinate;
	private ItemCardType typeCard;
	private boolean attack;
	private boolean endTurn;
	private String gameMessage;
	
	/**
	 * 
	 * @param coordinate of the sector selected 
	 * @param numberGame number of game where this action are do
	 * @param numberPlayer number of player in the game
	 * @param typeCard	type of card used 
	 * @param attack if is true the player attack, if it is possible
	 * @param chat	message for the chat
	 * @param gameMessage	message provide for the game
	 */
	
	public TurnDTO(Coordinate coordinate, ItemCardType typeCard, boolean attack, String gameMessage) {
		super();
		this.coordinate = coordinate;
		this.typeCard = typeCard;
		this.attack = attack;
		this.gameMessage=gameMessage;
	}
	
	/**
	 * 
	 * @return the endTurn
	 */
	
	public boolean getEndTurn() {
		return endTurn;
	}

	/**
	 * @return the coordinate
	 */
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @return the typeCard
	 */
	
	public ItemCardType getTypeCard() {
		return typeCard;
	}

	/**
	 * @return the attack
	 */
	
	public boolean isAttack() {
		return attack;
	}

	/**
	 * @return the gameMessage
	 */
	
	public String getGameMessage() {
		return gameMessage;
	}
}
