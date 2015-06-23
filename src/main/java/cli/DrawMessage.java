package cli;

import dto.DTOGame;

public class DrawMessage implements Message {

	@Override
	public void receive(DTOGame dtoGame) {
		switch (dtoGame.getSectorCardType()) {
		case NOISEANY:
			System.out.println("Hai pescato una carta Noise in Any Sector:\n "
					+ "seleziona un settore a scelta");
			break;
		case NOISEYOUR:
			System.out.println("<giocatore " + dtoGame.getPlayerNumber() + "> "
					+ "RUMORE IN SETTORE "
					+ dtoGame.getCoordinate()[dtoGame.getPlayerNumber()]);
			break;
		case SILENCE:
			System.out.println("<giocatore " + dtoGame.getPlayerNumber() + ">"
					+ " SILENZIO IN TUTTI I SETTORI ");
			break;
		default:
			break;
		}
		if (dtoGame.getItemCardType() != null) {
			System.out.println("<giocatore " + dtoGame.getPlayerNumber()
					+ "> ha pescato una carta oggetto");
		}

	}

}
