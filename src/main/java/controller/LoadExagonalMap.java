package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadExagonalMap {
	
	private Coordinate alienSector;
	private Coordinate humanSector;
	private List<Coordinate> hatchSectors;
	private Sector[] listSectors;
	private Map map;
	
	public LoadExagonalMap() {
		hatchSectors=new ArrayList<Coordinate>();
		this.listSectors=new Sector[322];
	}

	public Map loadMap(String namefile) throws NumberFormatException, IOException {
		FileReader fileRead=new FileReader("C:\\Users\\Nicola\\Documents\\ANNO 3\\Ingegneria del Software\\Progetto\\"+namefile);
		BufferedReader br = new BufferedReader(fileRead); 
		int x=0, y=0;
		TypeSector typeSector;
		boolean crossable;
		List<Coordinate> adjacent=new ArrayList<Coordinate>();
		String s;
		while((s=br.readLine()) != null) {
			switch(s) {
				case "Alien": typeSector=TypeSector.ALIEN;
				break;
				case "Human": typeSector=TypeSector.HUMAN;
				break;
				case "Hatch": typeSector=TypeSector.HATCH;
				break;
				case "Dangerous": typeSector=TypeSector.DANGEROUS;
				break;
				default: typeSector=TypeSector.SECURE;
				break;
			}
			crossable=(br.readLine()=="true");
			x=Integer.parseInt(br.readLine());
			y=Integer.parseInt(br.readLine());
			switch(typeSector) {
				case ALIEN: this.alienSector=new Coordinate(x,y);
				break;
				case HUMAN: this.humanSector=new Coordinate(x,y);
				break;
				case HATCH: hatchSectors.add(new Coordinate(x,y));
				break;
				default: break;
			}
			for(int i=0;i<6;i++) 
				adjacent.add(new Coordinate(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
			
			if(typeSector!=TypeSector.HATCH) 
				listSectors[(y-1)*23+x-1]= new Sector(typeSector,crossable,x,y,adjacent);		//mettiamo nel file solo i settori presenti quindi una volta prese le 
			else 
				listSectors[(y-1)*23+x-1]= new HatchSector(typeSector,crossable,x,y,adjacent);								//coordinate inseriamo il settore al suo posto
		}
		br.close();
		map=new ExagonalMap(listSectors,humanSector,alienSector,hatchSectors);
		return map;
	}
}
