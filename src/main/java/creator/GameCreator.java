package creator;

import java.io.IOException;

import connection.MapName;
import connection.MapType;
import model.*;

/**
 * 
 * @author Nicola
 * This class creates a game, everytime the game management starts a game calls this class which
 * returns a game ready, is a singleton class
 *
 */


public class GameCreator {
	
	private static GameCreator instance = new GameCreator();
	
	private GameCreator() { 	
	}
	
	/**
	 * @return the instance of GameCreator the same for all
	 */
	
	public static GameCreator getinstance() {
		return instance;
	}
	
	/**
	 * @param mapName	the map which player would play
	 * @param totPlayers	number of player for this game
	 * @param mapType		all the map of base game are exagonal, but this allow to create square map or square map in 3D...
	 * @return	game, the game ready to start(model)
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	
	public Game createGame(MapName mapName, int totPlayers, MapType mapType) throws NumberFormatException, IOException {
		
		CardsCreator cardsCreator = new CardsCreator();
		HatchCards hatchCards = cardsCreator.createHatchCards();
		SectorCards sectorCards = cardsCreator.createSectorCards();
		ItemCards itemCards = cardsCreator.createItemCards();
		
		Map map = null;
		switch(mapType) {
			default:
				MapCreator mapCreator = new HexagonalMapCreator();
				map=mapCreator.loadMap(mapName);
		}
		
		PlayerCreator playerCreator = new PlayerCreator(map);
		Player[] players = playerCreator.createPlayer(totPlayers);
		
		return new Game(hatchCards, sectorCards, itemCards, map, players);
	}	
}
