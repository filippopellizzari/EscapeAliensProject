package rmi;

import java.rmi.RemoteException;
import java.util.List;

import connection.Token;
import connection.ViewForPlayer;
import dto.DTOGame;

public class ClientStub implements SetClientParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ClientDataRMI client;
	
	public ClientStub(ClientDataRMI clientDataRMI) {
		this.client=clientDataRMI;
	}

	public ClientStub(Token token, ViewForPlayer view,
			List<DTOGame> dtoGameList, List<String> buffer) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setToken(Token token) throws RemoteException {
		client.setToken(token);
		
	}

	@Override
	public void setView(ViewForPlayer view) throws RemoteException {
		client.setView(view);
	}

	@Override
	public void setBuffer(String string) throws RemoteException {
		client.setBuffer(string);
	}

	@Override
	public void setDTOGameList(DTOGame dtoGame) throws RemoteException {
		client.setDtoGameList(dtoGame);
		
	}

}
