package controller;


//DA SPIEGARE MEGLIO

import dto.DTOGame;
import dto.DTOTurn;

/**
 * 
 * 
 * @author Nicola
 *
 */

public interface ChooseAnAction {

	/**
	 * 
	 * @param dtoTurn
	 *       
	 * @return the response of action
	 */
	public DTOGame doAction(DTOTurn dtoTurn);
}
