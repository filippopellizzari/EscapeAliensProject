package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPlayer {
	static Sector playerSector;
	static Player player;
	static Sector sector1;		//first sector where player has to go
	static Sector sector2;
	static ItemCard card;
	static ItemCard card2;
	
	@BeforeClass public static void onlyOnce() {
		List<Coordinate> coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(2, 1));
		coordinate.add(new Coordinate(1,2));
		coordinate.add(new Coordinate(-1,-1));
		playerSector=new Sector(SectorType.ALIEN,true,1,1,coordinate);
		System.out.println(playerSector.toString());					//use the method toString, class Sector
		player=new Player(PlayerType.ALIEN,playerSector,2,1);
		player.setSpeed(3); 											//use the method setSpeed, classPlayer
		playerSector.addPlayer(player);									//use the method addPlayer, class Sector
		coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(1,1));
		coordinate.add(new Coordinate(2,1));
		coordinate.add(new Coordinate(2,2));
		coordinate.add(new Coordinate(1,3));
		coordinate.add(new Coordinate(-1,-1));
		sector1=new Sector(SectorType.SECURE,false,1,2,coordinate);
		coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(1,2));
		coordinate.add(new Coordinate(2,2));
		coordinate.add(new Coordinate(2,3));
		coordinate.add(new Coordinate(1,4));
		coordinate.add(new Coordinate(-1,-1));
		sector2=new HatchSector(SectorType.HATCH,false,1,3,coordinate);
		card=new ItemCard(ItemCardType.ADRENALINE);
		player.addItemCardPlayer(card);									//use the function addcard, class Player
	}
	
	@Test
	public void TestSpeedAndSector() {
		assertTrue(player.getSpeed()==3 && player.getCurrentSector()==playerSector);	//test if the speed is 2 and is sector of Player is the same that i have passed before, classPlayer;
	}
	
	@Test
	public void testremoveAndAdd() {
		assertEquals(player,playerSector.removePlayer());				//test if remove return the same player that i have passed before, class Sector
		playerSector.addPlayer(player);	
		
	}
	
	@Test
	public void testCorrectTypeOfSector() {
		assertTrue(playerSector.getSectorType()==SectorType.ALIEN && sector1.getSectorType()==SectorType.SECURE && sector2.getSectorType()==SectorType.HATCH); //test type of sector, class SectorType
	}
	
	@Test
	public void testAliveAndType() {
		assertTrue(player.getNumberOfPlayer()==1 && player.isAlive()==true && player.getPlayerType()==PlayerType.ALIEN);
	}
	
	@Test
	public void testNumberCardOfPlayer() {
		assertTrue(player.getItemCardPlayer().size()==1);				//test number of item card, class Player
	}
	
	@Test
	public void testRemoveCard() {
		card2=player.removeItemCardPlayer(1);
		assertEquals(card2,null);										//test removeCardItemPlayer when the card is null, class Player
	}
	
	@Test
	public void testSameCardPassed() {
		card2=player.removeItemCardPlayer(0);
		player.addItemCardPlayer(card2);
		assertEquals(card2,card);										//test if the card pass and the card discarded is the same, class Player
		
	}
	
	@Test
	public void testPlayeMove1() {
		int i=0;
		boolean condition=false;
		Player player2=player;	
		Sector sector3=sector1;
		while(i<6 && condition==false) {
			if(player.getCurrentSector().getAdjacent().get(i).equals(sector1.getCoordinate()))
			{
				if(sector1.isClose()==false) {						//control if the sector is accessible
					condition=true;
					sector1.addPlayer(playerSector.removePlayer());
					player.setCurrentSector(sector1);
				}
			}
			i++;
		}
		assertEquals(player.getCurrentSector(),sector1);	//test if the sector of player is sector1, classPlayer
		playerSector.addPlayer(sector1.removePlayer());
		player.setCurrentSector(playerSector);
	}
	@Test
	public void testPlayerMove2() {
		sector1.addPlayer(playerSector.removePlayer());
		player.setCurrentSector(sector1);
		int i=0;
		boolean condition=false;
		while(i<6 && condition==false) {
			if(player.getCurrentSector().getAdjacent().get(i).equals(sector2.getCoordinate())) {
				if(sector2.isClose()==false) {						//control if the sector is accessible
					condition=true;
					sector2.addPlayer(sector1.removePlayer());
					player.setCurrentSector(sector2);
					player.setAlive(false); 					//player is dead
				}
			}
			i++;
		}
		assertEquals(player.getCurrentSector(),sector2);	//test if the sector of player is sector1, classPlayer
		playerSector.addPlayer(sector2.removePlayer());
		player.setCurrentSector(playerSector);
		assertTrue(sector2.isClose()==true && player.isAlive()==false); 	//control if the player is really dead and the sector is close, class HatchSector
	}
}
