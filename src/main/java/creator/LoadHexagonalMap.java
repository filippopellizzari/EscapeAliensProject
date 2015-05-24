package creator;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class load an exagonal map from the corresponding file
 * @author Nicola
 *
 */

public class LoadHexagonalMap {
	
	private Sector[] sectors;
	private Coordinate alienSector;
	private Coordinate humanSector;
	private List<Coordinate> hatchSectors;
	
	/**
	 * 
	 */
	
	public LoadHexagonalMap() {
		this.hatchSectors = new ArrayList<Coordinate>();
		this.sectors = new Sector[322];
	}
	
	/**
	 * 
	 * @param fileName, name of file 
	 * @return exagonal map
	 * @throws NumberFormatException
	 * @throws IOException
	 */

	public Map loadMap(String fileName) throws NumberFormatException, IOException {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName+"Map.txt").getFile());
		
		FileReader fileRead = new FileReader(file);
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
			
			boolean closed = Boolean.parseBoolean(br.readLine()); 
			
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
			for(int i = 0; i < 6; i++){
				adjacent.add(new Coordinate(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
			}
			
			
			//creazione concreta del settore
			if(sectorType != SectorType.HATCH) {
				sectors[(y-1)*23 + (x-1)]= new Sector(sectorType, closed, x, y, adjacent);
			}
			else {
				sectors[(y-1)*23 + (x-1)] = new HatchSector(sectorType, closed, x, y, adjacent);		
			}
			
		}
		br.close();
		
		return new HexagonalMap(sectors, humanSector, alienSector, hatchSectors);
		
	}
}
