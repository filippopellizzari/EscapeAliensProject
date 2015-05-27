package dto;

import java.io.Serializable;

import controller.TypeOfAction;
import model.*;

/**
 * this class is used as DTO between Client and Server, when the game starts
 * 
 * @author Nicola this is the DTO passed from server to client
 *
 */

public class DTOGame implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Coordinate[] coordinate;
	private ItemCardType typeItemCard;
	private HatchCardColor hatchCardColor;
	private SectorCardType sectorType;
	private TypeOfAction typeOfAction;
	private String chat;
	private String gameMessage;
	private int destination;
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the coordinate
	 */
	public Coordinate[] getCoordinate() {
		return coordinate;
	}
	/**
	 * @return the typeItemCard
	 */
	public ItemCardType getTypeItemCard() {
		return typeItemCard;
	}
	/**
	 * @return the hatchCardColor
	 */
	public HatchCardColor getHatchCardColor() {
		return hatchCardColor;
	}
	/**
	 * @return the sectorType
	 */
	public SectorCardType getSectorType() {
		return sectorType;
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
	 * @return the destination
	 */
	public int getDestination() {
		return destination;
	}
	/**
	 * @param coordinate of the sector selected, each is for a specific player
	 * @param number, number of coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate, int number) {
		this.coordinate[number] = coordinate;
	}
	/**
	 * * @param typeItemCard type of item card used/draw
	 */
	public void setTypeItemCard(ItemCardType typeItemCard) {
		this.typeItemCard = typeItemCard;
	}
	/**
	 * @param hatchCardColor the hatchCardColor to set
	 */
	public void setHatchCardColor(HatchCardColor hatchCardColor) {
		this.hatchCardColor = hatchCardColor;
	}
	/**
	 * @param type type of sector card draw
	 */
	public void setSectorType(SectorCardType type) {
		this.sectorType = type;
	}
	/**
	 * @param typeOfAction action that the player has done
	 */
	public void setTypeOfAction(TypeOfAction typeOfAction) {
		this.typeOfAction = typeOfAction;
	}
	/**
	 * @param chat the chat to set
	 */
	public void setChat(String chat) {
		this.chat += chat;
	}
	/**
	 * @param gameMessage, message for the game
	 */
	public void setGameMessage(String gameMessage) {
		this.gameMessage += gameMessage;
	}
	/**
	 * @param destination, 1-8 means for a specific player 9 is broadcast
	 */
	public void setDestination(int destination) {
		this.destination = destination;
	}
}
