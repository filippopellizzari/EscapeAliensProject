package connection;

import controller.GameController;

public class GameDescription {
	private String mapName;
	private int numberOfPlayers;
	private GameController controller;
	private Broker broker;
	
	public GameDescription(String mapName, int numberOfPlayers,
			GameController controller) {
		this.mapName = mapName;
		this.numberOfPlayers = numberOfPlayers;
		this.controller = controller;
	}
	/**
	 * @return the mapName
	 */
	public String getMapName() {
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
	
	
}
