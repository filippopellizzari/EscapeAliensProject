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
	private ItemCardType itemCardType;
	private boolean attack;
	private String chat;
	private boolean endTurn;
	private boolean move;
	private boolean useItem;		//se vero usa la carta se no la scarta
	
	/**
	 * @param coordinate of the sector selected 
	 * @param numberGame number of game where this action are do
	 * @param numberPlayer number of player in the game
	 * @param itemCardType	type of card used 
	 * @param attack if is true the player attack, if it is possible
	 * @param chat	message for the chat
	 * @param endTurn if player would pass his turn or draw the one sectorCard
	 * @param move if player want to move
	 * @param useItem if player has used one card
	 */
	
	public DTOSend(Coordinate coordinate, int numberGame, int numberPlayer,
			ItemCardType itemCardType, boolean attack, String chat, boolean endTurn, boolean move, boolean useItem) {
		this.coordinate = coordinate;
		this.numberGame = numberGame;
		this.numberPlayer = numberPlayer;
		this.itemCardType = itemCardType;
		this.attack = attack;
		this.chat = chat;
		this.endTurn = endTurn;
		this.move = move;
		this.useItem = useItem;
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

}
