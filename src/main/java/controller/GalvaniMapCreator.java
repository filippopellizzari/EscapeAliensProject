package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class GalvaniMapCreator extends MapCreator{
	private List<Sector> listSectors=new ArrayList<Sector>(322);
	private String name="GalvaniMap.txt";
	private Game game;
	public GalvaniMapCreator(Game game) {
		this.game=game;
	}
	public Map createMap() {
		LoadExagonalMap loadExagonalmap=new LoadExagonalMap();
		listSectors =loadExagonalmap.loadMap(name, listSectors);
		Coordinate alienSector=loadExagonalmap.getAlienSector();
		Coordinate humanSector=loadExagonalmap.getHumanSector();
		this.map=new FermiMap(listSectors,game, humanSector, alienSector);
		return map;
	}
}