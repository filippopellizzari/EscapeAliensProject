package creator;

import java.io.IOException;

import model.*;

public class MapCreator {
	
	public Map createMap(String mapName) {
		
		Map map = null;
		LoadExagonalMap loadExagonalmap = new LoadExagonalMap();
		
		
		switch(mapName) {
			case "Fermi": 
			try {
				map = loadExagonalmap.loadMap("FermiMap.txt");
			} catch (NumberFormatException | IOException e1) {}
			break;
			case "Galilei": try {
				map = loadExagonalmap.loadMap("GalileiMap.txt");
			} catch (NumberFormatException | IOException e1) {}
			break;
			case "Galvani": try {
				map = loadExagonalmap.loadMap("GalvaniMap.txt");
			} catch (NumberFormatException | IOException e1) {}
			break;
			default: try {
				map = loadExagonalmap.loadMap("GalileiMap.txt");
			} catch (NumberFormatException | IOException e1) {}
		}
		return map;
	}
}
		

