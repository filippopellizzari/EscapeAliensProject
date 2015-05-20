package model;

import java.util.ArrayList;
import java.util.List;

public class Sector extends Coordinate{
	
	private final SectorType sectorType;
	private final List<Player> players;
	private final List<Coordinate> adjacent;
	protected boolean close;						//used by hatch sector (subclasses)
	
	public Sector (SectorType sectorType, boolean close, int x, int y, List<Coordinate> adjacent){
		super(x,y);
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


	public List<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player){
		players.add(player);
	}
	
	public void removePlayer(Player player) { //LASCIALO COSI'! SERVE PER LA LOGICA
		players.remove(player);
	}

	public boolean isClose() {
		return close;
	}

	@Override
	public String toString() {
		return "Sector [sectorType=" + sectorType + ", getX()=" + getX()
				+ ", getY()=" + getY() + "]";
	}

	
}
