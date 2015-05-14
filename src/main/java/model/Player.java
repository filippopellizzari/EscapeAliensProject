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
	//private boolean defense; //passa a true appena un giocatore pesca una carta Defense, si mantiene da un turno all'altro quindi è un attributo
	
	public Player(TypePlayer name, Sector currentSector, int speed, int numberOfPlayer) {
		this.name = name;
		this.currentSector = currentSector;
		this.speed = speed;
		this.numberOfPlayer = numberOfPlayer;
		this.alive = true;
		this.itemCardPlayer = new ArrayList<ItemCard>();
		//this.defense = false;
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
/*
	public boolean isDefense() {
		return defense;
	}

	public void setDefense(boolean defense) {
		this.defense = defense;
	}
	*/
	
}
