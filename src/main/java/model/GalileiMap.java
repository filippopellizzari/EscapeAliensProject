package model;

import java.util.List;

public class GalileiMap extends ExagonalMap{
	
	public GalileiMap(List<Sector> lista, Coordinate humanSector, Coordinate alienSector, List<Coordinate> hatchSectors){
		super(humanSector, alienSector, hatchSectors);
		this.listaSettori=lista;
	}
	
}
