package model;

import java.util.List;

public abstract class Map {
	protected List<Sector> listaSettori;
	protected Game game;
	public abstract Sector getSector(int x, int y);
	public abstract boolean isNull(int x, int y);
}
