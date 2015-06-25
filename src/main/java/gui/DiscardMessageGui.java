package gui;

import dto.DTOGame;

public class DiscardMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		rp.getMessagePanel().getTextArea().append("<giocatore " + (dtoGame.getPlayerNumber()+1) + ">"
				+ " ha scartato una carta oggetto\n");

	}

}
