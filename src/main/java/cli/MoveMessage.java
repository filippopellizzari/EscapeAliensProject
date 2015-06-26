package cli;

import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid move action
 * 
 * @author Filippo
 *
 */
public class MoveMessage implements Message {

	@Override
	public void receive(DTOGame dtoGame) {
		System.out.println("Ti sei mosso con successo in settore "
				+ dtoGame.getCoordinate(dtoGame.getPlayerNumber()));
		if (dtoGame.getHatchCardColor() != null) {
			switch (dtoGame.getHatchCardColor()) {
			case RED:
				displayRed(dtoGame);
				break;
			case GREEN:
				displayGreen(dtoGame);
				break;
			default:
				break;
			}
		}
	}

	private void displayRed(DTOGame dtoGame) {
		System.out.println("<giocatore " + (dtoGame.getPlayerNumber() + 1)
				+ ">"
				+ " pesca carta scialuppa rossa:\n il giocatore non si salva "
				+ "e il settore rimane bloccato");
	}

	private void displayGreen(DTOGame dtoGame) {
		System.out.println("<giocatore " + (dtoGame.getPlayerNumber() + 1)
				+ ">"
				+ " pesca carta scialuppa verde:\n il giocatore ha vinto "
				+ "e il settore rimane bloccato");
	}

}
