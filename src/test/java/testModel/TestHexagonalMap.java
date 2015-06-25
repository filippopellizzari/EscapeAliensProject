package testModel;

import static org.junit.Assert.*;

import java.io.IOException;

import model.*;

import org.junit.BeforeClass;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import creator.GameCreator;

/**
 * Test of functions of a generic hexagonal map (e.g Fermi)
 * 
 * @author Filippo
 *
 */
public class TestHexagonalMap {

	static Game fermi;
	static GameCreator gameCreator;

	@BeforeClass
	public static void onlyOnce() throws NumberFormatException, IOException {
		gameCreator = GameCreator.getinstance();
		fermi = gameCreator.createGame(MapName.Fermi, 3, MapType.HEXAGONAL);

	}

	/**
	 * test verifies the correspondence between a coordinate and a secto in the
	 * map
	 */
	@Test
	public void testGetSector() {
		Coordinate c = new Coordinate(12, 9);
		assertTrue(fermi.getMap().getSector(c)
				.equals(fermi.getMap().getSectors()[11 + 8 * 23]));
	}

	/**
	 * test verifies that a coordinate with positive values don't represent a
	 * sector in the map
	 */
	@Test
	public void testIsSectorNull() {
		Coordinate c = new Coordinate(13, 5);
		assertTrue(fermi.getMap().isNull(c));
	}
	/**
	 * test verifies that the coordinate with x<1
	 * is never in the map 
	 */
	@Test
	public void testWrongValueX(){
		Coordinate c = new Coordinate(-1, 2);
		assertTrue(fermi.getMap().isNull(c));
		
	}
	/**
	 * test verifies that the coordinate with y<1
	 * is never in the map 
	 */
	@Test
	public void testWrongValueY(){
		Coordinate c = new Coordinate(2, 0);
		assertTrue(fermi.getMap().isNull(c));
	}
	/**
	 * test verifies that a coordinate is not "null" if it represents
	 * a sector in the map
	 */
	@Test
	public void testIsSectorNotNull() {
		Coordinate c = new Coordinate(12, 5);
		assertTrue(!fermi.getMap().isNull(c));
	}
	/**
	 * test verifies that a coordinate is null, if x > 23
	 */
	@Test 
	public void testHighX(){
		Coordinate c = new Coordinate(25, 5);
		assertTrue(fermi.getMap().isNull(c));
	}
	/**
	 * test verifies that a coordinate is null, if y > 14
	 */
	@Test 
	public void testHighY(){
		Coordinate c = new Coordinate(12, 15);
		assertTrue(fermi.getMap().isNull(c));
	}
	
	

}
