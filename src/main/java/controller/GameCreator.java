package controller;

import java.util.List;

import model.*;

public class GameCreator {
	private GameCreator() { }
	final private static GameCreator instance=new GameCreator();
	public static GameCreator getinstance() {
		return instance;
	}
	public Game CreateGame(int numberMap, int numberOfPlayers) {
		CardsCreator cardCreator=new CardsCreator();
		HatchCards hatchCards=cardCreator.hatchCardsCreator();
		SectorCards sectorCards=cardCreator.sectorCardsCreator();
		ItemCards itemCards=cardCreator.itemCardsCreator();
		Map map;
		switch(numberMap) {
			case 1: GalileiMapCreator galileiMapCreator=new GalileiMapCreator();
			map=galileiMapCreator.createMap();
			break;
			case 2: GalvaniMapCreator galvaniMapCreator=new GalvaniMapCreator();
			map=galvaniMapCreator.createMap();
			break;
			default: FermiMapCreator fermiMapCreator=new FermiMapCreator();
			map=fermiMapCreator.createMap();
			break;
		}
		PlayerCreator playerCreator=new PlayerCreator(map);
		List<Player> players=playerCreator.createPlayer(numberOfPlayers);
		return new Game(hatchCards,sectorCards,itemCards,map,players);
	}
}
