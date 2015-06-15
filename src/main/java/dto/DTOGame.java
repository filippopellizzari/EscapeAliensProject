package dto;

import java.io.Serializable;

import controller.ActionType;
import model.*;

/**
 * this class is used as a DTO between Client and Server, when the game starts
 * 
 * @author Nicola this is the DTO passed from server to client
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
	private String gameMessage;
	private int receiver; //1-8 for single player, 9 broadcast, 10 caso particolare
	private int playerNumber;

	public DTOGame() {
		this.coordinate = new Coordinate[8];
		this.playerType = new PlayerType[8];
	}

	public Coordinate[] getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate, int number) {
		this.coordinate[number] = coordinate;
	}


	public PlayerType[] getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType type, int number) {
		playerType[number] = type;
	}

	public ItemCardType getItemCardType() {
		return itemCardType;
	}

	public void setItemCardType(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}

	public SectorCardType getSectorCardType() {
		return sectorCardType;
	}

	public void setSectorCardType(SectorCardType sectorCardType) {
		this.sectorCardType = sectorCardType;
	}

	public HatchCardColor getHatchCardColor() {
		return hatchCardColor;
	}

	public void setHatchCardColor(HatchCardColor hatchCardColor) {
		this.hatchCardColor = hatchCardColor;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public String getGameMessage() {
		return gameMessage;
	}

	public void setGameMessage(String gameMessage) {
		this.gameMessage = gameMessage;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
}
