package cli;

import dto.DTOGame;

/**
 * This is the interface of all classes used to receive communication from
 * server, in case of valid action (excepted message of endTurn and chat
 * message)
 * 
 * @author Filippo
 *
 */
public interface Message {

	/**
	 * This method receives data and analyzes them to display the right message
	 * 
	 * @param dtoGame
	 *             data from server
	 */
	public void receive(DTOGame dtoGame);
}
