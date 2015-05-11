package model;

import java.util.List;

public class FermiMap extends Map{
	public FermiMap(List<Sector> lista, Game game, Coordinate humanSector, Coordinate alienSector)
	{
		super(game, humanSector, alienSector);
		this.listaSettori=lista;
	}
	@Override
	public Sector getSector(int x, int y) {
		return listaSettori.get((x-1)+(y-1)*23);			//sono 23 lettere e 14 colonne quindi riga*23+colonna i - sono dovuti che la lista parte da 0
	}

	@Override
	public boolean isNull(int x, int y) {					//dice se esiste tale casella
		if(listaSettori.get((x-1)+(y-1)*14)==null) return true;
		return false;
	}
}
