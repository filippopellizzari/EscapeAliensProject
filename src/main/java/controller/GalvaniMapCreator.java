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
		try {
			listSectors =loadExagonalmap.loadMap(name, listSectors);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		this.map=new FermiMap(listSectors, loadExagonalmap.getHumanSector(), loadExagonalmap.getAlienSector(),loadExagonalmap.getHatchSectors());
		return map;
	}
}