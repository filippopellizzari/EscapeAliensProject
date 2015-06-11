package dto;

import controller.ActionType;
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
	private ItemCardType itemCardType;
	private ActionType actionType;

	/**
	 * 
	 * @param coordinate
	 *            , coordinate of a sector
	 * @param itemCardType
	 *            , type of a item card, players can't use other card
	 * @param actionType
	 *            , type of action the player would do
	 */

	public DTOTurn(Coordinate coordinate, ItemCardType itemCardType,
			ActionType actionType) {
		this.coordinate = coordinate;
		this.itemCardType = itemCardType;
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

	public ItemCardType getTypeCard() {
		return itemCardType;
	}

	/**
	 * @return the typeOfAction
	 */

	public ActionType getActionType() {
		return actionType;
	}
}
