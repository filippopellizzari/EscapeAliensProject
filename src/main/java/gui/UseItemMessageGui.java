package gui;

import model.Coordinate;
import model.PlayerType;
import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid use Item Card action
 * 
 * @author Filippo
 *
 */
public class UseItemMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		rp.getMessagePanel()
				.getTextArea()
				.append("<giocatore " + (dtoGame.getPlayerNumber() + 1) + ">"
						+ " ha usato la carta " + dtoGame.getItemCardType()
						+ "\n");
		switch (dtoGame.getItemCardType()) {
		case ATTACK:
			displayAttack(dtoGame, rp);
			break;
		case SPOTLIGHT:
			displaySpotlight(dtoGame, rp);
			break;
		default:
			break;
		}

	}

	private void displayAttack(DTOGame dtoGame, RightPanel rp) {
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
	
	private void displaySpotlight(DTOGame dtoGame, RightPanel rp){
		for (int i = 0; i < dtoGame.getCoordinate().length; i++) {
			Coordinate coord = dtoGame.getCoordinate(i);
			if (coord != null) {
				rp.getLogPanel()
						.getTextArea()
						.append("<giocatore " + (i + 1) + ">"
								+ " si trova nel settore" + coord + "\n");
			}
		}
	}

}
