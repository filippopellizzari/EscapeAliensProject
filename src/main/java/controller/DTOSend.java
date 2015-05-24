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
	private boolean attack;
	private String chat;
	private boolean endTurn;
	private boolean move;
	private boolean useCard;		//se vero usa la carta se no la scarta
	
	/**
	 * @param coordinate of the sector selected 
	 * @param numberGame number of game where this action are do
	 * @param numberPlayer number of player in the game
	 * @param typeCard	type of card used 
	 * @param attack if is true the player attack, if it is possible
	 * @param chat	message for the chat
	 * @param endTurn if player would pass his turn or draw the one sectorCard
	 * @param move if player would move
	 * @param useCard if player has use one card
	 */
	
	public DTOSend(Coordinate coordinate, int numberGame, int numberPlayer,
			ItemCardType typeCard, boolean attack, String chat, boolean endTurn, boolean move, boolean useCard) {
		this.coordinate = coordinate;
		this.numberGame = numberGame;
		this.numberPlayer = numberPlayer;
		this.typeCard = typeCard;
		this.attack = attack;
		this.chat = chat;
		this.endTurn=endTurn;
		this.move=move;
		this.useCard=useCard;
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
	
	public boolean isUseCard() {
		return useCard;
	}

}
