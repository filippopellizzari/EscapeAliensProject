package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private final  PlayerType playerType;
	private Sector currentSector;
	private int speed;
	private final int numberOfPlayer;
	private boolean alive;
	private List<ItemCard> itemCardPlayer;
	
	public Player(PlayerType playerType, Sector currentSector, int speed, int numberOfPlayer) {
		this.playerType = playerType;
		this.currentSector = currentSector;
		this.speed = speed;
		this.numberOfPlayer = numberOfPlayer;
		this.alive = true;
		this.itemCardPlayer = new ArrayList<ItemCard>();
		
	}
	
	public Sector getCurrentSector() {
		return currentSector;
	}

	public void setCurrentSector(Sector currentSector) {
		this.currentSector = currentSector;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}
	
	public List<ItemCard> getItemCardPlayer() {
		return itemCardPlayer;
	}
	
	public void addItemCardPlayer(ItemCard itemCard) {
		itemCardPlayer.add(itemCard);
	}
	
	public ItemCard removeItemCardPlayer(int numberOfCard) {
		if(itemCardPlayer.size()>=numberOfCard+1) 
			return itemCardPlayer.remove(numberOfCard);
		else return null;
	}

	
}
