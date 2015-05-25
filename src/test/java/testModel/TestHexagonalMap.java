package testModel;

import static org.junit.Assert.*;
import model.*;



import org.junit.BeforeClass;
import org.junit.Test;

import creator.GameCreator;
/**
 * Test sulle funzionalità di una generica mappa a settori esagonali (es. Fermi)
 * @author filippopellizzari
 *
 */
public class TestHexagonalMap {

		
		static Game fermi;
		static GameCreator gameCreator;
		@BeforeClass public static void onlyOnce() {
			gameCreator=GameCreator.getinstance();
			fermi=gameCreator.createGame("Fermi", 3, "Hexagonal"); 
			
		}
	/**
	 * il test verifica che un settore presente sulla mappa 
	 * sia contenuto nell'array di settori della mappa
	 */
	@Test
	public void testGetSector() {
		Coordinate c = new Coordinate(12,9);
		assertTrue(fermi.getMap().getSector(c).equals(fermi.getMap().getSectors()[11+8*23]));
	}
	/**
	 * 	il test verifica che un settore non presente sulla mappa sia considerato "nullo"
	 */
	@Test
	public void testIsSectorNull() {	
		Coordinate c = new Coordinate(13, 5);
		assertTrue(fermi.getMap().isNull(c));
	}
	/**
	 * il test verifica che un settore presente sulla mappa non sia considerato "nullo"
	 */
	@Test
	public void testIsSectorNotNull() {	
		Coordinate c = new Coordinate(12, 5);
		assertTrue(!fermi.getMap().isNull(c));
	}
	
	
	
	
	
	
	

}
