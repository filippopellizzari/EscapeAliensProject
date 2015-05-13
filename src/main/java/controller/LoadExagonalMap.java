package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadExagonalMap {
	
	private Coordinate alienSector;
	private Coordinate humanSector;
	private List<Coordinate> hatchSectors;
	
	public LoadExagonalMap() {
		hatchSectors=new ArrayList<Coordinate>();
	}
	
	public List<Sector> loadMap(String namefile, List<Sector> listSectors) throws NumberFormatException, IOException {
		FileReader fileRead=new FileReader(""+namefile);
		BufferedReader br = new BufferedReader(fileRead); 
		int x=0, y=0;
		TypeSector typeSector;
		boolean crossable;
		List<Coordinate> coordinate=new ArrayList<Coordinate>(6);
		while((br.readLine()) != null) {
			x=Integer.parseInt(br.readLine());
			y=Integer.parseInt(br.readLine());
			typeSector = TypeSector.valueOf(br.readLine());
			switch(typeSector) {
				case Alien: this.alienSector=new Coordinate(x,y);
				break;
				case Human: this.humanSector=new Coordinate(x,y);
				break;
				case Hatch: hatchSectors.add(new Coordinate(x,y));
				break;
				default: break;
			}
			if(typeSector==TypeSector.Alien || typeSector==TypeSector.Human) {
				crossable=(br.readLine()=="true");
				for(int i=0;i<6;i++) 
					coordinate.set(i, new Coordinate(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
				if(typeSector!=TypeSector.Hatch) 
					listSectors.set((y-1)*23+x-1, new Sector(typeSector,crossable,x,y,coordinate));		//mettiamo nel file solo i settori presenti quindi una volta prese le 
				else 
					listSectors.set((y-1)*23+x-1, new HatchSector(typeSector,crossable,x,y,coordinate));								//coordinate inseriamo il settore al suo posto
			}																															//se è hatch va dichiarato diversamente
			else {
				if(typeSector!=TypeSector.Alien) 
					alienSector=new Coordinate(x,y);
				else 
					humanSector=new Coordinate(x,y);
			}
		}
		br.close();
		return listSectors;
	}
	public Coordinate getAlienSector() {
		return alienSector;
	}
	public Coordinate getHumanSector() {
		return humanSector;
	}
	public List<Coordinate> getHatchSectors() {
		return hatchSectors;
	}
}
