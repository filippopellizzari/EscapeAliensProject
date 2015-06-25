package cli;

import dto.DTOGame;

public class MoveMessage implements Message{

	@Override
	public void receive(DTOGame dtoGame){
		System.out.println("Ti sei mosso con successo in settore "
				+ dtoGame.getCoordinate(dtoGame.getPlayerNumber()));
		if (dtoGame.getHatchCardColor() != null) {
			switch (dtoGame.getHatchCardColor()) {
			case RED:
				System.out
						.println("<giocatore "
								+ (dtoGame.getPlayerNumber()+1)
								+ ">"
								+ " pesca carta scialuppa rossa:\n il giocatore non si salva "
								+ "e il settore rimane bloccato");
				break;
			case GREEN:
				System.out
						.println("<giocatore "
								+ (dtoGame.getPlayerNumber()+1)
								+ ">"
								+ " pesca carta scialuppa verde:\n il giocatore ha vinto "
								+ "e il settore rimane bloccato");
				break;
			}
		}
	}
}
