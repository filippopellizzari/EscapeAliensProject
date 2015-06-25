package cli;

import dto.DTOGame;

/**
 * This class is used to display the communication from server, in case of a
 * valid draw action
 * 
 * @author Filippo
 *
 */
public class DrawMessage implements Message {

	@Override
	public void receive(DTOGame dtoGame) {
		switch (dtoGame.getSectorCardType()) {
		case NOISEANY:
			System.out.println("Hai pescato una carta Noise in Any Sector:\n "
					+ "seleziona un settore a scelta");
			break;
		case NOISEYOUR:
			System.out.println("<giocatore " + (dtoGame.getPlayerNumber() + 1)
					+ "> " + "RUMORE IN SETTORE "
					+ dtoGame.getCoordinate(dtoGame.getPlayerNumber()));
			break;
		case SILENCE:
			System.out.println("<giocatore " + (dtoGame.getPlayerNumber() + 1)
					+ ">" + " SILENZIO IN TUTTI I SETTORI ");
			break;
		default:
			break;
		}
		

	}

}
