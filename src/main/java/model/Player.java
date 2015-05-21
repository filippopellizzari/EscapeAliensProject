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
	 * @param alive
	 */

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * 
	 * @return
	 */

	public PlayerType getPlayerType() {
		return playerType;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public List<ItemCard> getItemCardPlayer() {
		return itemCardPlayer;
	}
	
	/**
	 * 
	 * @param itemCard
	 */
	
	public void addItemCardPlayer(ItemCard itemCard) {
		itemCardPlayer.add(itemCard);
	}
	
	/**
	 * 
	 * @param numberOfCard
	 * @return
	 */
	
	public ItemCard removeItemCardPlayer(int numberOfCard) {
		if(itemCardPlayer.size()>=numberOfCard+1) 
			return itemCardPlayer.remove(numberOfCard);
		else return null;
	}
}
