package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sector extends Coordinate{
	
	private final SectorType sectorType;
	protected boolean crossable;
	private Queue<Player> players;
	private final List<Coordinate> adjacent;
	
	public Sector (SectorType sectorType, boolean crossable, int x, int y, List<Coordinate> adjacent){
		super(x,y);
		this.sectorType=sectorType;
		this.crossable=crossable;
		this.adjacent=adjacent;
		this.players = new LinkedList<Player>();
	}
	
	public SectorType getSectorType() {
		return sectorType;
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

	@Override
	public String toString() {
		return "Sector [name=" + sectorType + ", crossable=" + crossable
				+ ", players=" + players + ", adjacent=" + adjacent + "]";
	}
	
	
	
	
}
