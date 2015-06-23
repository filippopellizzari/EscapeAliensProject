package cli;

import dto.DTOGame;

public class DiscardMessage implements Message{

	@Override
	public void receive(DTOGame dtoGame) {
		System.out.println("<giocatore " + (dtoGame.getPlayerNumber()+1) + ">"
				+ " ha scartato una carta oggetto");
	}
}
