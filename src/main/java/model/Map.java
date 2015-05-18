package model;

import java.util.List;

public class Map {
	
	private Sector[] listaSettori; 
	private Coordinate humanSector;
	private Coordinate alienSector;
	private List<Coordinate> hatchSectors;
	
	public Map(Sector[] listaSettori, Coordinate humanSector, Coordinate alienSector, List<Coordinate> hatchSectors) {
		this.humanSector = humanSector;
		this.alienSector = alienSector;
		this.hatchSectors = hatchSectors;
	}
	
	
	public Sector getSector(Coordinate coordinate) {
		return listaSettori[(coordinate.getX()-1)+(coordinate.getY()-1)*23];
	}
	
	public boolean isNull(Coordinate coordinate) {					
		return(listaSettori[(coordinate.getX()-1)+(coordinate.getY()-1)*23]==null);
	}
	
	
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
