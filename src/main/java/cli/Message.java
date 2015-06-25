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

	public void receive(DTOGame dtoGame);
}
