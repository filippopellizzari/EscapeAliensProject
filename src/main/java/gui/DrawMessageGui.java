package gui;

import dto.DTOGame;

public class DrawMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		switch (dtoGame.getSectorCardType()) {
		case NOISEANY:
			rp.getMessagePanel().getTextArea().append("Hai pescato una carta Noise in Any Sector:\n "
					+ "seleziona un settore a scelta\n");
			break;
		case NOISEYOUR:
			rp.getLogPanel().getTextArea().append("<giocatore " + (dtoGame.getPlayerNumber()+1) + "> "
					+ "RUMORE IN SETTORE "
					+ dtoGame.getCoordinate(dtoGame.getPlayerNumber())+"\n");
			break;
		case SILENCE:
			rp.getMessagePanel().getTextArea().append("<giocatore " + (dtoGame.getPlayerNumber()+1) + ">"
					+ " SILENZIO IN TUTTI I SETTORI\n ");
			break;
		default:
			break;
		}

	}

}
