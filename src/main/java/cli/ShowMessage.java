package cli;

import connection.ClientData;
import controller.ActionType;
import dto.DTOGame;
/**
 * 
 * @author Nicola
 *
 */
public class ShowMessage implements Runnable {
	
	
	private ClientData cd;
	private Client client;
	private ClientMessage clientMessage;
	
	public ShowMessage(ClientData cd, Client client) throws InterruptedException {
		this.cd = cd;
		this.client = client;
		this.clientMessage = new ClientMessage(cd.getView().getNumberPlayer());
	}

	@Override
	public void run() {
		DTOGame dtoGame = null;
		do {
			try {
				dtoGame = cd.getDtoGame();
				clientMessage.receive(dtoGame);
			} catch (InterruptedException e) {
				System.err.println("Errore nella scrittura messaggi");
			}
		} while (dtoGame.getActionType() != ActionType.ENDGAME);
		
		System.out.println(dtoGame.getGameMessage());
		client.setFinePartita(true);	
	}

}
