package creator;

import java.io.IOException;

import model.*;


public class GameCreator {
	
	/**
	 * pattern Singleton
	 */
	final private static GameCreator instance = new GameCreator();
	
	private GameCreator() { 
		
	}
	
	public static GameCreator getinstance() {
		return instance;
	}

	public Game createGame(String mapName, int numberOfPlayers) {
		
		CardsCreator cardsCreator = new CardsCreator();
		HatchCards hatchCards = cardsCreator.createHatchCards();
		SectorCards sectorCards = cardsCreator.createSectorCards();
		ItemCards itemCards = cardsCreator.createItemCards();
		
		
		MapCreator mapCreator = new MapCreator();
		Map map = null;
		try {
			map = mapCreator.createMap(mapName);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PlayerCreator playerCreator = new PlayerCreator(map);
		Player[] players = playerCreator.createPlayer(numberOfPlayers);
		
		
		return new Game(hatchCards,sectorCards,itemCards,map,players);
	}
}
