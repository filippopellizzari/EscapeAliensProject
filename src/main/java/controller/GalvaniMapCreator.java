package controller;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GalvaniMapCreator extends MapCreator{
	private List<Sector> listSectors=new ArrayList<Sector>(322);
	private String name="GalvaniMap.txt";
	public GalvaniMapCreator() {
	}
	public Map createMap() {
		LoadExagonalMap loadExagonalmap=new LoadExagonalMap();
		try {
			listSectors =loadExagonalmap.loadMap(name, listSectors);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Coordinate alienSector=loadExagonalmap.getAlienSector();
		Coordinate humanSector=loadExagonalmap.getHumanSector();
		this.map=new FermiMap(listSectors, humanSector, alienSector);
		return map;
	}
}