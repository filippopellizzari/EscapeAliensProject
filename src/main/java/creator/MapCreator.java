package creator;

import java.io.IOException;
import java.util.List;

import connection.MapName;
import model.*;

/**
 * This class create a map of the type you passed
 * @author Nicola
 * @see Map
 *
 */

public abstract class MapCreator {
	
	protected Sector[] sectors;
	protected Coordinate alienSector;
	protected Coordinate humanSector;
	protected List<Coordinate> hatchSectors;
	
	/**
	 * 
	 * @param mapName, the name of a map
	 * @return Map, one type of map
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	
	public abstract Map loadMap(MapName mapName) throws NumberFormatException, IOException;		
}
		

