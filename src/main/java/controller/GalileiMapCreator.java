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
	
	@Override
	public Map createMap() {
		try {
			listSectors =loadExagonalmap.loadMap(name, listSectors);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		this.map=new GalileiMap(listSectors, loadExagonalmap.getHumanSector(), loadExagonalmap.getAlienSector(),loadExagonalmap.getHatchSectors());
		return map;
	}
	
}