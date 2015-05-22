package creator;

import model.*;

/**
 * 
 * @author Nicola, this class create a game, everytime the game management start a game call this class which return a game ready
 *
 */


public class GameCreator {
	
	/**
	 * pattern Singleton
	 */
	private static GameCreator instance = new GameCreator();
	
	private GameCreator() { 	
	}
	
	/**
	 * 
	 * @return the instance of GameCreator the same for all
	 */
	
	public static GameCreator getinstance() {
		return instance;
	}
	
	/**
	 * 
	 * @param mapName	the map which player would play
	 * @param totPlayers	number of player for this game
	 * @param typeMap		all the map of base game are exagonal, but this allow to create square map or square map in 3D...
	 * @return	game, the game ready to start(model)
	 */


	public Game createGame(String mapName, int totPlayers, String typeMap) {
		
		CardsCreator cardsCreator = new CardsCreator();
		HatchCards hatchCards = cardsCreator.createHatchCards();
		SectorCards sectorCards = cardsCreator.createSectorCards();
		ItemCards itemCards = cardsCreator.createItemCards();
		
		MapCreator mapCreator = new MapCreator();
		Map map = mapCreator.createMap(mapName,typeMap);
		
		PlayerCreator playerCreator = new PlayerCreator(map);
		Player[] players = playerCreator.createPlayer(totPlayers);
		
		return new Game(hatchCards, sectorCards, itemCards, map, players);
	}
	
	
	
	
	
	
	
	
	
	
	
}
