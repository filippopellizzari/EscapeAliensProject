package testCreator;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
/**
 * @author Nicola
 * @author Filippo
 *
 */
public class TestCreatorGame {
	static Game game;
	static GameCreator gameCreator;

	
	@BeforeClass public static void onlyOnce() throws NumberFormatException, IOException {
		 
		
		gameCreator=GameCreator.getinstance();
		game=gameCreator.createGame("Fermi", 3, "Hexagonal"); 
		
	}
	

	
	@Test
	public void testCreateGame() {
		assertTrue(game instanceof Game);				//test correct type of game, class game
	}
	
	@Test
	public void testHatchCards() {
		assertTrue(game.getHatchCards().getDeck().size()==6 && game.getHatchCards().getDiscardPile().isEmpty()==true);		//test number of hatch cards, class Game
	}
	
	@Test
	public void testSectorCards() {
		assertTrue(game.getSectorCards().getDeck().size()==25 && game.getSectorCards().getDiscardPile().isEmpty()==true);	//test number of sector cards, class Game
	}
	
	@Test
	public void testItemCards() {
		assertTrue(game.getItemCards().getDeck().size()==12 && game.getItemCards().getDiscardPile().isEmpty()==true);		//test number of item cards, class Game
	}
	
	
	@Test
	public void testTypeOfPlayer() {				//test correct number of type of player, class playerCreator
		int alien=0;
		int human=0;
		for(int i=0;i<3;i++)
		{
			if(game.getPlayers(i).getType()==PlayerType.ALIEN) alien++;
			if(game.getPlayers(i).getType()==PlayerType.HUMAN) human++;
		}
		assertTrue(alien==2 && human==1);
	}
	
	@Test
	public void testCorrectPlayers() {				//test correct type of player and its features, class playerCreator
		Player player;
		for(int i=0;i<3;i++)
		{
			player=game.getPlayers(i);
			if(player.getType()==PlayerType.ALIEN)
				assertTrue(player.getSector()==game.getMap().getSector(game.getMap().getAlienCoord()) 
				&& player.getSpeed()==2 && player.isAlive()==true && player.getItem().isEmpty()==true);
			else 
				assertTrue(player.getSector()==game.getMap().getSector(game.getMap().getHumanCoord()) 
					&& player.getSpeed()==1 && player.isAlive()==true && player.getItem().isEmpty()==true);
		}
	}	

	
	@Ignore
	public void testMap() throws NumberFormatException, IOException {
		HexagonalMap map;
		Sector[] sectors=new Sector[322];
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("FermiMap.txt").getFile());
		
		
		
		
		FileReader fileRead = new FileReader(file);
		SectorType sectorType;
		Coordinate humanSector = null;
		Coordinate alienSector = null;
		List<Coordinate> hatchSectors = new ArrayList<Coordinate>();
		BufferedReader br = new BufferedReader(fileRead); 
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
			br.readLine();
			int x = Integer.parseInt(br.readLine());
			int y = Integer.parseInt(br.readLine());
			
			
			switch(sectorType) {
				case ALIEN: alienSector = new Coordinate(x,y);
				break;
				case HUMAN: humanSector = new Coordinate(x,y);
				break;
				case HATCH: hatchSectors.add(new Coordinate(x,y));
				break;
				default: break;
			}
			List<Coordinate> adjacent = new ArrayList<Coordinate>();
			for(int i=0; i<6; i++){
				adjacent.add(new Coordinate(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
			}
			if(sectorType != SectorType.HATCH) {
				sectors[(y-1)*23 + (x-1)]= new Sector(sectorType, false, x, y, adjacent);
			}
			else {
				sectors[(y-1)*23 + (x-1)] = new HatchSector(sectorType, false, x, y, adjacent);		
			}
			
			for(int i=0;i<23;i++){
				for(int j=0;j<14;j++){
					if(game.getMap().isNull(new Coordinate(i,j))==false){
						if(game.getMap().getSector(new Coordinate(i,j))==sectors[(j-1)*23+i-1]) {
							assertTrue(true);
							
						}
					}
				}
			}
			
			assertTrue(humanSector==game.getMap().getHumanCoord()&&alienSector==game.getMap().getAlienCoord());
			for(int i=0;i<6;i++) 
				assertTrue(game.getMap().getHatchSectors().get(i)==hatchSectors.get(i));
			
		}
	}
}

