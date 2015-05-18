package controller;

import java.io.IOException;

import model.*;

public class MapCreator {
	protected Map map;
	LoadExagonalMap loadExagonalmap;
	public MapCreator() {
		loadExagonalmap=new LoadExagonalMap();
	}
	
	public Map createMap(String name, String typeOfMap) {
		switch(typeOfMap) {
			default: LoadExagonalMap loadExagonalmap=new LoadExagonalMap();
			try {
				this.map =loadExagonalmap.loadMap(name);
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		return map;
	}
}
