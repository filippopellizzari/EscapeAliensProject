package connection;

import java.util.ArrayList;
import java.util.List;

import rmi.ClientHandlerChooseGameRmi;
import socket.ClientHandlerChooseGameSocket;

public class DetailsPlayers {

	private List<ClientHandlerChooseGameSocket> socketPlayers;
	private List<ClientHandlerChooseGameRmi> rmiPlayers;
	private int gameId;
	private int numberOfPlayers;
	
	
	public DetailsPlayers() {
		this.socketPlayers = new ArrayList<ClientHandlerChooseGameSocket>();
		this.rmiPlayers = new ArrayList<ClientHandlerChooseGameRmi>();
		this.numberOfPlayers = 0;
	}
	/**
	 * @return the numberOfPlayers
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	/**
	 * @param numberOfPlayers the numberOfPlayers to set
	 */
	public void setNumberOfPlayers() {
		this.numberOfPlayers++;
	}
	/**
	 * @return the gameId
	 */
	public int getGameId() {
		return gameId;
	}
	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	/**
	 * @return the socketPlayers
	 */
	public List<ClientHandlerChooseGameSocket> getSocketPlayers() {
		return socketPlayers;
	}
	/**
	 * @param socketPlayers the socketPlayers to set
	 */
	public void setSocketPlayers(ClientHandlerChooseGameSocket socketPlayers) {
		this.socketPlayers.add(socketPlayers);
	}
	/**
	 * @return the rmiPlayers
	 */
	public List<ClientHandlerChooseGameRmi> getRmiPlayers() {
		return rmiPlayers;
	}
	/**
	 * @param rmiPlayers the rmiPlayers to set
	 */
	public void setRmiPlayers(ClientHandlerChooseGameRmi rmiPlayers) {
		this.rmiPlayers.add(rmiPlayers);
	}
	
}
