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
		return decksHatch.PickOut();
	}
	public SectorCard getDecksSector() {
		return decksSector.PickOut();
	}
	public ItemCard getDecksItem() {
		return decksItem.PickOut();
	}
	public void getDecksItem(ItemCard itemToDiscard) {
		decksItem.Discard(itemToDiscard);
	}
	public Map getGameMap() {
		return gameMap;
	}
	public List<Player> getPlayerInGame() {
		return playerInGame;
	}	
}
