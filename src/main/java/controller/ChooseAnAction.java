package controller;

import dto.DTOGame;
import dto.DTOTurn;

/**
 * This is the interface for the pattern strategy, whathever action the controller does uses this method that 
 * returns a dtoGame, this is passed to client and/or to broker
 * @author Nicola
 *
 */

public interface ChooseAnAction {

	/**
	 * Does an action with the specified type, attack if the action type is ATTACK, ...
	 * @param dtoTurn
	 * @return the response of action
	 */
	public DTOGame doAction(DTOTurn dtoTurn);
}
