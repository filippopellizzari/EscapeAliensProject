package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;

import org.junit.Test;

public class TestPlayer {
	
	
	@Test
	public void testPlayerFunction() {
		List<Coordinate> coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(2, 1));
		coordinate.add(new Coordinate(1,2));
		coordinate.add(new Coordinate(-1,-1));
		Sector playerSector=new Sector(SectorType.ALIEN,true,1,1,coordinate);
		System.out.println(playerSector.toString());					//use the method toString, class Sector
		Player player=new Player(PlayerType.ALIEN,playerSector,2,1);
		assertTrue(player.getSpeed()==2 && player.getCurrentSector()==playerSector);	//test if the speed is 2 and is sector of Player is the same that i have passed before, classPlayer;
		player.setSpeed(3); 											//use the method setSpeed, classPlayer
		playerSector.addPlayer(player);									//use the method addPlayer, class Sector
		assertEquals(player,playerSector.removePlayer());				//test if remove return the same player that i have passed before, class Sector
		coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(1,1));
		coordinate.add(new Coordinate(2,1));
		coordinate.add(new Coordinate(2,2));
		coordinate.add(new Coordinate(1,3));
		coordinate.add(new Coordinate(-1,-1));
		Sector sector1=new Sector(SectorType.SECURE,false,1,2,coordinate);
		coordinate=new ArrayList<Coordinate>();
		coordinate.add(new Coordinate(-1,-1));
		coordinate.add(new Coordinate(1,2));
		coordinate.add(new Coordinate(2,2));
		coordinate.add(new Coordinate(2,3));
		coordinate.add(new Coordinate(1,4));
		coordinate.add(new Coordinate(-1,-1));
		Sector sector2=new HatchSector(SectorType.HATCH,false,1,3,coordinate);
		assertTrue(playerSector.getSectorType()==SectorType.ALIEN && sector1.getSectorType()==SectorType.SECURE && sector2.getSectorType()==SectorType.HATCH); //test type of sector, class SectorType
		assertTrue(player.getNumberOfPlayer()==1 && player.isAlive()==true && player.getPlayerType()==PlayerType.ALIEN);
		ItemCard card=new ItemCard(ItemCardType.ADRENALINE);
		ItemCard card2;
		player.addItemCardPlayer(card);									//use the function addcard, class Player
		assertTrue(player.getItemCardPlayer().size()==1);				//test number of item card, class Player
		card2=player.removeItemCardPlayer(1);
		assertEquals(card2,null);										//test removeCardItemPlayer when the card is null, class Player
		card2=player.removeItemCardPlayer(0);
		assertEquals(card2,card);										//test if the card pass and the card discarded is the same, class Player
		for(int i=0;i<6;i++) {
			if(player.getCurrentSector().getAdjacent().get(i)==new Coordinate(sector1.getX(),sector1.getY())) {
				
			}
		}
		
	}
}
