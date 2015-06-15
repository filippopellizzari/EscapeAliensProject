package testModel;

import static org.junit.Assert.*;

import java.io.IOException;

import model.*;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import creator.GameCreator;

/**
 * Test of functions of a sector, in a generic hexagonal map (e.g Galilei)
 * 
 * @author Filippo
 *
 */
public class TestSector {

	Game model;

	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);

	}

	/**
	 * test verifies that a human sector is closed (not crossable and not
	 * achievable)
	 */
	@Test
	public void testClosedHumanSector() {
		Coordinate humanCoord = model.getMap().getHumanCoord();
		Sector humanSector = model.getMap().getSector(humanCoord);
		assertTrue(humanSector.isClosed());
	}

	/**
	 * test verifies that an alien sector is closed (not crossable and not
	 * achievable)
	 */
	@Test
	public void testClosedAlienSector() {
		Coordinate alienCoord = model.getMap().getAlienCoord();
		Sector alienSector = model.getMap().getSector(alienCoord);
		assertTrue(alienSector.isClosed());
	}
	/** 
	 * test ToString
	 */
	@Test
	public void testToString(){
		Coordinate c = new Coordinate(4,3);
		Sector s = model.getMap().getSector(c);
		assertEquals(s.toString(), "Sector [sectorType=DANGEROUS, coordinate=(4,3)]");	
	}
}
