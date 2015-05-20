package model;

import java.util.List;

public abstract class Map {						//abstract because the true Map is Exagonal, more extensible 
	
	protected Sector[] sectors; 				//all attributes are the same in each map but they are used by subclasses
	protected Coordinate humanSector;
	protected Coordinate alienSector;
	protected List<Coordinate> hatchSectors;
	
	public Map(Sector[] sectors, Coordinate humanSector, Coordinate alienSector, List<Coordinate> hatchSectors) {
		this.sectors = sectors;
		this.humanSector = humanSector;
		this.alienSector = alienSector;
		this.hatchSectors = hatchSectors;
	}
	
	
	public abstract Sector getSector(Coordinate coordinate);				//each type of map has its own method to get a sector and show if it is null
	
	public abstract boolean isNull(Coordinate coordinate);	
	
	public Coordinate getHumanSector() {
		return humanSector;
	}
	
	public Coordinate getAlienSector() {
		return alienSector;
	}
	
	public List<Coordinate> getHatchSectors() {
		return hatchSectors;
	}
	
}
