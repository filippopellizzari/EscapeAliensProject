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
	private ItemCardType itemCardType;
	private boolean attack;
	private boolean move;
	private boolean useItem;
	private boolean endTurn;
	private boolean error;
	private boolean notifyAll;
	private String chat;
	private String gameMessage;
	/**
	 * 
	 * @param coordinate of the sector selected 
	 * @param numberGame number of game where this action are do
	 * @param numberPlayer number of player in the game
	 * @param itemCardType	type of card used 
	 * @param attack if is true the player attack, if it is possible
	 * @param move if the player has attacked
	 * @param useItem if the player has used a card
	 * @param chat	message for the chat
	 * @param gameMessage	message provide for the game
	 * 
	 */
	
	public DTOGame(Coordinate coordinate, ItemCardType itemCardType, boolean attack, boolean move, boolean useItem, String chat, 
			String gameMessage, boolean endTurn, boolean error, boolean notifyAll) {
		super();
		this.coordinate = coordinate;
		this.itemCardType = itemCardType;
		this.attack = attack;
		this.move = move;
		this.useItem = useItem;
		this.chat = chat;
		this.gameMessage = gameMessage;
		this.endTurn = endTurn;
		this.error = error;
		this.notifyAll = notifyAll;
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
		return itemCardType;
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
	 * 
	 * @return the endTurn
	 */
	
	public boolean getEndTurn() {
		return endTurn;
	}

	/**
	 * @return the move
	 */
	
	public boolean isMove() {
		return move;
	}

	/**
	 * @return the useCard
	 */
	
	public boolean wantsToUseItem() {
		return useItem;
	}
	
	/**
	 * @return the error
	 */
	
	public boolean isError() {
		return error;
	}

	/**
	 * @return the notifyAll
	 */
	
	public boolean isNotifyAll() {
		return notifyAll;
	}
}
