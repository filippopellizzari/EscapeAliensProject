package model;

import java.util.List;

public abstract class Map {
	protected List<Sector> listaSettori;
	protected Game game;
	protected Coordinate humanSector;
	protected Coordinate alienSector;
	public abstract Sector getSector(int x, int y);
	public abstract boolean isNull(int x, int y);
	public Coordinate getHumanSector() {
		return humanSector;
	}
	public Coordinate getAlienSector() {
		return alienSector;
	}
	public Map(Game game, Coordinate humanSector, Coordinate alienSector) {
		this.game = game;
		this.humanSector = humanSector;
		this.alienSector = alienSector;
	}
}
