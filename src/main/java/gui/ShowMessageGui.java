package gui;



import cli.ClientModel;
import connection.ClientData;
import controller.ActionType;
import dto.DTOGame;

public class ShowMessageGui implements Runnable {
	
	
	private ClientData cd;
	private ClientMessageGui clientMessage;
	
	public ShowMessageGui(ClientData cd, ClientModel model, RightPanel rp) throws InterruptedException {
		this.cd = cd;
		this.clientMessage = new ClientMessageGui(cd.getView().getNumberPlayer(), model, rp);
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
