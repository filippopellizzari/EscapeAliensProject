package controller;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GalileiMapCreator extends MapCreator{
	private List<Sector> listSectors=new ArrayList<Sector>(322);
	private String name="GalileiMap.txt";
	public GalileiMapCreator() {
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
		List<Coordinate> hatchSectors=loadExagonalmap.getHatchSectors();
		this.map=new GalileiMap(listSectors, humanSector, alienSector,hatchSectors);
		return map;
	}
}