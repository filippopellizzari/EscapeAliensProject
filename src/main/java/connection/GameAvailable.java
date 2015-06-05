package connection;

import java.util.ArrayList;
import java.util.List;

public class GameAvailable {
	private static GameAvailable instance = new GameAvailable();
	private List<TypeOfMap> mapName;
	public GameAvailable() {
		mapName.add(new TypeOfMap("Fermi", "Hexagonal"));
		mapName.add(new TypeOfMap("Galilei", "Hexagonal"));
		mapName.add(new TypeOfMap("Galvani", "Hexagonal"));
	}

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
