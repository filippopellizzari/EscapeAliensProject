package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadExagonalMap {
	int j=0;
	Coordinate alienSector;
	Coordinate humanSector;
	public List<Sector> loadMap(String namefile, List<Sector> listSectors) {
		FileReader fileRead=new FileReader(namefile);
		BufferedReader br = new BufferedReader(fileRead); 
		String s;
		int x,y;
		TypeSector typeSector;
		boolean crossable;
		List<Coordinate> coordinate=new ArrayList<Coordinate>(6);
		while((s = br.readLine()) != null) {
			typeSector = (TypeSector) br.readLine(); 		//COME DEBBO FARE
			if(typeSector!=TypeSector.Alien && typeSector!=TypeSector.Human) {
				crossable=(br.readLine()=="true");
				x=Integer.parseInt(br.readLine());
				y=Integer.parseInt(br.readLine());
				for(int i=0;i<6;i++) coordinate.set(i, new Coordinate(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
				if(typeSector!=TypeSector.Hatch) listSectors.set(j, new Sector(typeSector,crossable,x,y,coordinate));
				else listSectors.set(j, new HatchSector(typeSector,crossable,x,y,coordinate));
				j++;
			}
			else {
				if(typeSector!=TypeSector.Alien) alienSector=new Coordinate(x,y);
				else humanSector=new Coordinate(x,y);
			}
		}
		return listSectors;
	}
	public Coordinate getAlienSector() {
		return alienSector;
	}
	public Coordinate getHumanSector() {
		return humanSector;
	}
}