package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import connection.*;
import dto.*;

public interface Actions extends Remote{
	public Token getToken() throws RemoteException;
	public DTOGame doAnAction(DTOSend dtoSend, Token token) throws RemoteException;
	public ViewForPlayer subscribeGame(TypeOfMap typeOfMap, Token token) throws RemoteException;
	public DTOGame subscribe(Token token)	throws RemoteException, InterruptedException;
}