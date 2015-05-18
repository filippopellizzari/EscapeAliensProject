package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sector extends Coordinate{
	
	private final SectorType sectorType;
	private Queue<Player> players;
	private List<Coordinate> adjacent;
	private boolean open;
	
	public Sector (SectorType sectorType, int x, int y, List<Coordinate> adjacent, boolean open){
		super(x,y);
		this.sectorType = sectorType;
		this.adjacent = adjacent;
		this.players = new LinkedList<Player>();
		this.open = open;
	}
	
	public SectorType getSectorType() {
		return sectorType;
	}

	public List<Coordinate> getAdjacent() {
		return adjacent;
	}

	public void addPlayer(Player player){
		players.add(player);
	}
	
	public Player removePlayer() {
		return players.remove();
	}

	
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "Sector [name=" + sectorType 
				+ ", players=" + players + ", adjacent=" + adjacent + "]";
	}
	
	
	
	
}
