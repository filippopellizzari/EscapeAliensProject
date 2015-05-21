package model;

import java.util.ArrayList;
import java.util.List;

public class Sector {
	
	private final SectorType sectorType;
	private List<Player> players;
	private List<Coordinate> adjacent;
	protected boolean close;						//used by hatch sector (subclasses)
	Coordinate coordinate;
	
	public Sector (SectorType sectorType, boolean close, int x, int y, List<Coordinate> adjacent){
		this.coordinate=new Coordinate(x,y);
		this.sectorType = sectorType;
		this.adjacent = adjacent;
		this.players = new ArrayList<Player>();
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
		return players.remove(0);
	}

	public boolean isClose() {
		return close;
	}
	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	@Override
	public String toString() {
		return "Sector [name=" + sectorType 
				+ ", players=" + players + ", adjacent=" + adjacent + "]";
	}
}
