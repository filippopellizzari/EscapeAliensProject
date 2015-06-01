package connection;

import java.util.ArrayList;
import java.util.List;

public class GameAvailable {
	private static GameAvailable instance = new GameAvailable();
	private List<TypeOfMap> mapName;
	

	public static GameAvailable getinstance() {
		return instance;
	}
	
	/**
	 * @return the mapName
	 */
	public List<TypeOfMap> getMapName() {
		return mapName;
	}
	/**
	 * @return the mapView
	 */
	

}
