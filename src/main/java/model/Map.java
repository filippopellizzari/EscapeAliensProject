package model;

import java.util.List;

public abstract class Map {
	
	private List<Sector> listaSettori;
	private Coordinate humanSector;
	private Coordinate alienSector;
	public abstract Sector getSector(Coordinate coordinate);
	public abstract boolean isNull(Coordinate coordinate);
	
	public Coordinate getHumanSector() {
		return humanSector;
	}
	
	public Coordinate getAlienSector() {
		return alienSector;
	}
	
	public Map(Coordinate humanSector, Coordinate alienSector) {
		this.humanSector = humanSector;
		this.alienSector = alienSector;
	}
}
