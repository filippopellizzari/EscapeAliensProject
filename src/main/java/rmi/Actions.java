package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import connection.*;
import dto.*;

/**
 * This class is the stub that server publishes for the client, all the method that the client can call are here
 * @author Nicola
 *
 */

public interface Actions extends Remote{
	/**
	 * This method returnes a new token for the player
	 * @return the new token
	 * @throws RemoteException
	 */
	
	public Token getToken() throws RemoteException;
	
	/**
	 * This method sends a player's action and returnes the response from the server
	 * @param dtoSend, the player's action
	 * @param token, the player's token
	 * @return a response, dtoGame
	 * @throws RemoteException
	 */
	
	public DTOGame doAnAction(DTOSend dtoSend, Token token) throws RemoteException;
	
	/**
	 * This method sends a type of map, this generate a new request to join a new game, and if the game has at least
	 * 2 player returns the view for the player, else return null
	 * @param typeOfMap, the type of map chooses by the player
	 * @param token, the player's token
	 * @return the player's view
	 * @throws RemoteException
	 */
	
	public ViewForPlayer subscribeGame(TypeOfMap typeOfMap, Token token) throws RemoteException;
	
	/**
	 * This method is used to receive a message from the pub-sub
	 * @param token, the player's token
	 * @return a dtoGame, a new message from the pub-sub
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	
	public DTOGame subscribe(Token token)	throws RemoteException, InterruptedException;
}