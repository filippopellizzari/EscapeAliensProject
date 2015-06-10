package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import connection.*;
import dto.*;

public interface Actions extends Remote{
	public Token getToken() throws RemoteException;
	public DTOGame doAnAction(DTOSend dtoSend, Token token) throws RemoteException;
	public void subscribeGame(TypeOfMap typeOfMap, Token token, SetClientParameter setParameter) throws RemoteException;
	public void subscribe(SetClientParameter setParameter) throws RemoteException;
}