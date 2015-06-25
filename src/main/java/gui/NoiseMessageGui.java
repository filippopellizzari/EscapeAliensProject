package gui;

import dto.DTOGame;

public class NoiseMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		rp.getLogPanel().getTextArea().append("<giocatore " + (dtoGame.getPlayerNumber()+1) + ">"
				+ " RUMORE IN SETTORE "
				+ dtoGame.getCoordinate(dtoGame.getPlayerNumber())+"\n");
		
		if (dtoGame.getItemCardType() != null) {
			rp.getMessagePanel().getTextArea().append("<giocatore " + (dtoGame.getPlayerNumber()+1)
					+ "> ha pescato una carta oggetto\n");
		}

	}

}