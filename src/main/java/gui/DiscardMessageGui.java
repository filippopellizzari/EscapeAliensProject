package gui;

import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid discard Item Card action
 * 
 * @author Filippo
 *
 */
public class DiscardMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		rp.getMessagePanel()
				.getTextArea()
				.append("<giocatore " + (dtoGame.getPlayerNumber() + 1) + ">"
						+ " ha scartato una carta oggetto\n");

	}

}
