package gui;

import model.ItemCardType;
import dto.DTOGame;

/**
 * This class is used to analyze a single dtoGame to communicate the correct
 * message to client
 * 
 * @author Filippo
 *
 */
public class ClientMessageGui {

	private int numberOfPlayer;
	private ClientModelGui model;
	private RightPanel rp;

	/**
	 * 
	 * @param numberOfPlayer
	 *            , player who is playing the turn
	 * @param model
	 *            , data of player view
	 * @param rp
	 *            , reference to the right Panel of game table
	 */
	public ClientMessageGui(int numberOfPlayer, ClientModelGui model,
			RightPanel rp) {
		this.numberOfPlayer = numberOfPlayer;
		this.model = model;
		this.rp = rp;
	}

	/**
	 * 
	 * 
	 * @param dtoGame
	 */
	public void receive(DTOGame dtoGame) {
		if (dtoGame.getActionType() != null) {
			MessageGui message;
			switch (dtoGame.getActionType()) {
			case MOVE:
				message = new MoveMessageGui();
				message.receive(dtoGame, rp);
				updatePosition(dtoGame);
				break;
			case ATTACK:
				message = new AttackMessageGui();
				message.receive(dtoGame, rp);
				notifyDefense(dtoGame);
				break;
			case USEITEM:
				message = new UseItemMessageGui();
				message.receive(dtoGame, rp);
				removeItem(dtoGame);
				notifyDefense(dtoGame);
				break;
			case DISCARDITEM:
				message = new DiscardMessageGui();
				message.receive(dtoGame, rp);
				removeItem(dtoGame);
				break;
			case DRAWSECTORCARD:
				message = new DrawMessageGui();
				message.receive(dtoGame, rp);
				itemDrawnPrivateMessage(dtoGame);
				itemDrawnPublicMessage(dtoGame);
				break;
			case SELECTSECTORNOISE:
				message = new NoiseMessageGui();
				message.receive(dtoGame, rp);
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
			rp.getMessagePanel().getTextArea()
					.append(dtoGame.getGameMessage() + "\n");
		}

		chatMessage(dtoGame);
	}

	/**
	 * This method displays a message when a player is attacked, but he is not
	 * eliminated thanks to defense Item Card
	 * 
	 * @param dtoGame
	 */
	private void notifyDefense(DTOGame dtoGame) {
		if (dtoGame.getNumberPlayerDefense() != null) {
			int numDefended = dtoGame.getNumberPlayerDefense();
			rp.getMessagePanel()
					.getTextArea()
					.append("<giocatore " + (numDefended + 1)
							+ "> è stato attaccato,\n "
							+ " ma si è salvato grazie alla carta difesa\n");
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
			rp.getMyPositionPanel().getTextArea()
					.append(model.getCoordinate().toString() + "\n");
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
				rp.getMessagePanel()
						.getTextArea()
						.append("<giocatore " + (dtoGame.getPlayerNumber() + 1)
								+ "> ha pescato una carta oggetto\n");
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
				rp.getMessagePanel()
						.getTextArea()
						.append("Hai pescato una carta oggetto "
								+ dtoGame.getItemCardType() + "\n");
				model.getItems().add(dtoGame.getItemCardType());
			}
			if (dtoGame.getGameMessage() != null) {
				rp.getMessagePanel().getTextArea()
						.append(dtoGame.getGameMessage());
			}

		}
	}

	/**
	 * This method displays a message of end Turn
	 * 
	 * @param dtoGame
	 */
	private void endTurnMessage(DTOGame dtoGame) {
		rp.getMessagePanel().getTextArea()
				.append(dtoGame.getGameMessage() + "\n");
	}

	/**
	 * This method displays a message of chat
	 * 
	 * @param dtoGame
	 * 
	 */
	private void chatMessage(DTOGame dtoGame) {
		if (dtoGame.getChat() != null) {
			rp.getMessagePanel()
					.getTextArea()
					.append("<giocatore " + (dtoGame.getPlayerNumber() + 1)
							+ "> " + dtoGame.getChat() + "\n");
		}
	}
}
