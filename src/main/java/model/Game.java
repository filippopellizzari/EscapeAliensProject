package model;

/**
 * 
 * @author Nicola
 * @see	Ma, to know the scenario of the game
 * @see HatchCards, to know number and type of hatch cards
 * @see SectorCards, to know number and type of sector cards
 * @see ItemCards, to know number and type of item cards
 *
 */

public class Game {
	
    private final HatchCards hatchCards;
    private final SectorCards sectorCards;
	private final ItemCards itemCards;
	private final Map map;
	private final Player[] players;
	
	/**
	 * 
	 * @param hatchCards decks of hatchCards
	 * @param sectorCards decks of sectorCards
	 * @param itemCards decks of itemCards
	 * @param map, the map where play
	 * @param players, type and attributes of each player
	 */
	
	public Game(HatchCards hatchCards, SectorCards sectorCards,
			ItemCards itemCards, Map map, Player[] players) {
		this.hatchCards = hatchCards;
		this.sectorCards = sectorCards;
		this.itemCards = itemCards;
		this.map = map;
		this.players = players;
	}
	
	/**
	 * 
	 * @return HatchCards, this is the deck and discard of Hatch cards
	 * @see HatchCards
	 */
	
	public HatchCards getHatchCards() {
		return hatchCards;
	}
	
	/**
	 * 
	 * @return SectorCards, this is the deck and discard of Sector cards
	 * @see SectorCards
	 */
	
	public SectorCards getSectorCards() {
		return sectorCards;
	}
	
	/**
	 * 
	 * @return ItemCards, this is the deck and discard of Item Hatch cards
	 * @see ItemCards
	 */
	
	public ItemCards getItemCards() {
		return itemCards;
	}
	
	/**
	 * 
	 * @return the map, the Map contains all sectors
	 * @see Map
	 */
	
	public Map getMap() {
		return map;
	}
	
	/**
	 * 
	 * @param numberOfPlayer, used to referred at the right player
	 * @return if player doesn't exit so return null, otherwise return correct player
	 */

	public Player getPlayers(int numberOfPlayer) {
		if(numberOfPlayer>players.length-1) return null;
		return players[numberOfPlayer];
	}

	/**
	 * 
	 * @return return the array of the player used to control they status for decide if the game is finished and for test
	 */

	public Player[] getPlayers() {
		return players;
	}
	
}
	
	
	
