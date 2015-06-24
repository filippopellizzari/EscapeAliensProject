package dto;

import java.io.Serializable;

import controller.ActionType;
import model.*;

/**
 * This class is used as a DTO between Client and Server, when the game starts
 * this is the DTO passed from server to client
 * @author Nicola 
 *
 */

public class DTOGame implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Coordinate[] coordinate;
	private final PlayerType[] playerType;
	private ItemCardType itemCardType;
	private SectorCardType sectorCardType;
	private HatchCardColor hatchCardColor;
	private ActionType actionType;
	private String chat;
	private String gameMessage = "";
	private int receiver; //1-8 for single player, 9 broadcast, 10 caso particolare
	private int playerNumber;
	private int numberPlayerDefense;
	/**
	 * Create a new dtoGame
	 */

	public DTOGame() {
		this.coordinate = new Coordinate[8];
		this.playerType = new PlayerType[8];
	}

	/**
	 * @return the itemCardType
	 */
	
	public ItemCardType getItemCardType() {
		return itemCardType;
	}

	/**
	 * @param itemCardType the itemCardType to set
	 */
	
	public void setItemCardType(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}

	/**
	 * @return the sectorCardType
	 */
	
	public SectorCardType getSectorCardType() {
		return sectorCardType;
	}

	/**
	 * @param sectorCardType the sectorCardType to set
	 */
	
	public void setSectorCardType(SectorCardType sectorCardType) {
		this.sectorCardType = sectorCardType;
	}

	/**
	 * @return the hatchCardColor
	 */
	
	public HatchCardColor getHatchCardColor() {
		return hatchCardColor;
	}

	/**
	 * @param hatchCardColor the hatchCardColor to set
	 */
	
	public void setHatchCardColor(HatchCardColor hatchCardColor) {
		this.hatchCardColor = hatchCardColor;
	}

	/**
	 * @return the actionType
	 */
	
	public ActionType getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the chat
	 */
	
	public String getChat() {
		return chat;
	}

	/**
	 * @param chat the chat to set
	 */
	
	public void setChat(String chat) {
		this.chat = chat;
	}

	/**
	 * @return the gameMessage
	 */
	
	public String getGameMessage() {
		return gameMessage;
	}

	/**
	 * @param gameMessage the gameMessage to set
	 */
	
	public void setGameMessage(String gameMessage) {
		this.gameMessage += gameMessage;
	}

	/**
	 * @return the receiver
	 */
	
	public int getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver the receiver to set
	 */
	
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the playerNumber
	 */
	
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * @param playerNumber the playerNumber to set
	 */
	
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	/**
	 * @return the coordinate
	 */
	
	public Coordinate[] getCoordinate() {
		return coordinate;
	}
	
	/**
	 * @return the coordinate of the player
	 */
	public Coordinate getCoordinate(int number) {
		return coordinate[number];
	}

	/**
	 * @return the playerType
	 */
	
	public PlayerType[] getPlayerType() {
		return playerType;
	}
	
	/**
	 * @param coordinate
	 * @param number
	 */
	
	public void setCoordinate(Coordinate coordinate, int number) {
		this.coordinate[number] = coordinate;
	}
	
	/**
	 * @param type
	 * @param number
	 */

	public void setPlayerType(PlayerType type, int number) {
		playerType[number]=type;

	}
	
	/**
	 * @return playerType of player number
	 */
	public PlayerType getPlayerType(int number) {
		return playerType[number];		
	}
	/**
	 * number of Player who has defended himself
	 * 
	 * @return
	 */
	public int getNumberPlayerDefense() {
		return numberPlayerDefense;
	}
	/**
	 * 
	 * @param numberPlayerDefense
	 */
	public void setNumberPlayerDefense(int numberPlayerDefense) {
		this.numberPlayerDefense = numberPlayerDefense;
	}
}
