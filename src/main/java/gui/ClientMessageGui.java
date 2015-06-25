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

	public ClientMessageGui(int numberOfPlayer, ClientModelGui model, RightPanel rp) {
		this.numberOfPlayer = numberOfPlayer;
		this.model = model;
		this.rp = rp;
	}

	public void receive(DTOGame dtoGame) {
		if (dtoGame.getActionType() != null) {
			MessageGui message;
			switch (dtoGame.getActionType()) {
			case MOVE:
				message = new MoveMessageGui();
				message.receive(dtoGame, rp);;
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
				itemDrawnMessage(dtoGame);
				break;
			case SELECTSECTORNOISE:
				message = new NoiseMessageGui();
				message.receive(dtoGame, rp);
				break;
			case ENDTURN:
				endTurnMessage(dtoGame);
				break;
			default:
				break;
			}

		} else {
			// messaggio di errore
			rp.getMessagePanel().getTextArea().append(dtoGame.getGameMessage()+"\n");
		}

		chatMessage(dtoGame);
	}

	
	private void notifyDefense(DTOGame dtoGame) {
		int numDefended = dtoGame.getNumberPlayerDefense();
		if(numDefended >= 0 && numDefended <=8){
			rp.getMessagePanel().getTextArea().append("<giocatore "+(numDefended+1)+"> è stato attaccato, "
					+ " ma si è salvato grazie alla carta difesa\n");
			if(numberOfPlayer == numDefended){
				model.removeItem(ItemCardType.DEFENSE);			
			}
		}
		
	}

	private void updatePosition(DTOGame dtoGame){
		if(numberOfPlayer == dtoGame.getPlayerNumber()){
		model.setCoordinate(dtoGame.getCoordinate(numberOfPlayer));
		rp.getMyPositionPanel().getTextArea().append(model.getCoordinate().toString()+"\n");
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
				rp.getMessagePanel().getTextArea().append("Hai pescato una carta oggetto "
						+ dtoGame.getItemCardType()+"\n");
				model.getItems().add(dtoGame.getItemCardType());
			}
			if (dtoGame.getGameMessage() != null) {
				rp.getMessagePanel().getTextArea().append(dtoGame.getGameMessage()+"\n");
			}

		}
	}

	/**
	 * 
	 * @param dtoGame
	 */
	private void endTurnMessage(DTOGame dtoGame) {
		rp.getMessagePanel().getTextArea().append(dtoGame.getGameMessage()+"\n");
	}

	/**
	 * 
	 * @param dtoGame
	 * 
	 */
	private void chatMessage(DTOGame dtoGame) {
		if (dtoGame.getChat() != null) {
			rp.getMessagePanel().getTextArea().append("<giocatore " + (dtoGame.getPlayerNumber() + 1)
					+ "> " + dtoGame.getChat()+"\n");
		}
	}
}
