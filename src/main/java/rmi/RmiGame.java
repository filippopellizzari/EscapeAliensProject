package rmi;

import java.rmi.RemoteException;

import dto.DTOSend;

/**
 * This thread is used to do an action on the game, is a thread because a player can do more than one action before
 * a response is receives, so the client doesn't have a blocked interface
 * @author Nicola
 *
 */

public class RmiGame implements Runnable {
	private ClientDataRMI clientData;
	private DTOSend dtoSend;
	
	/**
	 * 
	 * @param clientDataRMI, contains all the parameter to set
	 * @param dtoSend, object for an action, creates by the client and sends to the server
	 */

	public RmiGame(ClientDataRMI clientDataRMI, DTOSend dtoSend) {
		this.clientData=clientDataRMI;
		this.dtoSend=dtoSend;
	}
	
	/**
	 * Does an action on the game
	 */

	@Override
	public void run() {
		try {
			clientData.setDtoGameList(clientData.getGame().doAnAction(dtoSend, clientData.getToken()));
		} catch (RemoteException e) {
			System.err.println("errore ricezione dtoGame");
		}
	}

}