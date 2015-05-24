package testModel;

import static org.junit.Assert.*;
import model.*;

import org.junit.BeforeClass;
import org.junit.Test;

import creator.GameCreator;

/**
 * Test sulle funzionalità di un settore di una generica mappa a settori esagonali (es. Galilei)
 * @author Filippo
 *
 */
public class TestSector {

	static Game model;
	
	@BeforeClass public static void onlyOnce() {
		model = GameCreator.getinstance().createGame("Galilei", 8, "Exagonal");	
		
	}
	
	/**
	 * il test verifica che il settore umano (partenza) sia "chiuso",
	 *  ossia non attraversabile e non raggiungibile
	 */
	@Test
	public void testClosedHumanSector() {
		Coordinate humanCoord = model.getMap().getHumanCoord();
		Sector humanSector = model.getMap().getSector(humanCoord);
		assertTrue(humanSector.isClosed());
	}
	
	/**
	 * il test verifica che il settore alieno (partenza) sia "chiuso",
	 *  ossia non attraversabile e non raggiungibile
	 */
	@Test
	public void testClosedAlienSector() {
		Coordinate alienCoord = model.getMap().getAlienCoord();
		Sector alienSector = model.getMap().getSector(alienCoord);
		assertTrue(alienSector.isClosed());
	}


}
