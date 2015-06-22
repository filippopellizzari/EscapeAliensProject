package model;

/**
 * This class contains all the features of the game from players to map
 * @author Nicola
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
	 * @return HatchCards, this is the deck and discard of Hatch cards
	 */
	
	public HatchCards getHatchCards() {
		return hatchCards;
	}
	
	/**
	 * @return SectorCards, this is the deck and discard of Sector cards
	 */
	
	public SectorCards getSectorCards() {
		return sectorCards;
	}
	
	/**
	 * @return ItemCards, this is the deck and discard of Item Hatch cards
	 */
	
	public ItemCards getItemCards() {
		return itemCards;
	}
	
	/**
	 * @return the map, the Map contains all sectors
	 */
	
	public Map getMap() {
		return map;
	}
	
	/**
	 * @param numberOfPlayer, used to referred at the right player
	 * @return if player doesn't exit so return null, otherwise return correct player
	 */

	public Player getPlayers(int numberOfPlayer) {
		return players[numberOfPlayer];
	}

	/**
	 * @return return the array of the player used to control they status for decide if the game is finished and for test
	 */

	public Player[] getPlayers() {
		return players;
	}
}