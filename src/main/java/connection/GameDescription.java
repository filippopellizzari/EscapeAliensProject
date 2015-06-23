package connection;

import pubSub.*;
import controller.GameController;

/**
 * This class contains all the things of a specific game, his broker, his controller, his number of game and player
 * @author Nicola
 *
 */

public class GameDescription {
	private MapName mapName;
	private int numberOfPlayers;
	private GameController controller;
	private Broker broker;
	private StatusController statusController;
	private StatusCreation statusGame;
	
	/**
	 * This constructor sets the features of a new game
	 * @param mapName
	 * @param numberOfPlayers
	 * @param controller
	 */
	
	public GameDescription(MapName mapName, int numberOfPlayers,
			GameController controller) {
		this.mapName = mapName;
		this.numberOfPlayers = numberOfPlayers;
		this.controller = controller;
		this.statusController=StatusController.FREE;
		this.broker=new Broker(numberOfPlayers,this);
		this.statusGame=StatusCreation.OPEN;
	}

	/**
	 * @return the mapName
	 */
	public MapName getMapName() {
		return mapName;
	}
	/**
	 * @return the numberOfPlayers
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	/**
	 * @return the controller
	 */
	public GameController getController() {
		return controller;
	}
	/**
	 * @return the broker
	 */
	public Broker getBroker() {
		return broker;
	}
	/**
	 * @param broker the broker to set
	 */
	public void setBroker(Broker broker) {
		this.broker = broker;
	}
	
	/**
	 * This method show the gameStatus, if the game is in-progress waits until is finished
	 * @throws InterruptedException 
	 * @stop until the game is finished
	 */
	public synchronized void getStatusGame() throws InterruptedException {
		while(statusGame==StatusCreation.OPEN)
			this.wait();
	}
	
	/**
	 * this method reduces the number of player, when all player have taken their last dto they reduce the number by one for
	 * each one
	 */
	
	public synchronized void endPlayer() {
		this.numberOfPlayers--;
		if(numberOfPlayers==0) {
			statusGame=StatusCreation.TERMINATED;
			this.notifyAll();
		}
	}
	
	/**
	 * If the controller is busy wait otherwise uses the controller and sets it busy
	 * @throws InterruptedException
	 */
	
	public synchronized void getStatus() throws InterruptedException {		//se il controller è impegnato aspetta fino a che non si libera
		while(statusController==StatusController.BUSY) 
			this.wait();
		statusController=StatusController.BUSY;
	}
	
	/**
	 * Frees the controller
	 */
	
	public synchronized void setStatus() {					//libera la risorsa e avvisa tutti
		statusController=StatusController.FREE;
		this.notifyAll();
	}
}
