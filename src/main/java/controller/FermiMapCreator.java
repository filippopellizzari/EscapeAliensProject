package controller;

import model.*;

import java.io.IOException;

public class FermiMapCreator extends MapCreator{
	
	private Sector[] listSectors=new Sector[322];
	private String name="FermiMap.txt";
	
	@Override
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