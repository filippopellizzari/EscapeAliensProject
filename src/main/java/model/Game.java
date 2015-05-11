package model;

import java.util.List;

public class Game {
	HatchCards decksHatch;
	SectorCards decksSector;
	ItemCards decksItem;
	Map gameMap;
	List<Player> playerInGame;
	public Game(HatchCards decksHatch, SectorCards decksSector, ItemCards decksItem, Map gameMap, List<Player> playerInGame) {
		this.decksHatch = decksHatch;
		this.decksSector = decksSector;
		this.decksItem = decksItem;
		this.gameMap = gameMap;
		this.playerInGame = playerInGame;
	}
	
}
