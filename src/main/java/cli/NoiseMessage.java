package cli;

import dto.DTOGame;

public class NoiseMessage implements Message{

	@Override
	public void receive(DTOGame dtoGame) {
		System.out.println("<giocatore " + (dtoGame.getPlayerNumber()+1) + ">"
				+ " RUMORE IN SETTORE "
				+ dtoGame.getCoordinate(dtoGame.getPlayerNumber()));
		
		if (dtoGame.getItemCardType() != null) {
			System.out.println("<giocatore " + (dtoGame.getPlayerNumber()+1)
					+ "> ha pescato una carta oggetto");
		}
		
	}

}
