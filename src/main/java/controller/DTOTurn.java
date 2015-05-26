package controller;

import model.*;

public class DTOTurn {

	private Coordinate coordinate;
	private ItemCardType typeCard;
	private TypeOfAction typeOfAction;
	
	public DTOTurn(Coordinate coordinate, ItemCardType typeCard, TypeOfAction typeOfAction) {
		this.coordinate = coordinate;
		this.typeCard = typeCard;
		this.typeOfAction=typeOfAction;
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
