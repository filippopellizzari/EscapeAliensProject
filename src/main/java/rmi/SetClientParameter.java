package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import connection.Token;
import connection.ViewForPlayer;
import dto.DTOGame;

public interface SetClientParameter extends Remote, Serializable{
	void setBuffer(String string) throws RemoteException;
	void setDTOGameList(DTOGame dtoGame) throws RemoteException;
}