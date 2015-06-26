package gui;

import dto.DTOGame;

/**
 * This is the interface of all classes used to receive communication from
 * server, in case of valid action (excepted message of endTurn and chat
 * message)
 * 
 * @author Filippo
 *
 */
public interface MessageGui {

	/**
	 * This method receives data and analyzes them to display the right message
	 * in the logPanels or in the messagePanel
	 * 
	 * @param dtoGame
	 *            , data from the server
	 * @param rp
	 *            , reference to rightPanel
	 */
	public void receive(DTOGame dtoGame, RightPanel rp);

}
