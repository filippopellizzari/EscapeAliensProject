package controller;

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
	public String doAction(DTOTurn dtoTurn);
}
