package rmi;

import java.rmi.RemoteException;

import dto.DTOGame;

public class subscribeRMI implements Runnable {

	private ClientDataRMI clientData;
	
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
		}while(dtoGame.getGameMessage()!="Partita conclusa");
	}
}