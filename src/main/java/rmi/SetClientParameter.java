package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import connection.ViewForPlayer;
import dto.DTOGame;

public interface SetClientParameter extends Remote{
	void setBuffer(String string) throws RemoteException;
	void setDTOGameList(DTOGame dtoGame) throws RemoteException;
	void setView(ViewForPlayer view) throws RemoteException;
}