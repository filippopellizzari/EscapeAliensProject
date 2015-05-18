package model;

import java.util.List;

public class FermiMap extends ExagonalMap{
	
	public FermiMap(Sector[] lista, Coordinate humanSector, Coordinate alienSector, List<Coordinate> hatchSectors) {
		super(humanSector, alienSector ,hatchSectors);
		this.listaSettori=lista;
	}
	@Override
	public boolean isNull(Coordinate coordinate) {					//dice se esiste tale casella  //
		if(coordinate.getX()<=7 || coordinate.getX()>=17) return true;
		if(listaSettori[(coordinate.getX()-1)+(coordinate.getY()-1)*23]==null) return true;
		return false;
	}
}
