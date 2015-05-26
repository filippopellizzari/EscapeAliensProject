package controller;

import model.Coordinate;
import model.ItemCardType;

/**
 * 
 * @author Nicola
 * this is the DTO passed from client to server
 *
 */

public class DTOSend {
	
	private Coordinate coordinate;
	private final int numberGame;
	private final int numberPlayer;
	private ItemCardType typeCard;
	private String chat;
	private TypeOfAction typeOfAction;
	
	/**
	 * @param coordinate of the sector selected 
	 * @param numberGame number of game where this action are do
	 * @param numberPlayer number of player in the game
	 * @param typeCard	type of card used 
	 * @param chat	message for the chat
	 * @param typeOfAction the action that the player would do
	 */
	
	public DTOSend(Coordinate coordinate, int numberGame, int numberPlayer,
			ItemCardType typeCard, TypeOfAction typeOfAction) {
		this.coordinate = coordinate;
		this.numberGame = numberGame;
		this.numberPlayer = numberPlayer;
		this.typeCard = typeCard;
		this.chat = chat;
		this.typeOfAction=typeOfAction;
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
	 * @return the chat
	 */
	
	public String getChat() {
		return chat;
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
	 * @return the typeOfAction
	 */
	
	public TypeOfAction getTypeOfAction() {
		return typeOfAction;
	}
	
}
