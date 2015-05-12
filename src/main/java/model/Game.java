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
	public HatchCard getDecksHatch() {
		return decksHatch.draw();
	}
	public SectorCard getDecksSector() {
		return decksSector.draw();
	}
	public ItemCard getDecksItem() {
		return decksItem.draw();
	}
	public void getDecksItem(ItemCard current) {
		decksItem.discard(current);
	}
	public Map getGameMap() {
		return gameMap;
	}
	public List<Player> getPlayerInGame() {
		return playerInGame;
	}	
}
