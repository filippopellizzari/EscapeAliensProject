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
		this.broker=new Broker(numberOfPlayers);
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
	 * If the controller is busy wait otherwise uses the controller and sets it busy
	 * @throws InterruptedException
	 */
	
	public synchronized void getStatus() throws InterruptedException {		//se il controller è impegnato aspetta fino a che non si libera
		System.out.println("Aspetto che il controller sia libero");
		while(statusController==StatusController.BUSY) 
			this.wait();
		statusController=StatusController.BUSY;
		System.out.println("Prendo il controller");
	}
	
	/**
	 * Frees the controller
	 */
	
	public synchronized void setStatus() {					//libera la risorsa e avvisa tutti
		statusController=StatusController.FREE;
		this.notifyAll();
		System.out.println("Libero il controller");
	}
}
