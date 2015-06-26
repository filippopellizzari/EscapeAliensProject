package gui;

import connection.ClientData;
import controller.ActionType;
import dto.DTOGame;

/**
 * This class is a thread which calls ClientMessage as soon as arrives a dto
 * from the server; thread ends when the game ends
 * 
 * @author Filippo
 *
 */
public class ShowMessageGui implements Runnable {

	private ClientData cd;
	private ClientMessageGui clientMessage;

	/**
	 * 
	 * @param cd
	 *            , reference to ClientData
	 * @param model
	 *            , data of client view
	 * @param rp
	 *            , reference to rightPanel
	 * @throws InterruptedException
	 */
	public ShowMessageGui(ClientData cd, ClientModelGui model, RightPanel rp)
			throws InterruptedException {
		this.cd = cd;
		this.clientMessage = new ClientMessageGui(cd.getView()
				.getNumberPlayer(), model, rp);
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

	}

}
