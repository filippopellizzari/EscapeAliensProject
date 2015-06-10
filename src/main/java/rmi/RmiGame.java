package rmi;

import java.rmi.RemoteException;

import dto.DTOSend;

public class RmiGame implements Runnable {
	private ClientDataRMI clientData;
	private DTOSend dtoSend;

	public RmiGame(ClientDataRMI clientDataRMI, DTOSend dtoSend) {
		this.clientData=clientDataRMI;
		this.dtoSend=dtoSend;
	}

	@Override
	public void run() {
		try {
			clientData.setDtoGameList(clientData.getGame().doAnAction(dtoSend, clientData.getToken()));
		} catch (RemoteException e) {
			System.err.println("errore ricezione dtoGame");
		}
	}

}