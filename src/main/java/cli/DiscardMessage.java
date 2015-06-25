package cli;

import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid discard Item Card action
 * 
 * @author Filippo
 *
 */
public class DiscardMessage implements Message {

	@Override
	public void receive(DTOGame dtoGame) {
		System.out.println("<giocatore " + (dtoGame.getPlayerNumber() + 1)
				+ ">" + " ha scartato una carta oggetto");
	}
}
