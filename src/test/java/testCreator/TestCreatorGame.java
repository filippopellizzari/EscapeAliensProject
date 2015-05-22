
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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCreatorGame {
	static Game game;
	static GameCreator gameCreator;
	static ClassLoader classLoader;
	
	@BeforeClass public static void onlyOnce() {
		 
		
		gameCreator=GameCreator.getinstance();
		game=gameCreator.createGame("FermiMap", 3, "Hexagonal"); 
		
	}
	

	@Test
	public void testStreamToString() {
		ClassLoader classLoader = getClass().getClassLoader();
	   assertNotNull(classLoader.getResource("FermiMap.txt"));
	   
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
	public void testTakeTheWrongPlayer() {			//test number of player, class Game
		assertTrue(game.getPlayers(3)==null);
	}
	
	@Test
	public void testTypeOfPlayer() {				//test correct number of type of player, class playerCreator
		int alien=0;
		int human=0;
		for(int i=0;i<3;i++)
		{
			if(game.getPlayers(i).getPlayerType()==PlayerType.ALIEN) alien++;
			if(game.getPlayers(i).getPlayerType()==PlayerType.HUMAN) human++;
		}
		assertTrue(alien==2 && human==1);
	}
	
	@Test
	public void testCorrectPlayers() {				//test correct type of player and its features, class playerCreator
		Player player;
		for(int i=0;i<3;i++)
		{
			player=game.getPlayers(i);
			if(player.getPlayerType()==PlayerType.ALIEN)
				assertTrue(player.getCurrentSector()==game.getMap().getSector(game.getMap().getAlienSector()) 
				&& player.getSpeed()==2 && player.isAlive()==true && player.getItemCardPlayer().isEmpty()==true);
			else 
				assertTrue(player.getCurrentSector()==game.getMap().getSector(game.getMap().getHumanSector()) 
					&& player.getSpeed()==1 && player.isAlive()==true && player.getItemCardPlayer().isEmpty()==true);
		}
	}	
	
	@Test
	public void testMap() throws NumberFormatException, IOException {
		ExagonalMap map;
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
			/*
			for(int i=0;i<23;i++){
				for(int j=0;j<14;j++){
					if(game.getMap().isNull(new Coordinate(i,j))==false){
						if(game.getMap().getSector(new Coordinate(i,j))==sectors[(j-1)*23+i-1]) {
							assertTrue(true);
							
						}
					}
				}
			}
			
			assertTrue(humanSector==game.getMap().getHumanSector()&&alienSector==game.getMap().getAlienSector());
			for(int i=0;i<6;i++) 
				assertTrue(game.getMap().getHatchSectors().get(i)==hatchSectors.get(i));
			*/
		}
	}
}

