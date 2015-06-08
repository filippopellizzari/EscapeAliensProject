package dto;

import java.io.Serializable;

import controller.TypeOfAction;
import model.*;

/**
 * 
 * @author Nicola this is the DTO passed from client to server
 *
 */

public class DTOSend implements Serializable {

	private Coordinate coordinate;
	private final int numberPlayer;
	private ItemCardType typeCard;
	private String chat;
	private TypeOfAction typeOfAction;

	/**
	 * @param coordinate
	 *            of the sector selected
	 * @param numberPlayer
	 *            number of player in the game
	 * @param typeCard
	 *            type of card used
	 * @param chat
	 *            message for the chat
	 * @param typeOfAction
	 *            the action that the player would do
	 */

	public DTOSend(Coordinate coordinate, int numberPlayer,
			ItemCardType typeCard, TypeOfAction typeOfAction, String chat) {
		this.coordinate = coordinate;
		this.numberPlayer = numberPlayer;
		this.typeCard = typeCard;
		this.chat = chat;
		this.typeOfAction = typeOfAction;
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

	/**
	 * 
	 * @param coordinate
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * 
	 * @param typeCard
	 */
	public void setTypeCard(ItemCardType typeCard) {
		this.typeCard = typeCard;
	}

	/**
	 * 
	 * @param chat
	 */
	public void setChat(String chat) {
		this.chat = chat;
	}

	/**
	 * 
	 * @param typeOfAction
	 */
	public void setTypeOfAction(TypeOfAction typeOfAction) {
		this.typeOfAction = typeOfAction;
	}

}
