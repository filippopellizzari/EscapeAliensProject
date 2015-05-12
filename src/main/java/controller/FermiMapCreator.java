package controller;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FermiMapCreator extends MapCreator{
	private List<Sector> listSectors=new ArrayList<Sector>(322);
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