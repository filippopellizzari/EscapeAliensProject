package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

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
		Player player=new Player(PlayerType.ALIEN,playerSector,2,1);
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
		Sector sector2=new HatchSector(SectorType.SECURE,false,1,3,coordinate);
		for(int i=0;i<6;i++) {									//test constructor, class HatchCards
			hatchCard.add(listHatch.draw());
			assertTrue(hatchCard.get(i) instanceof HatchCard);				//test draw, class HatchCards
			assertTrue(hatchCard.get(i).getColor()==HatchCardColor.GREEN||hatchCard.get(i).getColor()==HatchCardColor.RED);		//test return color, class HatchCard
			if(hatchCard.get(i).getColor()==HatchCardColor.GREEN) green++;
			else red++;
		}
		assertTrue(red==3&& green==3);							//test number of each color, class HatchCards
		assertTrue(listHatch.getDeck().isEmpty());		//test number of card in deck, class HatchCards
		listHatch.discard(hatchCard.get(0));
		assertEquals(listHatch.getDiscardPile().get(0),hatchCard.get(0));	//test discardPile, class HatchCards
		assertTrue(listHatch.getDiscardPile().size()==1);			//test number of card in discard pile after discard a card, class HatchCards
	}
}
