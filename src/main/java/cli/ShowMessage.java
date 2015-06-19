package cli;

import connection.ClientData;
import controller.ActionType;
import dto.DTOGame;

public class ShowMessage implements Runnable {
	private ClientData cd;
	ClientMessage clientMessage;

	public ShowMessage(ClientData cd) throws InterruptedException {
		this.cd=cd;
		clientMessage=new ClientMessage(cd.getView().getNumberPlayer());
	}

	@Override
	public void run() {
		DTOGame dtoGame = null;
		do {
			try {
				dtoGame=cd.getDtoGame();
				clientMessage.receive(dtoGame);
			} catch (InterruptedException e) {
				System.err.println("Errore nella scrittura messaggi");
			}
		}while(dtoGame.getActionType()==ActionType.ENDGAME);
	}

}
