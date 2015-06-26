package gui;

import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid noise in any sector action
 * 
 * @author Filippo
 *
 */
public class NoiseMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		rp.getLogPanel()
				.getTextArea()
				.append("<giocatore " + (dtoGame.getPlayerNumber() + 1) + ">"
						+ " RUMORE IN SETTORE "
						+ dtoGame.getCoordinate(dtoGame.getPlayerNumber())
						+ "\n");

	}

}
