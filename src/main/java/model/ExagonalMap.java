package model;

import java.util.List;

public class ExagonalMap extends Map{

	public ExagonalMap(Sector[] sectors, Coordinate humanSector,Coordinate alienSector, List<Coordinate> hatchSectors) {
		super(sectors, humanSector, alienSector, hatchSectors);
	}

	@Override	
	public Sector getSector(Coordinate coordinate) {								//to find a sector 
		return sectors[(coordinate.getX()-1)+(coordinate.getY()-1)*23];
	}

	@Override
	public boolean isNull(Coordinate coordinate) {
		return(sectors[(coordinate.getX()-1)+(coordinate.getY()-1)*23]==null);
	}

}
