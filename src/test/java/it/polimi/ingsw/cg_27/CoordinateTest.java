package it.polimi.ingsw.cg_27;

import static org.junit.Assert.*;
import model.Coordinate;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void test() {
		
		Coordinate c = new Coordinate(2,3);
		assertEquals(2,c.getX(),0);
		
	}

}
