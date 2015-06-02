package connection;

import java.util.ArrayList;
import java.util.List;

import rmi.ClientHandlerChooseGameRmi;
import socket.ClientHandlerChooseGameSocket;

public class DetailsPlayers {

	private List<ClientHandlerChooseGameSocket> socketPlayers;
	private List<ClientHandlerChooseGameRmi> rmiPlayers;
	private int gameId;			//id della sua creazione non quello di quando è in gioco
	private int numberOfPlayers;
	private String buffer;
	private ViewForPlayer[] view;
	
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
	
	public synchronized int takeNumberOfPlayer() {
		int number=numberOfPlayers;
		numberOfPlayers--;
		if(numberOfPlayers==-1) notifyAll();	//notifica che tutti i giocatori hanno preso il loro numero
		return number;
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
	/**
	 * @return the buffer
	 */
	public String getBuffer() {
		return buffer;
	}
	/**
	 * @param buffer the buffer to set
	 */
	public synchronized void setBuffer(String buffer) {		//notifica a tutti i thread in attesa di messaggio
		this.buffer=null;
		this.buffer = buffer;
		notifyAll();
	}
	/**
	 * @return the view
	 */
	public ViewForPlayer getView(int number) {
		return view[number];
	}
	/**
	 * @param view the view to set
	 */
	public void setView(ViewForPlayer[] view) {
		this.view = view;
	}
}
