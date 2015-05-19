package creator;

import model.*;


public class GameCreator {
	
	/**
	 * pattern Singleton
	 */
	private static GameCreator instance = new GameCreator();
	
	private GameCreator() { 	
	}
	
	public static GameCreator getinstance() {
		return instance;
	}


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
