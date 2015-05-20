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
	
	public void removeItemCardPlayer(ItemCard itemCard) { //LASCIALO COSI'! SERVE PER LA LOGICA
		itemCardPlayer.remove(itemCard);
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfPlayer;
		result = prime * result
				+ ((playerType == null) ? 0 : playerType.hashCode());
		return result;
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
		if (playerType != other.playerType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player "+numberOfPlayer;
				
	}

	
	
}
