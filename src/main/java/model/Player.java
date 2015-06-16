package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Nicola
 * @author Filippo
 * this class represent a player in the game with all its attributes
 * @see PlayerType
 *
 */

public class Player {
	
	private PlayerType playerType;
	private PlayerState playerState;
	private Sector currentSector;
	private int speed;
	private final int numberOfPlayer;
	private boolean inGame;
	private final List<ItemCard> itemCardPlayer;
	
	/**
	 * 
	 * @param playerType, there are 2 type of player Alien and Human
	 * @param currentSector, sector where there is this player
	 * @param speed, number of sector that player can pass in one round 
	 * @param numberOfPlayer	number of player, used to communicate with the controller
	 */
	
	public Player(PlayerType playerType, Sector currentSector, int speed, int numberOfPlayer) {
		this.playerType = playerType;
		this.playerState = PlayerState.PLAYING;
		this.currentSector = currentSector;
		this.speed = speed;
		this.numberOfPlayer = numberOfPlayer;
		this.inGame = true;
		this.itemCardPlayer = new ArrayList<ItemCard>();
	}
	
	/**
	 * 
	 * @return player type, used by controller because a human can do some option denied at alien and vice versa
	 */

	public PlayerType getType() {
		return playerType;
	}
	
	/**
	 * 
	 * @param playerType
	 */
	
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}

	/**
	 * 
	 * @return the player's sector
	 */
	
	public Sector getSector() {
		return currentSector;
	}
	
	/**
	 * 
	 * @param currentSector, the sector where the player go is the new location of player
	 */

	public void setSector(Sector currentSector) {
		this.currentSector = currentSector;
	}
	
	/**
	 * 
	 * @return the player speed, used by controller to check the move
	 */

	public int getSpeed() {
		return speed;
	}
	
	/**
	 * 
	 * @param speed, used by alien player when kill a human to increase his speed
	 */

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * 
	 * @return number of player, each player is a client and plays with one number identifier
	 */
	
	public int getNumber() {
		return numberOfPlayer;
	}
	
	/**
	 * 
	 * @return true if player is in Game
	 */

	public boolean isInGame() {
		return inGame;
	}
	
	/**
	 * 
	 * @param inGame
	 */

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}


	/**
	 * 
	 * @return list of the itemCards of a player
	 */
	
	public List<ItemCard> getItem() {
		return itemCardPlayer;
	}
	
	/**add an item card at the end of the list of player's itemCards 
	 * 
	 * @param itemCard Object 
	 */
	
	public void addItem(ItemCard itemCard) {
		itemCardPlayer.add(itemCard);
	}
	
	/**remove an itemCard from the list;
	 * the new list size is oldsize-1; 
	 * new indexes at the right of element removed are
	 * oldindexes-1;
	 * 
	 * @param index of the itemCard to remove from the player's deck
	 * @return itemCard Object if exists, null otherwise
	 */
	
	public ItemCard removeItem(int index) {
			return itemCardPlayer.remove(index);
		
	}

	@Override
	public String toString() {
		return "Player "+ numberOfPlayer;
				
	}

	
}
