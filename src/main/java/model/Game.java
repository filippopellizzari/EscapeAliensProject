package model;

import java.util.List;

public class Game {
	
    private final HatchCards hatchCards;
    private final SectorCards sectorCards;
	private final ItemCards itemCards;
	private final Map map;
	private final List<Player> players;
	
	public Game(HatchCards hatchCards, SectorCards sectorCards,
			ItemCards itemCards, Map map, List<Player> players) {
		super();
		this.hatchCards = hatchCards;
		this.sectorCards = sectorCards;
		this.itemCards = itemCards;
		this.map = map;
		this.players = players;
	}
	
	public HatchCards getHatchCards() {
		return hatchCards;
	}
	
	public SectorCards getSectorCards() {
		return sectorCards;
	}
	
	public ItemCards getItemCards() {
		return itemCards;
	}
	
	public Map getMap() {
		return map;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	
	
	
	

}
