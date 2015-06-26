package gui;

import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid move action
 * 
 * @author Filippo
 *
 */
public class MoveMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		rp.getLogPanel()
				.getTextArea()
				.append("Ti sei mosso con successo in settore "
						+ dtoGame.getCoordinate(dtoGame.getPlayerNumber())
						+ "\n");
		if (dtoGame.getHatchCardColor() != null) {
			switch (dtoGame.getHatchCardColor()) {
			case RED:
				displayRed(dtoGame, rp);
				break;
			case GREEN:
				displayGreen(dtoGame, rp);
				break;
			default:
				break;
			}
		}
	}
	
	private void displayRed(DTOGame dtoGame, RightPanel rp){
		rp.getMessagePanel()
		.getTextArea()
		.append("<giocatore "
				+ (dtoGame.getPlayerNumber() + 1)
				+ ">"
				+ " pesca carta scialuppa rossa:\n il giocatore non si salva "
				+ "e il settore rimane bloccato\n");
	}
	
	private void displayGreen(DTOGame dtoGame, RightPanel rp){
		rp.getMessagePanel()
		.getTextArea()
		.append("<giocatore "
				+ (dtoGame.getPlayerNumber() + 1)
				+ ">"
				+ " pesca carta scialuppa verde:\n il giocatore ha vinto "
				+ "e il settore rimane bloccato\n");
	}
}
