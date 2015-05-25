package creator;

import java.io.IOException;

import model.*;

/**
 * This class create a map of the type you passed, in this case exagonal map and one of the three available
 * @author Nicola
 * @see Map
 *
 */

public class MapCreator {
	
	/**
	 * 
	 * @param mapName the name of the map: Fermi, Galilei, Galvani
	 * @param typeMap exagonal in this case but can be different
	 * @return map
	 */
	
	public Map createMap(String mapName, String typeMap) {
		
		Map map = null;
		LoadHexagonalMap loadExagonalmap = new LoadHexagonalMap();

		switch(typeMap) {
			default: try {
				map=loadExagonalmap.loadMap(mapName);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
			
		return map;
	}
}
		

