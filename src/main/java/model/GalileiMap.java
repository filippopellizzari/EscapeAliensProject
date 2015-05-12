package model;

import java.util.List;

public class GalileiMap extends Map{

	public GalileiMap(List<Sector> lista, Coordinate humanSector, Coordinate alienSector, List<Coordinate> hatchSectors)
	{
		super(humanSector, alienSector, hatchSectors);
		this.listaSettori=lista;
	}
	@Override
	public Sector getSector(Coordinate coordinate) {
		return listaSettori.get((coordinate.getX()-1)+(coordinate.getY()-1)*23);			//sono 23 lettere e 14 righe quindi riga*23+colonna i - sono dovuti che la lista parte da 0
	}

	@Override
	public boolean isNull(Coordinate coordinate) {					//dice se esiste tale casella
		if(listaSettori.get((coordinate.getX()-1)+(coordinate.getY()-1)*23)==null) return true;
		return false;
	}
}
