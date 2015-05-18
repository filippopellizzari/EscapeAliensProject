package controller;

import model.*;

public class GameCreator {
	
	private GameCreator() { }
	final private static GameCreator instance=new GameCreator();
	public static GameCreator getinstance() {
		return instance;
	}
	
	public Game createGame(int numberMap, int numberOfPlayers) {
		CardsCreator cardCreator=new CardsCreator();
		HatchCards hatchCards=cardCreator.hatchCardsCreator();
		SectorCards sectorCards=cardCreator.sectorCardsCreator();
		ItemCards itemCards=cardCreator.itemCardsCreator();
		Map map;
		MapCreator mapCreator=new MapCreator();
		switch(numberMap) {
			case 1: map=mapCreator.createMap("GalileiMap.txt","Exagonal");
			break;
			case 2: map=mapCreator.createMap("GalileiMap.txt","Exagonal");
			break;
			default: map=mapCreator.createMap("GalileiMap.txt","Exagonal");
			break;
		}
		
		PlayerCreator playerCreator=new PlayerCreator(map);
		Player[] players=playerCreator.createPlayer(numberOfPlayers);
		return new Game(hatchCards,sectorCards,itemCards,map,players);
	}
}
