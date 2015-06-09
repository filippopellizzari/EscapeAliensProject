package connection;

import pubSub.*;
import controller.GameController;

public class GameDescription {
	private MapName mapName;
	private int numberOfPlayers;
	private GameController controller;
	private Broker broker;
	private StatusController statusController;
	
	public GameDescription(MapName mapName, int numberOfPlayers,
			GameController controller) {
		this.mapName = mapName;
		this.numberOfPlayers = numberOfPlayers;
		this.controller = controller;
		this.statusController=StatusController.FREE;
		broker=new Broker(numberOfPlayers);		//
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
	public synchronized void getStatus() throws InterruptedException {		//se il controller è impegnato aspetta fino a che non si libera
		while(statusController==StatusController.BUSY) 
			this.wait();
		
	}
	public synchronized void setStatus() {					//libera la risorsa e avvisa tutti
		this.statusController=StatusController.FREE;
		this.notifyAll();
	}
}
