package creator;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadExagonalMap {
	
	private Sector[] sectors;
	private Coordinate alienSector;
	private Coordinate humanSector;
	private List<Coordinate> hatchSectors;
	
	public LoadExagonalMap() {
		this.hatchSectors = new ArrayList<Coordinate>();
		this.sectors = new Sector[322];
	}

	public Map loadMap(String fileName) throws NumberFormatException, IOException {
		
		FileReader fileRead = new FileReader(fileName+".txt");
		BufferedReader br = new BufferedReader(fileRead); 
		
		SectorType sectorType;
		
		String s;
		while((s = br.readLine()) != null) {
				
			//assegnazione del tipo di settore
			switch(s) {
				case "Alien": sectorType = SectorType.ALIEN;
				break;
				case "Human": sectorType = SectorType.HUMAN;
				break;
				case "Hatch": sectorType = SectorType.HATCH;
				break;
				case "Dangerous": sectorType = SectorType.DANGEROUS;
				break;
				default: sectorType = SectorType.SECURE;
				break;
			}
			boolean close = (br.readLine()=="true");
			int x = Integer.parseInt(br.readLine());
			int y = Integer.parseInt(br.readLine());
			
			
			switch(sectorType) {
				case ALIEN: this.alienSector = new Coordinate(x,y);
				break;
				case HUMAN: this.humanSector = new Coordinate(x,y);
				break;
				case HATCH: hatchSectors.add(new Coordinate(x,y));
				break;
				default: break;
			}
			
			//6 settori adiacenti a un settore
			List<Coordinate> adjacent = new ArrayList<Coordinate>();
			for(int i=0; i<6; i++){
				adjacent.add(new Coordinate(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
			}
			
			
			//creazione concreta del settore
			if(sectorType != SectorType.HATCH) {
				sectors[(y-1)*23 + (x-1)]= new Sector(sectorType, close, x, y, adjacent);
			}
			else {
				sectors[(y-1)*23 + (x-1)] = new HatchSector(sectorType, close, x, y, adjacent);		
			}
			
		}
		br.close();
		
		return new ExagonalMap(sectors, humanSector, alienSector, hatchSectors);
		
	}
}
