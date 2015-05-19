package creator;

import java.io.IOException;

import model.*;

public class MapCreator {
	
	public Map createMap(String mapName, String typeMap) {
		
		Map map = null;
		LoadExagonalMap loadExagonalmap = new LoadExagonalMap();
		switch(typeMap) {
		default: try {
				map=loadExagonalmap.loadMap(mapName);
			} catch (NumberFormatException | IOException e) {}
		}
		return map;
	}
}
		

