package model;

import java.util.List;

/**
 * This class is used to find a sector in a exagonal map, extension of Map
 * 
 * @see Map
 * @author Nicola
 * 
 *
 */

public class ExagonalMap extends Map{
	
	/**
	 * 
	 * @param sectors list of sector, this sectors compose the map
	 * @param humanSector coordinate of the human sector in the map
	 * @param alienSector coordinate of the alien sector in the map
	 * @param hatchSectors list of coordinate of each hatch sectors in the map 
	 */

	public ExagonalMap(Sector[] sectors, Coordinate humanSector,Coordinate alienSector, List<Coordinate> hatchSectors) {
		super(sectors, humanSector, alienSector, hatchSectors);
	}
	
	@Override	
	public Sector getSector(Coordinate coordinate) {							
			return sectors[(coordinate.getX()-1)+(coordinate.getY()-1)*23];
	}
	
	@Override
	public boolean isNull(Coordinate coordinate) {
		return(sectors[(coordinate.getX()-1)+(coordinate.getY()-1)*23]==null);
	}

}
