package model;

import java.util.List;

public class FermiMap extends ExagonalMap{
	public FermiMap(List<Sector> lista, Coordinate humanSector, Coordinate alienSector, List<Coordinate> hatchSectors) {
		super(humanSector, alienSector ,hatchSectors);
		this.listaSettori=lista;
	}
}
