package controller;

import model.*;

import java.io.IOException;

public class GalileiMapCreator extends MapCreator{
	
	private Sector[] listSectors=new Sector[322];
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