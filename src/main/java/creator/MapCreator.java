package creator;

import java.io.IOException;

import model.*;

/**
 * 
 * @author Nicola
 *
 */

public class MapCreator {
	
	/**
	 * 
	 * @param mapName
	 * @param typeMap
	 * @return
	 */
	
	public Map createMap(String mapName, String typeMap) {
		
		Map map = null;
		LoadExagonalMap loadExagonalmap = new LoadExagonalMap();
		
 		switch(typeMap) {
 			default: try {
				map=loadExagonalmap.loadMap(mapName);
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

 		}

			
		return map;
	}
}
		

