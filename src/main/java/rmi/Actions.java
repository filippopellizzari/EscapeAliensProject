package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import connection.*;
import dto.*;

public interface Actions extends Remote{
	public void getToken(SetClientParameter setClientParameter) throws RemoteException;
	public void doAnAction(DTOSend dtoSend, Token token, SetClientParameter setClientParameter) throws RemoteException;
	public void subscribeGame(TypeOfMap typeOfMap, Token token, SetClientParameter setClientParameter) throws RemoteException;
}