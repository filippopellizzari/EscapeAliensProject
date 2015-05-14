package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private final  TypePlayer name;
	private Sector currentSector;
	private int speed;
	private final int numberOfPlayer;
	private boolean alive;
	private List<ItemCard> itemCardPlayer;
	
	public Player(TypePlayer name, Sector currentSector, int speed, int numberOfPlayer) {
		this.name = name;
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

	public TypePlayer getName() {
		return name;
	}

	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}
	
	
	public ItemCard getItemCardPlayer(int numberOfCard) {
		return itemCardPlayer.get(numberOfCard);
	}
	
	public void addItemCardPlayer(ItemCard itemCard) {
		itemCardPlayer.add(itemCard);
	}
	
	public ItemCard removeItemCardPlayer(int numberOfCard) {
		return itemCardPlayer.remove(numberOfCard);
	}

	
}
