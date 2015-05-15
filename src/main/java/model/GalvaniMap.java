package model;

import java.util.List;

public class GalvaniMap extends ExagonalMap{
	
	public GalvaniMap(Sector[] lista, Coordinate humanSector, Coordinate alienSector, List<Coordinate> hatchSectors){
		super(humanSector, alienSector, hatchSectors);
		this.listaSettori=lista;
	}
}
