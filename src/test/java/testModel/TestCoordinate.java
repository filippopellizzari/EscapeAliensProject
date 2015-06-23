package testModel;

import static org.junit.Assert.*;
import model.Coordinate;
import model.ItemCard;
import model.ItemCardType;


import org.junit.Test;
/**
 * test of the class Coordinate
 * (toString, equals, hashcode);
 * 
 * @author Filippo
 *
 */
public class TestCoordinate {
	/**
	 * test verifies that a null object
	 * is not equals to a coordinate
	 */
	@Test
	public void testEqualsNull() {
		Coordinate c = new Coordinate(2,3);
		Object o = null;
		assertFalse(c.equals(o));
	}
	
	/**
	 * test verifies that two different objects are not
	 * equals
	 */
	@Test
	public void testClass() {
		Coordinate c = new Coordinate(2,3);
		Object o = new ItemCard(ItemCardType.ADRENALINE);
		assertFalse(c.equals(o));
	}
	/**
	 * test verifies that a coordinate is equals to itself
	 */
	@Test 
	public void testEquals(){
		Coordinate c = new Coordinate(2,3);
		assertTrue(c.equals(c));
	}
	/**
	 * test verifies correct toString
	 */
	@Test
	public void testToString(){
		Coordinate c = new Coordinate(12,8);
		assertEquals(c.toString()," [L,8]");
	}
	/**
	 * test verifies correct hashcode
	 */
	@Test
	public void testHashCode(){
		int h = new Coordinate(2,3).hashCode();
		assertEquals(h,1026);
	}

	
	
	
}
