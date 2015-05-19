package model;


public class Game {
	
    private final HatchCards hatchCards;
    private final SectorCards sectorCards;
	private final ItemCards itemCards;
	private final Map map;
	private final Player[] players;
	
	public Game(HatchCards hatchCards, SectorCards sectorCards,
			ItemCards itemCards, Map map, Player[] players) {
		
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

	public Player getPlayers(int numberOfPlayer) {
		return players[numberOfPlayer];
	}
	
	
	
	
	

}
