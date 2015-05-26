package controller;

import java.io.Serializable;

import model.Coordinate;
import model.ItemCardType;

/**
 * this class is used as DTO between Client and Server, when the game is start
 * @author Nicola
 *
 */

public class DTOTurn implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Coordinate coordinate;
	private ItemCardType itemCardType;
	private boolean attack;
	private boolean endTurn;
	private boolean useItem;
	private boolean move;
	
	/**
	 * 
	 * @param coordinate coordinate of the sector selected 
	 * @param itemCardType	type of card used 
	 * @param attack, the player try to attack
	 * @param end turn if the player has finish
	 * @param useCard, the player would use a card
	 * @param move, the player would move
	 */
	public DTOTurn(Coordinate coordinate, ItemCardType itemCardType, boolean attack, boolean endTurn, boolean useItem, boolean move) {
		this.coordinate = coordinate;
		this.itemCardType = itemCardType;
		this.attack = attack;
		this.endTurn = endTurn;
		this.useItem = useItem;
		this.move = move;
	}
	
	/**
	 * 
	 * @return the endTurn
	 */
	
	public boolean getEndTurn() {
		return endTurn;
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
	 * @return the boolean endTurn
	 */
	
	public boolean isEndTurn() {
		return endTurn;
	}
	
	/**
	 * @return the useCard
	 */
	
	public boolean wantsToUseItem() {
		return useItem;
	}

	/**
	 * @return the move
	 */
	
	public boolean isMove() {
		return move;
	}
}
