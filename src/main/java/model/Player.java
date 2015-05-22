package model;

import java.util.ArrayList;

import java.util.List;

/**
 * 
 * @author Nicola
 * this class represent a player in the game with all its attributes
 * @see PlayerType
 *
 */

public class Player {
	
	private final  PlayerType playerType;
	private Sector currentSector;
	private int speed;
	private final int numberOfPlayer;
	private boolean alive;
	private List<ItemCard> itemCardPlayer;
	
	/**
	 * 
	 * @param playerType, there are 2 type of player Alien and Human
	 * @param currentSector, sector where there is this player
	 * @param speed, number of sector that player can pass in one round 
	 * @param numberOfPlayer	number of player, used to communicate with the controller
	 */
	
	public Player(PlayerType playerType, Sector currentSector, int speed, int numberOfPlayer) {
		this.playerType = playerType;
		this.currentSector = currentSector;
		this.speed = speed;
		this.numberOfPlayer = numberOfPlayer;
		this.alive = true;
		this.itemCardPlayer = new ArrayList<ItemCard>();
		
	}
	
	/**
	 * 
	 * @return the player's sector
	 */
	
	public Sector getCurrentSector() {
		return currentSector;
	}
	
	/**
	 * 
	 * @param currentSector, the sector where the player go is the new location of player
	 */

	public void setCurrentSector(Sector currentSector) {
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
	 * @return status of player, if player is Alive he/she can play, otherwise no
	 */

	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * 
	 * @param alive, set the new status of player it is used to kill a player
	 */

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * 
	 * @return player type, used by controller because a human can do some option denied at alien and vice versa
	 */

	public PlayerType getPlayerType() {
		return playerType;
	}
	
	/**
	 * 
	 * @return number of player, each player is a client and play with one playere identify by number
	 */
	
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}
	
	/**
	 * 
	 * @return item of player, used to control the number of the card because none can has more than 3 item card
	 */
	
	public List<ItemCard> getItemCardPlayer() {
		return itemCardPlayer;
	}
	
	/**
	 * 
	 * @param itemCard, add a card draw by a player, player draw item card when go in a dangerous sector and draw a sectorcard with the correct 
	 * symbols(item)
	 */
	
	public void addItemCardPlayer(ItemCard itemCard) {
		itemCardPlayer.add(itemCard);
	}
	
	/**
	 * 
	 * @param numberOfCard to remove from the player
	 * @return the card if its exit and null otherwise because
	 */
	
	public ItemCard removeItemCardPlayer(int numberOfCard) {
		if(itemCardPlayer.size()>=numberOfCard+1) 
			return itemCardPlayer.remove(numberOfCard);
		else return null;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (numberOfPlayer != other.numberOfPlayer)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player "+numberOfPlayer;
				
	}

	
}
