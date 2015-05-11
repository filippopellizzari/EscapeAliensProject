package controller;

import model.Coordinate;
import model.GalileiMap;
import model.Game;
import model.HatchSector;
import model.Map;
import model.Sector;
import model.TypeSector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GalileiMapCreator extends MapCreator{
	private List<Sector> listSectors=new ArrayList<Sector>(322);
	private int j;
	public GalileiMapCreator(Game game) {
		this.game=game;
	}
	public Map createMap() {
		FileReader fileRead=new FileReader("C:\\Users\\Nicola\\Documents\\Java Projects\\cg_27\\MapsFile\\GalileiMap.txt");
		BufferedReader br = new BufferedReader(fileRead); 
		String s;
		int x,y;
		TypeSector typeSector;
		boolean crossable;
		List<Coordinate> coordinate=new ArrayList<Coordinate>(6);
		while((s = br.readLine()) != null) {
			typeSector=new TypeSector(s); 		//COME DEBBO FARE
			crossable=(br.readLine()=="true");
			x=Integer.parseInt(s);
			y=Integer.parseInt(s);
			for(int i=0;i<6;i++) coordinate.set(i, new Coordinate(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
			if(typeSector!=TypeSector.Hatch) listSectors.set(j, new Sector(typeSector,crossable,x,y,coordinate));
			else listSectors.set(j, new HatchSector(typeSector,crossable,x,y,coordinate));
			j++;
		}
		this.map=new GalileiMap(listSectors,game);
		return map;
	}
}