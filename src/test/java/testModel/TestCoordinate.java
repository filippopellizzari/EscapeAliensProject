package testModel;

import static org.junit.Assert.*;


import org.junit.Test;
import model.*;

public class TestCoordinate {

	
	

	@Test
	public void testGet() {
		Coordinate c = new Coordinate(3,5);
		assertEquals(c.getX(),3);
		assertNotEquals(c.getY(),3);
		assertEquals(c.getY(),5);
		
	}

	

}
