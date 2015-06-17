package cli;

import model.Coordinate;
import model.PlayerType;
import connection.ClientData;
import dto.DTOGame;

/**
 * This class is used to analyze a single dtoGame to communicate the correct
 * message to client
 * 
 * @author Filippo
 *
 */
public class ClientMessage {

	private ClientData cd;
	private DTOGame dtoGame;

	public ClientMessage(ClientData cd, DTOGame dtoGame) {
		this.cd = cd;
		this.dtoGame = dtoGame;
	}

	public void receive() {
		if (dtoGame.getActionType() != null) {
			switch (dtoGame.getActionType()) {
			case MOVE:
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
					case GREEN:
						System.out
								.println("<giocatore "
										+ dtoGame.getPlayerNumber()
										+ ">"
										+ " pesca carta scialuppa verde:\n il giocatore ha vinto "
										+ "e il settore rimane bloccato");
					}
				}
				break;
			case ATTACK:
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
				break;
			case USEITEM:
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
				break;
			case DISCARDITEM:
				System.out.println("<giocatore " + dtoGame.getPlayerNumber()
						+ ">" + " ha scartato una carta oggetto");
				break;
			case DRAWSECTORCARD:
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
				if (cd.getView().getNumberPlayer() == dtoGame.getPlayerNumber()) {
					if (dtoGame.getItemCardType() != null) {
						System.out.println("Hai pescato una carta oggetto "
								+ dtoGame.getItemCardType());
					}
					if (dtoGame.getGameMessage() != null) {
						System.out.println(dtoGame.getGameMessage());
					}

				}
				break;
			case SELECTSECTORNOISE:
				System.out.println("<giocatore " + dtoGame.getPlayerNumber()
						+ ">" + " RUMORE IN SETTORE "
						+ dtoGame.getCoordinate()[dtoGame.getPlayerNumber()]);
				break;
			case ENDTURN:
				System.out.println(dtoGame.getGameMessage());
				break;
			default:
				break;
			}

		} else {
			// messaggio di errore
			System.out.println(dtoGame.getGameMessage());
		}

	}

}
