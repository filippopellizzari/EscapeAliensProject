package gui;

import model.PlayerType;
import dto.DTOGame;

/**
 *This class is used to display the communication from server, in case of a
 * valid attack action 
 * 
 * @author Filippo
 *
 */
public class AttackMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		rp.getMessagePanel()
				.getTextArea()
				.append("<giocatore " + (dtoGame.getPlayerNumber() + 1) + ">"
						+ " ATTACCO IN SETTORE "
						+ dtoGame.getCoordinate(dtoGame.getPlayerNumber())
						+ "\n");
		
		for (int i = 0; i < dtoGame.getPlayerType().length; i++) {
			PlayerType type = dtoGame.getPlayerType(i);
			if (type != null) {
				rp.getMessagePanel()
						.getTextArea()
						.append("<giocatore " + (i + 1) + ">"
								+ " è stato attaccato e viene eliminato:\n"
								+ "era un " + type + "\n");
			}
		}
	}

}
