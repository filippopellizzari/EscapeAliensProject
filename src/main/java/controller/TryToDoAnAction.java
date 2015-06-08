package controller;

import dto.DTOGame;
import dto.DTOTurn;

/**
 * This interface is used to implements the pattern strategy
 * 
 * @author Nicola
 *
 */

public interface TryToDoAnAction {

	/**
	 * 
	 * @param dtoTurn
	 *            box with the action the player would do
	 * @return a string of action
	 */
	public DTOGame doAction(DTOTurn dtoTurn);
}
