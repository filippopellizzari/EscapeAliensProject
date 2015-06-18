package rmi;

import java.rmi.RemoteException;

import controller.ActionType;
import dto.DTOGame;

/**
 * This thread is used to do receives a dtoGame from the broker, is a thread because a player can do any action
 * while the pub-sub sends messages
 * @author Nicola
 *
 */

public class subscribeRMI implements Runnable {

	private ClientDataRMI clientData;
	
	/**
	 * 
	 * @param clientDataRMI, contains data to set
	 */
	
	public subscribeRMI(ClientDataRMI clientDataRMI) {
		this.clientData=clientDataRMI;
	}

	@Override
	public void run() {
		DTOGame dtoGame;
		do {
			dtoGame=new DTOGame();
			try {
				dtoGame=clientData.getGame().subscribe(clientData.getToken());
			} catch (InterruptedException | RemoteException e) {
				System.err.println("errore ricezione dtoGame");
			}
		}while(dtoGame.getActionType()!=ActionType.ENDGAME);
	}
}