package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Nicola
 * In this class test I create a player and 3 sector link each other, this player has a single card
 *
 */

public class TestPlayer {
	static Sector start;
	static Player player;
	static Sector sector1;		//first sector where player has to go
	static Sector sector2;
	

	
	/**
	 * I create the player, the card and the sectors
	 */
	
	@BeforeClass public static void onlyOnce() {
		List<Coordinate> coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(2, 1));
		coordinate.add(new Coordinate(1,2));
		coordinate.add(new Coordinate(-1,-1));
		start=new Sector(SectorType.ALIEN,true,1,1,coordinate);
		player=new Player(PlayerType.ALIEN,start,2,1);
		player.setSpeed(3); 											//use the method setSpeed, classPlayer
		start.addPlayer(player);									//use the method addPlayer, class Sector
		coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(1,1));
		coordinate.add(new Coordinate(2,1));
		coordinate.add(new Coordinate(2,2));
		coordinate.add(new Coordinate(1,3));
		coordinate.add(new Coordinate(-1,-1));
		sector1 = new Sector(SectorType.SECURE,false,1,2,coordinate);
		coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(1,2));
		coordinate.add(new Coordinate(2,2));
		coordinate.add(new Coordinate(2,3));
		coordinate.add(new Coordinate(1,4));
		coordinate.add(new Coordinate(-1,-1));
		sector2 = new HatchSector(SectorType.HATCH,false,1,3,coordinate);									
	}
	
	/**
	 * I test if the player has the correct speed and is in the right sector
	 */
	
	@Test
	public void TestSpeedAndSector() {
		assertTrue(player.getSpeed()==3 && player.getSector()==start);	//test if the speed is 2 and is sector of Player is the same that i have passed before, classPlayer;
	}
	
	/**
	 * I verify the correct remove of a player from a sector
	 */
	
	@Test
	public void testremoveAndAdd() {
		assertEquals(player,start.removePlayer());				//test if remove return the same player that i have passed before, class Sector
		start.addPlayer(player);	
		
	}
	
	/**
	 * I verify the correct type of the sectors
	 */
	
	@Test
	public void testCorrectTypeOfSector() {
		assertTrue(start.getType()==SectorType.ALIEN && sector1.getType()==SectorType.SECURE && sector2.getType()==SectorType.HATCH); //test type of sector, class SectorType
	}
	
	/**
	 * test if a player is alive
	 */
	@Test
	public void testAlive() {
		assertTrue(player.isAlive());
	}
	/**
	 * test of alien PlayerType
	 */
	@Test 
	public void testAlienType(){
		assertEquals(player.getType(), PlayerType.ALIEN);
	}
	/**
	 * test of human PlayerType
	 */
	@Test
	public void testHumanType(){
		player.setPlayerType(PlayerType.HUMAN);
		assertEquals(player.getType(), PlayerType.HUMAN);
	}
	/**
	 * test of NumberPlayer
	 */
	@Test
	public void testNumber(){
		assertEquals(player.getNumber(),1);
	}
	

	/**
	 * test verifies that player has 3 ItemCards,
	 * after adding 3 item cards to his personal deck
	 */
	@Test
	public void testAddItem() {
		player.addItem(new ItemCard(ItemCardType.ADRENALINE));
		player.addItem(new ItemCard(ItemCardType.ATTACK));
		player.addItem(new ItemCard(ItemCardType.ADRENALINE));
		
		assertEquals(player.getItem().size(),3);
									
		
	}
	/**
	 * test of removeItem; when an element is removed from the 
	 * ArrayList of ItemCard, subtracts one from the indices of the
	 * element in the right
	 */
	@Test
	public void testRemoveItem(){
		player.removeItem(0);
		ItemCard itemCard2 = player.removeItem(0);
		assertEquals(itemCard2.getType(), ItemCardType.ATTACK);
	}
	
	/**
	 * I test the player when he move from sector start to sector1
	 */
	
	@Test
	public void testPlayeMove1() {
		int i=0;
		boolean condition=false;
		while(i<6 && condition==false) {
			if(player.getSector().getAdjacent().get(i).equals(sector1.getCoordinate()))
			{
				if(sector1.isClosed()==false) {						//control if the sector is accessible
					condition=true;
					sector1.addPlayer(start.removePlayer());
					player.setSector(sector1);
				}
			}
			i++;
		}
		assertEquals(player.getSector(),sector1);	//test if the sector of player is sector1, classPlayer
		start.addPlayer(sector1.removePlayer());
		player.setSector(start);
	}
	
	/**
	 * I test that the player move correctly from sector 1 to sector 2
	 */
	
	@Test
	public void testPlayerMove2() {
		sector1.addPlayer(start.removePlayer());
		player.setSector(sector1);
		int i=0;
		boolean condition=false;
		while(i<6 && condition==false) {
			if(player.getSector().getAdjacent().get(i).equals(sector2.getCoordinate())) {
				if(sector2.isClosed()==false) {						//control if the sector is accessible
					condition=true;
					sector2.addPlayer(sector1.removePlayer());
					player.setSector(sector2);
					player.setAlive(false); 					//player is dead
				}
			}
			i++;
		}
		assertEquals(player.getSector(),sector2);	//test if the sector of player is sector1, classPlayer

		start.addPlayer(sector2.removePlayer());
		player.setSector(start);

	}
	/**
	 * I verify that sector2 (hatch) is close after player enter it and the player is dead
	 */
	
	@Test
	public void testHatchSector() {
		assertTrue(sector2.isClosed()==true && player.isAlive()==false); 	//control if the player is really dead and the sector is close, class HatchSector
	}
	
	/**
	 * I verify the position of the player
	 */
	
	@Test
	public void testPositionOfPlayer() {
		assertTrue(start.getPlayers().size()==1&& sector1.getPlayers().size()==0 && sector2.getPlayers().size()==0);
	}
	
}
