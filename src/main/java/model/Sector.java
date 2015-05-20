package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sector extends Coordinate{
	
	private final SectorType sectorType;
	private Queue<Player> players;
	private List<Coordinate> adjacent;
	protected boolean close;						//used by hatch sector (subclasses)
	
	public Sector (SectorType sectorType, boolean close, int x, int y, List<Coordinate> adjacent){
		super(x,y);
		this.sectorType = sectorType;
		this.adjacent = adjacent;
		this.players = new LinkedList<Player>();
		this.close = close;
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

	public boolean isClose() {
		return close;
	}

	@Override
	public String toString() {
		return "Sector [name=" + sectorType 
				+ ", players=" + players + ", adjacent=" + adjacent + "]";
	}
}
