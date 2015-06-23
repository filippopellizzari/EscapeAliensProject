package cli;

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
			Message message;
			switch (dtoGame.getActionType()) {
			case MOVE:
				message = new MoveMessage();
				message.receive(dtoGame);
				break;
			case ATTACK:
				message = new AttackMessage();
				message.receive(dtoGame);
				break;
			case USEITEM:
				message = new UseItemMessage();
				message.receive(dtoGame);
				break;
			case DISCARDITEM:
				message = new DiscardMessage();
				message.receive(dtoGame);
				break;
			case DRAWSECTORCARD:
				message = new DrawMessage();
				message.receive(dtoGame);
				itemDrawnMessage(dtoGame);
				break;
			case SELECTSECTORNOISE:
				message = new NoiseMessage();
				message.receive(dtoGame);
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
	 * private message, when a player draws an itemCard
	 * 
	 * @param dtoGame
	 */
	private void itemDrawnMessage(DTOGame dtoGame) {

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
	private void endTurnMessage(DTOGame dtoGame) {
		System.out.println(dtoGame.getGameMessage());
	}

	/**
	 * 
	 * @param dtoGame
	 */
	private void chatMessage(DTOGame dtoGame) {
		if (dtoGame.getChat() != null) {
			System.out.println(dtoGame.getChat());
		}
	}
}
