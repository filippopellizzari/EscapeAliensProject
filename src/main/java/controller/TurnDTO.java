package controller;

import java.io.Serializable;

import model.Coordinate;
import model.ItemCardType;

/**
 * this class is used as DTO between Client and Server, when the game is start
 * @author Nicola
 *
 */

public class TurnDTO implements Serializable{
	private Coordinate coordinate;
	private ItemCardType typeCard;
	private boolean attack;
	private boolean endTurn;
	private boolean useCard;
	private boolean move;
	
	/**
	 * 
	 * @param coordinate of the sector selected 
	 * @param typeCard	type of card used 
	 * @param attack, the player try to attack
	 * @param end turn if the player has finish
	 * @param useCard, the player would use a card
	 * @param move, the player would move
	 */
	public TurnDTO(Coordinate coordinate, ItemCardType typeCard, boolean attack, boolean endTurn, boolean useCard, boolean move) {
		this.coordinate = coordinate;
		this.typeCard = typeCard;
		this.attack = attack;
		this.endTurn=endTurn;
		this.useCard=useCard;
		this.move=move;
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
	 * @return the boolean endTurn
	 */
	
	public boolean isEndTurn() {
		return endTurn;
	}
	
	/**
	 * @return the useCard
	 */
	
	public boolean isUseCard() {
		return useCard;
	}

	/**
	 * @return the move
	 */
	
	public boolean isMove() {
		return move;
	}
}
