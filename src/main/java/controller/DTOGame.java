package controller;

import java.io.Serializable;

import model.Coordinate;
import model.ItemCardType;

/**
 * this class is used as DTO between Client and Server, when the game starts
 * @author Nicola
 *
 */

public class DTOGame implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coordinate coordinate;
	private final int numberGame;
	private final int numberPlayer;
	private ItemCardType typeCard;
	private boolean attack;
	private String chat;
	private String gameMessage;
	private boolean endTurn;
	
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
	
	public DTOGame(Coordinate coordinate, int numberGame, int numberPlayer,
			ItemCardType typeCard, boolean attack, String chat, String gameMessage, boolean endTurn) {
		super();
		this.coordinate = coordinate;
		this.numberGame = numberGame;
		this.numberPlayer = numberPlayer;
		this.typeCard = typeCard;
		this.attack = attack;
		this.chat = chat;
		this.gameMessage=gameMessage;
		this.endTurn=endTurn;
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
	 * @return the chat
	 */
	
	public String getChat() {
		return chat;
	}

	/**
	 * @return the gameMessage
	 */
	
	public String getGameMessage() {
		return gameMessage;
	}

	/**
	 * @return the numberGame
	 */
	
	public int getNumberGame() {
		return numberGame;
	}

	/**
	 * @return the numberPlayer
	 */
	
	public int getNumberPlayer() {
		return numberPlayer;
	}
	
	/**
	 * 
	 * @return the endTurn
	 */
	
	public boolean getEndTurn() {
		return endTurn;
	}
}
