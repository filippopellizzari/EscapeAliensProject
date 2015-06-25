package cli;

import connection.ClientData;
import controller.ActionType;
import dto.DTOGame;

/**
 * This class is a thread which calls ClientMessage as soon as arrives a dto
 * from the server; thread ends when the game ends
 * 
 * @author Nicola
 *
 */
public class ShowMessage implements Runnable {

	private ClientData cd;
	private Client client;
	private ClientMessage clientMessage;

	/**
	 * 
	 * @param cd
	 * @param client
	 * @param model
	 * @throws InterruptedException
	 */
	public ShowMessage(ClientData cd, Client client, ClientModel model)
			throws InterruptedException {
		this.cd = cd;
		this.client = client;
		this.clientMessage = new ClientMessage(cd.getView().getNumberPlayer(),
				model);
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
