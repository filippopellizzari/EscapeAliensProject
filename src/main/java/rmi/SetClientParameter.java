package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import connection.Token;
import connection.ViewForPlayer;
import dto.DTOGame;

public interface SetClientParameter extends Remote, Serializable{
	void setToken(Token token) throws RemoteException;
	void setView(ViewForPlayer view) throws RemoteException;
	void setBuffer(String string) throws RemoteException;
	void setDTOGameList(DTOGame dtoGame) throws RemoteException;
}