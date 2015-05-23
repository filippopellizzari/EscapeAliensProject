package testModel;

import static org.junit.Assert.*;
import model.*;



import org.junit.BeforeClass;
import org.junit.Test;

import creator.GameCreator;

public class TestHexagonalMap {

		
		static Game fermi;
		static GameCreator gameCreator;
		@BeforeClass public static void onlyOnce() {
			gameCreator=GameCreator.getinstance();
			fermi=gameCreator.createGame("Fermi", 3, "Hexagonal"); 
			
		}
	
	
	@Test
	public void testGetSector() {
		Coordinate c = new Coordinate(12,9);
		assertTrue(fermi.getMap().getSector(c).equals(fermi.getMap().getSectors()[11+8*23]));
	}
		
	@Test
	public void testIsSectorNull() {	
		Coordinate c = new Coordinate(13, 5);
		assertTrue(fermi.getMap().isNull(c));
	}
	
	@Test
	public void testIsSectorNotNull() {	
		Coordinate c = new Coordinate(12, 5);
		assertTrue(!fermi.getMap().isNull(c));
	}
	
	
	
	
	
	
	

}
