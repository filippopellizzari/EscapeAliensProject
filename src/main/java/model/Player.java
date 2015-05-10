package model;

import java.util.List;

public class Player {
	TypePlayer typePlayer;
	Sector actualSector;
	int speed;
	int numberOfPlayer;
	boolean alive;
	List<ItemCard> itemCardPlayer;
	public Player(TypePlayer typePlayer, Sector actualSector, int speed, int numberOfPlayer, boolean alive, List<ItemCard> itemCardPlayer) {
		this.typePlayer = typePlayer;
		this.actualSector = actualSector;
		this.speed = speed;
		this.numberOfPlayer = numberOfPlayer;
		this.alive = alive;
		this.itemCardPlayer=itemCardPlayer;
	}
	public TypePlayer getTypePlayer() {
		return typePlayer;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		if(typePlayer==TypePlayer.Alien) speed++;
	}
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public Sector getActualSector() {
		return actualSector;
	}
	public void setActualSector(Sector actualSector) {
		this.actualSector = actualSector;
	}
	public List<ItemCard> getItemCardPlayer() {
		//to do
		return itemCardPlayer;
	}
	public void addItemCardPlayer(List<ItemCard> itemCardPlayer) {
		//to do
	}
	public void removeItemCardPlayer(List<ItemCard> itemCardPlayer) {
		//to do
	}
	
}