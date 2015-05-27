package dto;

import java.io.Serializable;

import controller.TypeOfAction;
import model.Coordinate;
import model.ItemCardType;

/**
 * this class is used as DTO between Client and Server, when the game starts
 * 
 * @author Nicola this is the DTO passed from server to client
 *
 */

public class DTOGame implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Coordinate coordinate;
	private final ItemCardType typeCard;
	private final TypeOfAction typeOfAction;
	private final String chat;
	private final String gameMessage;
	private final int numberOfPlayer;

	/**
	 * 
	 * @param coordinate
	 *            of the sector selected
	 * @param typeCard
	 *            type of card used
	 * @param typeOfAction
	 *            action that the player has done
	 * @param chat
	 *            message for the chat
	 * @param gameMessage
	 *            message provide for the game
	 * @param numberOfPlayer
	 *            number of player in the game
	 */

	public DTOGame(Coordinate coordinate, ItemCardType typeCard,
			TypeOfAction typeOfAction, String chat, String gameMessage,
			int numberOfPlayer) {
		this.coordinate = coordinate;
		this.typeCard = typeCard;
		this.typeOfAction = typeOfAction;
		this.chat = chat;
		this.gameMessage = gameMessage;
		this.numberOfPlayer = numberOfPlayer;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the typeOfAction
	 */
	public TypeOfAction getTypeOfAction() {
		return typeOfAction;
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
	 * @return the numberOfPlayer
	 */
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

}
