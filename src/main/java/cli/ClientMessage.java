package cli;

import model.Coordinate;
import model.PlayerType;
import dto.DTOGame;

/**
 * This class is used to analyze a single dtoGame to communicate the correct
 * message to client
 * 
 * @author Filippo
 *
 */
public class ClientMessage {
	
	private int numberOfPlayer;
	
	public ClientMessage(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}
	
	public void receive(DTOGame dtoGame) {
		if (dtoGame.getActionType() != null) {
			switch (dtoGame.getActionType()) {
			case MOVE:
				moveMessage(dtoGame);
				break;
			case ATTACK:
				attackMessage(dtoGame);
				break;
			case USEITEM:
				useItemMessage(dtoGame);
				break;
			case DISCARDITEM:
				discardMessage(dtoGame);
				break;
			case DRAWSECTORCARD:
				drawMessage(dtoGame);
				break;
			case SELECTSECTORNOISE:
				noiseMessage(dtoGame);
				break;
			case ENDTURN:
				endTurnMessage(dtoGame);
				break;
			default:
				break;
			}

		} else {
			// messaggio di errore
			System.out.println(dtoGame.getGameMessage());
		}
		
		chatMessage(dtoGame);
	}
	
	/**
	 * 
	 * @param dtoGame
	 */
	private void moveMessage(DTOGame dtoGame){
		System.out.println("<giocatore " + dtoGame.getPlayerNumber()
				+ ">" + " mosso con successo in settore "
				+ dtoGame.getCoordinate()[dtoGame.getPlayerNumber()]);
		if (dtoGame.getHatchCardColor() != null) {
			switch (dtoGame.getHatchCardColor()) {
			case RED:
				System.out
						.println("<giocatore "
								+ dtoGame.getPlayerNumber()
								+ ">"
								+ " pesca carta scialuppa rossa:\n il giocatore non si salva "
								+ "e il settore rimane bloccato");
				break;
			case GREEN:
				System.out
						.println("<giocatore "
								+ dtoGame.getPlayerNumber()
								+ ">"
								+ " pesca carta scialuppa verde:\n il giocatore ha vinto "
								+ "e il settore rimane bloccato");
				break;
			}
		}
	}
	/**
	 * 
	 * @param dtoGame
	 */
	private void attackMessage(DTOGame dtoGame){
		System.out.println("<giocatore " + dtoGame.getPlayerNumber()
				+ ">" + " ATTACCO IN SETTORE "
				+ dtoGame.getCoordinate()[dtoGame.getPlayerNumber()]);
		for (int i = 0; i < dtoGame.getPlayerType().length; i++) {
			PlayerType type = dtoGame.getPlayerType()[i];
			if (type != null) {
				System.out.println("<giocatore " + i + ">"
						+ " è stato attaccato e viene eliminato:\n"
						+ "era un " + type);
			}
		}
		if (dtoGame.getGameMessage() != null) {
			System.out.println(dtoGame.getGameMessage());
		}
	}
	
	/**
	 * 
	 * @param dtoGame
	 */
	private void useItemMessage(DTOGame dtoGame){
		System.out.println("<giocatore " + dtoGame.getPlayerNumber()
				+ ">" + " ha usato la carta "
				+ dtoGame.getItemCardType());
		switch (dtoGame.getItemCardType()) {
		case ATTACK:
			System.out
					.println("<giocatore "
							+ dtoGame.getPlayerNumber()
							+ ">"
							+ " ATTACCO IN SETTORE "
							+ dtoGame.getCoordinate()[dtoGame
									.getPlayerNumber()]);
			for (int i = 0; i < dtoGame.getPlayerType().length; i++) {
				PlayerType type = dtoGame.getPlayerType()[i];
				if (type != null) {
					System.out.println("<giocatore " + i + ">"
							+ " è stato attaccato e viene eliminato:\n"
							+ "era un " + type);
				}
			}
			if (dtoGame.getGameMessage() != null) {
				System.out.println(dtoGame.getGameMessage());
			}
			break;
		case SPOTLIGHT:
			for (int i = 0; i < dtoGame.getCoordinate().length; i++) {
				Coordinate coord = dtoGame.getCoordinate()[i];
				if (coord != null) {
					System.out.println("<giocatore " + i + ">"
							+ " si trova nel settore" + coord);
				}
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 
	 * @param dtoGame
	 */
	private void discardMessage(DTOGame dtoGame){
		System.out.println("<giocatore " + dtoGame.getPlayerNumber()
				+ ">" + " ha scartato una carta oggetto");
	}
	/**
	 * 
	 * @param dtoGame
	 */
	private void drawMessage(DTOGame dtoGame){
		switch (dtoGame.getSectorCardType()) {
		case NOISEANY:
			System.out
					.println("Hai pescato una carta Noise in Any Sector:\n "
							+ "seleziona un settore a scelta");
			break;
		case NOISEYOUR:
			System.out
					.println("<giocatore "
							+ dtoGame.getPlayerNumber()
							+ "> "
							+ "RUMORE IN SETTORE "
							+ dtoGame.getCoordinate()[dtoGame
									.getPlayerNumber()]);
			break;
		case SILENCE:
			System.out.println("<giocatore "
					+ dtoGame.getPlayerNumber() + ">"
					+ " SILENZIO IN TUTTI I SETTORI ");
			break;
		default:
			break;
		}
		// messaggio privato, associato a un evento pubblico
		if (numberOfPlayer == dtoGame.getPlayerNumber()) {
			if (dtoGame.getItemCardType() != null) {
				System.out.println("Hai pescato una carta oggetto "
						+ dtoGame.getItemCardType());
			}
			if (dtoGame.getGameMessage() != null) {
				System.out.println(dtoGame.getGameMessage());
			}

		}
	}
	/**
	 * 
	 * @param dtoGame
	 */
	private void noiseMessage(DTOGame dtoGame){
		System.out.println("<giocatore " + dtoGame.getPlayerNumber()
				+ ">" + " RUMORE IN SETTORE "
				+ dtoGame.getCoordinate()[dtoGame.getPlayerNumber()]);
	}
	/**
	 * 
	 * @param dtoGame
	 */
	private void endTurnMessage(DTOGame dtoGame){
		System.out.println(dtoGame.getGameMessage());
	}
	/**
	 * 
	 * @param dtoGame
	 */
	private void chatMessage(DTOGame dtoGame){
		if(dtoGame.getChat() != null){
			System.out.println(dtoGame.getChat());
		}
	}
}
