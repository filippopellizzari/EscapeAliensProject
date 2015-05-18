package creator;

import java.io.IOException;

import model.*;

public class MapCreator {
	
	public Map createMap(String mapName) throws NumberFormatException, IOException{
		
		Map map;
		LoadExagonalMap loadExagonalmap = new LoadExagonalMap();
		
		switch(mapName) {
			case "Fermi": map = loadExagonalmap.loadMap("FermiMap.txt");
			break;
			case "Galilei": map = loadExagonalmap.loadMap("GalileiMap.txt");
			break;
			case "Galvani": map = loadExagonalmap.loadMap("GalvaniMap.txt");
			break;
			default: map = loadExagonalmap.loadMap("GalileiMap.txt");
		}
		
		
		return map;
	}
}
		

