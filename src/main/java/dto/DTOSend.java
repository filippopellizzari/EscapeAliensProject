package dto;

import java.io.Serializable;

import controller.ActionType;
import model.*;

/**
 * 
 * @author Nicola this is the DTO passed from client to server
 *
 */


public class DTOSend implements Serializable{
	private static final long serialVersionUID = 1L;

	private Coordinate coordinate;
	private int numberPlayer;
	private ItemCardType itemCardType;
	private String chat;
	private ActionType actionType;

	/**
	 * @param coordinate
	 *            of the sector selected
	 * @param numberPlayer
	 *            number of player in the game
	 * @param itemCardType
	 *            type of card used
	 * @param chat
	 *            message for the chat
	 * @param actionType
	 *            the action that the player would do
	 */

	public DTOSend(Coordinate coordinate, int numberPlayer,
			ItemCardType itemCardType, ActionType actionType, String chat) {
		this.coordinate = coordinate;
		this.numberPlayer = numberPlayer;
		this.itemCardType = itemCardType;
		this.chat = chat;
		this.actionType = actionType;
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

	public ItemCardType getItemCardType() {
		return itemCardType;
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

	public ActionType getActionType() {
		return actionType;
	}

	/**

	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @param typeCard the typeCard to set
	 */
	public void setItemCardType(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}

	/**
	 * @param chat the chat to set
	 */
	public void setChat(String chat) {
		this.chat = chat;
	}

	/**
	 * @param typeOfAction the typeOfAction to set
	 */
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	/**
	 * @param numberPlayer the numberPlayer to set
	 */
	public void setNumberPlayer(int numberPlayer) {
		this.numberPlayer = numberPlayer;
	}
	


}
