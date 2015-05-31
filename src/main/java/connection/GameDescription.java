package connection;

import controller.GameController;

public class GameDescription {
	private String mapName;
	private int numberOfPlayers;
	private GameController controller;
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
	
}
