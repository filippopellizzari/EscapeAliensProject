package creator;

import java.io.IOException;

import model.*;

public class MapCreator {
	
	public Map createMap(String mapName) {
		
		Map map = null;
		LoadExagonalMap loadExagonalmap = new LoadExagonalMap();
		try {
			map=loadExagonalmap.loadMap(mapName);
		} catch (NumberFormatException | IOException e) {}
		return map;
	}
}
		

