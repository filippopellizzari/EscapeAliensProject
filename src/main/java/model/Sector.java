package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sector extends Coordinate{
	
	private TypeSector name;
	protected boolean crossable;
	Queue<Player> playerQueue=new LinkedList<Player>();
	List<Coordinate> coordinate;
	private Queue<Player> players;
	private final List<Coordinate> adjacent;
	
	public Sector (TypeSector name, boolean crossable, int x, int y, List<Coordinate> adjacent){
		super(x,y);
		this.name=name;
		this.crossable=crossable;
		this.adjacent=adjacent;
		this.players = new LinkedList<Player>();
	}
	
	
	
	public TypeSector getName() {
		return name;
	}

	public void setName(TypeSector name) {
		this.name = name;
	}

	public List<Coordinate> getAdjacent() {
		return adjacent;
	}

	public void setCrossable(boolean crossable) {
		this.crossable = crossable;
	}

	public boolean isCrossable(){
		return crossable;
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public Player removePlayer() {
		return players.remove();
	}
	
	
}
