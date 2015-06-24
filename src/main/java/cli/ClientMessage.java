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

	public ClientMessage(int numberOfPlayer, ClientModel model) {
		this.numberOfPlayer = numberOfPlayer;
		this.model = model;
	}

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
				break;
			case DISCARDITEM:
				message = new DiscardMessage();
				message.receive(dtoGame);
				removeItem(dtoGame);
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

	
	private void notifyDefense(DTOGame dtoGame) {
		int numDefended = dtoGame.getNumberPlayerDefense();
		if(numDefended >= 0 && numDefended <=8){
			System.out.println("<giocatore "+(numDefended+1)+"> è stato attaccato, "
					+ " ma si è salvato grazie alla carta difesa");
			if(numberOfPlayer == numDefended){
				model.removeItem(ItemCardType.DEFENSE);
			}
		}
		
	}

	private void updatePosition(DTOGame dtoGame){
		if(numberOfPlayer == dtoGame.getPlayerNumber()){
		model.setCoordinate(dtoGame.getCoordinate(numberOfPlayer));
		}
	}
	private void removeItem(DTOGame dtoGame){
		if(numberOfPlayer == dtoGame.getPlayerNumber()){
			model.removeItem(dtoGame.getItemCardType());
			}
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
				model.getItems().add(dtoGame.getItemCardType());
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
	 * 
	 */
	private void chatMessage(DTOGame dtoGame) {
		if (dtoGame.getChat() != null) {
			System.out.println("<giocatore " + (dtoGame.getPlayerNumber() + 1)
					+ "> " + dtoGame.getChat());
		}
	}
}
