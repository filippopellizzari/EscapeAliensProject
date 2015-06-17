package connection;

import java.util.ArrayList;
import java.util.List;

/**
 * This class sets all the game available to play
 * @author Nicola
 *
 */

public class GameAvailable {
	private static GameAvailable instance = new GameAvailable();
	private List<TypeOfMap> mapName;
	
	/**
	 * Creates all the game available to play and save their features
	 */
	
	public GameAvailable() {
		mapName=new ArrayList<TypeOfMap>();
		mapName.add(new TypeOfMap(MapName.Fermi,MapType.HEXAGONAL));
		mapName.add(new TypeOfMap(MapName.Galilei,MapType.HEXAGONAL));
		mapName.add(new TypeOfMap(MapName.Galvani,MapType.HEXAGONAL));
	}
	
	/**
	 * Return this class
	 * @return
	 */

	public static GameAvailable getinstance() {
		return instance;
	}
	
	/**
	 * @return the mapName
	 */
	
	public List<TypeOfMap> getMapName() {
		return mapName;
	}
}
