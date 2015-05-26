package controller;

import java.io.Serializable;

import model.Coordinate;
import model.ItemCardType;

/**
 * this class is used as DTO between Client and Server, when the game starts
 * @author Nicola
 * this is the DTO passed from server to client
 *
 */

public class DTOGame implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coordinate coordinate;
	private ItemCardType typeCard;
	private TypeOfAction typeOfAction;
	private String chat;
	private String gameMessage;
	private int numberOfPlayer;
	
	/**
	 * 
	 * @param coordinate of the sector selected 
	 * @param typeCard type of card used 
	 * @param typeOfAction action that the player has done
	 * @param chat	message for the chat
	 * @param gameMessage	message provide for the game
	 * @param numberOfPlayer number of player in the game
	 */
	
	public DTOGame(Coordinate coordinate, ItemCardType typeCard,
			TypeOfAction typeOfAction, String chat, String gameMessage, int numberOfPlayer) {
		this.coordinate = coordinate;
		this.typeCard = typeCard;
		this.typeOfAction = typeOfAction;
		this.chat = chat;
		this.gameMessage = gameMessage;
		this.numberOfPlayer=numberOfPlayer;
	}	
}
