package cli;

import model.ItemCardType;
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
	private ClientModel model;

	/**
	 * 
	 * @param numberOfPlayer
	 * @param model
	 */
	public ClientMessage(int numberOfPlayer, ClientModel model) {
		this.numberOfPlayer = numberOfPlayer;
		this.model = model;
	}

	/**
	 * This class analyzes the type of action done; if it has no type is surely
	 * an error message; if there is a chat message, it is always displayed
	 * 
	 * @param dtoGame
	 */
	public void receive(DTOGame dtoGame) {
		if (dtoGame.getActionType() != null) {
			Message message;
			switch (dtoGame.getActionType()) {
			case MOVE:
				message = new MoveMessage();
				message.receive(dtoGame);
				updatePosition(dtoGame);
				break;
			case ATTACK:
				message = new AttackMessage();
				message.receive(dtoGame);
				notifyDefense(dtoGame);
				break;
			case USEITEM:
				message = new UseItemMessage();
				message.receive(dtoGame);
				removeItem(dtoGame);
				notifyDefense(dtoGame);
				break;
			case DISCARDITEM:
				message = new DiscardMessage();
				message.receive(dtoGame);
				removeItem(dtoGame);
				break;
			case DRAWSECTORCARD:
				message = new DrawMessage();
				message.receive(dtoGame);
				itemDrawnPrivateMessage(dtoGame);
				itemDrawnPublicMessage(dtoGame);
				break;
			case SELECTSECTORNOISE:
				message = new NoiseMessage();
				message.receive(dtoGame);
				itemDrawnPublicMessage(dtoGame);
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

	private void notifyDefense(DTOGame dtoGame) {
		if (dtoGame.getNumberPlayerDefense() != null) {
			int numDefended = dtoGame.getNumberPlayerDefense();
			System.out.println("<giocatore " + (numDefended + 1)
					+ "> è stato attaccato, "
					+ " ma si è salvato grazie alla carta difesa");
			if (numberOfPlayer == numDefended) {
				model.removeItem(ItemCardType.DEFENSE);
			}
		}
	}

	/**
	 * This method updates the current sector of a player after a move action,
	 * in the view of client
	 * 
	 * @param dtoGame
	 */
	private void updatePosition(DTOGame dtoGame) {
		if (numberOfPlayer == dtoGame.getPlayerNumber()) {
			model.setCoordinate(dtoGame.getCoordinate(numberOfPlayer));
		}
	}

	/**
	 * This method removes an item Card, in the view of client
	 * 
	 * @param dtoGame
	 */
	private void removeItem(DTOGame dtoGame) {
		if (numberOfPlayer == dtoGame.getPlayerNumber()) {
			model.removeItem(dtoGame.getItemCardType());
		}
	}

	/**
	 * This method notifies all (excepted player of turn, because he already
	 * knows it) if the player draws a generic Item Card (but only player knows
	 * the type of Item Card)
	 * 
	 * @param dtoGame
	 */
	private void itemDrawnPublicMessage(DTOGame dtoGame) {
		if (numberOfPlayer != dtoGame.getPlayerNumber()) {
			if (dtoGame.getItemCardType() != null) {
				System.out.println("<giocatore "
						+ (dtoGame.getPlayerNumber() + 1)
						+ "> ha pescato una carta oggetto");
			}
		}

	}

	/**
	 * This method notifies the player if he has drawn an item Card (also the
	 * type) or if the deck of Item Cards is empty or if he has drawn the fourth
	 * itemCard. Then, adds itemCard in the view of client.
	 * 
	 * @param dtoGame
	 */
	private void itemDrawnPrivateMessage(DTOGame dtoGame) {
		if (numberOfPlayer == dtoGame.getPlayerNumber()) {
			if (dtoGame.getItemCardType() != null) {
				System.out.println("Hai pescato una carta oggetto "
						+ dtoGame.getItemCardType());
				model.getItems().add(dtoGame.getItemCardType());
			}
			if (dtoGame.getGameMessage() != null) {
				System.out.println(dtoGame.getGameMessage());
			}

		}
	}

	/**
	 * This method displays a message of end Turn
	 * 
	 * @param dtoGame
	 */
	private void endTurnMessage(DTOGame dtoGame) {
		System.out.println(dtoGame.getGameMessage());
	}

	/**
	 * This method displays a message of chat
	 * 
	 * @param dtoGame
	 * 
	 */
	private void chatMessage(DTOGame dtoGame) {
		if (dtoGame.getChat() != null) {
			System.out.println("<giocatore " + (dtoGame.getPlayerNumber() + 1)
					+ "> " + dtoGame.getChat());
		}
	}
}
