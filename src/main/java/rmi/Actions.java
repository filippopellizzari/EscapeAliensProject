package rmi;

import java.rmi.Remote;

import connection.Token;
import connection.TypeOfMap;
import connection.ViewForPlayer;
import dto.*;

public interface Actions extends Remote{
	public DTOGame doAnAction(DTOSend dtoSend, Token token);
	public Token getToken();
	public ViewForPlayer subscribeGame(TypeOfMap typeOfMap, Token token);
}