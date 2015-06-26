package gui;

import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid draw action
 * 
 * @author Filippo
 *
 */
public class DrawMessageGui implements MessageGui {

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp) {
		switch (dtoGame.getSectorCardType()) {
		case NOISEANY:
			displayNoiseAny(rp);
			break;
		case NOISEYOUR:
			displayNoiseYour(dtoGame, rp);
			break;
		case SILENCE:
			displaySilence(dtoGame, rp);
			break;
		default:
			break;
		}

	}
	
	private void displayNoiseAny(RightPanel rp){
		rp.getMessagePanel()
		.getTextArea()
		.append("Hai pescato una carta Noise in Any Sector:\n "
				+ "seleziona un settore a scelta\n");
	}
	
	private void displayNoiseYour(DTOGame dtoGame, RightPanel rp){
		rp.getLogPanel()
		.getTextArea()
		.append("<giocatore " + (dtoGame.getPlayerNumber() + 1)
				+ "> " + "RUMORE IN SETTORE "
				+ dtoGame.getCoordinate(dtoGame.getPlayerNumber())
				+ "\n");
	}
	
	private void displaySilence(DTOGame dtoGame, RightPanel rp){
		rp.getMessagePanel()
		.getTextArea()
		.append("<giocatore " + (dtoGame.getPlayerNumber() + 1)
				+ ">" + " SILENZIO IN TUTTI I SETTORI\n ");
	}

}
