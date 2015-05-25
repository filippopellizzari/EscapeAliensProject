package model;

import java.util.List;

/**
 * This class is the map of the game, it has all of the sector, the coordinate of human, alien e hatch sector
 * in some sector there are players, all the game is based in the map
 * @author Nicola
 * @author Filippo
 * @see Exagonal Map to know how sector is passed is an extension and it's used to use exagonal map
 * @see Sector to see how propriety has the map
 *
 */

public abstract class Map {						//abstract because the true Map is Exagonal, more extensible 
	
	protected final Sector[] sectors; 				//all attributes are the same in each map but they are used by subclasses
	protected final Coordinate humanCoord;
	protected final Coordinate alienCoord;
	protected final List<Coordinate> hatchSectors;
	
	/**
	 * 
	 * @param sectors list of sector, this sectors compose the map
	 * @param humanCoord coordinate of the human sector in the map
	 * @param alienCoord coordinate of the alien sector in the map
	 * @param hatchSectors list of coordinate of each hatch sectors in the map 
	 * */
	
	public Map(Sector[] sectors, Coordinate humanCoord, Coordinate alienCoord, List<Coordinate> hatchSectors) {
		this.sectors = sectors;
		this.humanCoord = humanCoord;
		this.alienCoord = alienCoord;
		this.hatchSectors = hatchSectors;
	}
	
	/**
	 * 
	 * @param coordinate, the coordinate passed to ask a sector
	 * @return sectors return the sector of the right coordinate use the method isNull to test if the sector is null
	 * if it isn't return the sector otherwise return null
	 */
	
	public abstract Sector getSector(Coordinate coordinate);				//each type of map has its own method to get a sector and show if it is null
	
	/**
	 * 
	 * @param coordinate, the coordinate passed to ask if this sector exist or not
	 * @return boolean this method is used to find if the sector exist or not
	 */

	public abstract boolean isNull(Coordinate coordinate);	
	
	/**
	 * 
	 * @return coordinate of humanSector, all the human player start in human sector
	 */
	
	public Coordinate getHumanCoord() {
		return humanCoord;
	}
	
	/**
	 * 
	 * @return coordinate of alienSector, all the alien player start in alien sector
	 */
	
	public Coordinate getAlienCoord() {
		return alienCoord;
	}
	
	/**
	 * 
	 * @return the list of the coordinate of all hatch sectors in the map
	 */
	
	public List<Coordinate> getHatchSectors() {
		return hatchSectors;
	}
	/**
	 * 
	 * @return array of all the sectors;
	 */

	public Sector[] getSectors() {
		return sectors;
	}
	
	
	
}
