package dto;

import controller.TypeOfAction;
import model.*;

/**
 * This class is a small box of data passed from server to gameController to
 * perform a turn of a game
 * 
 * @author Nicola
 *
 */

public class DTOTurn {

	private Coordinate coordinate;
	private ItemCardType typeCard;
	private TypeOfAction typeOfAction;

	/**
	 * 
	 * @param coordinate
	 *            , coordinate of a sector
	 * @param typeCard
	 *            , type of a item card, players can't use other card
	 * @param typeOfAction
	 *            , type of action the player would do
	 */

	public DTOTurn(Coordinate coordinate, ItemCardType typeCard,
			TypeOfAction typeOfAction) {
		this.coordinate = coordinate;
		this.typeCard = typeCard;
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
	 * @return the typeOfAction
	 */

	public TypeOfAction getTypeOfAction() {
		return typeOfAction;
	}
}
