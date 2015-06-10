package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import connection.ViewForPlayer;
import dto.DTOGame;

public class ClientStub implements SetClientParameter, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DTOGame> dtoGameList;
	private List<String> buffer;
	private ViewForPlayer view;
	
	public ClientStub(List<DTOGame> dtoGameList, List<String> buffer, ViewForPlayer view) throws RemoteException {
		this.dtoGameList=dtoGameList;
		this.buffer=buffer;
		this.view=view;
	}

	@Override
	public void setBuffer(String string) throws RemoteException {
		buffer.add(string);
	}

	@Override
	public void setDTOGameList(DTOGame dtoGame) throws RemoteException {
		dtoGameList.add(dtoGame);
		
	}

	@Override
	public void setView(ViewForPlayer view) throws RemoteException {
		this.view=view;
	}

}
