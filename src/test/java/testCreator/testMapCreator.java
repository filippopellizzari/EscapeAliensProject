package testCreator;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import creator.*;
import model.*;


public class testMapCreator {

	@Test
	public void testGalilei() throws NumberFormatException, IOException{
		MapCreator mc = new LoadHexagonalMap();
		Map	m = mc.loadMap("Galilei");
		
		
		assertEquals(m.getAlienCoord().getX(),12);
		assertEquals(m.getAlienCoord().getY(),6);
		assertEquals(m.getAlienCoord().getX(),12);
		assertEquals(m.getAlienCoord().getY(),8);
		assertEquals(m.getHatchSectors().size(),4); 
		
		Coordinate c1 = new Coordinate (2,7);
		Coordinate c2 = new Coordinate (24,7);
		Coordinate c3 = new Coordinate (10,4);
		Coordinate c4 = new Coordinate (4,8);
		assertTrue(m.isNull(c1));
		assertTrue(m.isNull(c2));
		assertFalse(m.isNull(c3));
		assertFalse(m.isNull(c4));
		
		Coordinate h1 = new Coordinate (2,2);
		Coordinate h2 = new Coordinate (2,13);
		Coordinate h3 = new Coordinate (22,2);
		Coordinate h4 = new Coordinate (22,13);
		assertEquals(m.getSector(h1).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h2).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h3).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h4).getType(), SectorType.HATCH);
		
		assertEquals(m.getSector(c3).getAdjacent().size(), 6);
		assertEquals(m.getSector(c4).getAdjacent().get(0).getX(), 3);
		assertEquals(m.getSector(c3).getAdjacent().get(3).getY(), 5);
		
	}
	
	@Test
	public void testFermi() throws NumberFormatException, IOException{
		MapCreator mc = new LoadHexagonalMap();
		Map	m = mc.loadMap("Fermi");
	
		
		assertEquals(m.getHumanCoord().getX(),12);
		assertEquals(m.getHumanCoord().getY(),9);
		assertEquals(m.getHumanCoord().getX(),12);
		assertEquals(m.getHumanCoord().getY(),10);
		assertEquals(m.getHatchSectors().size(),4);
		
		Coordinate h1 = new Coordinate (10,5);
		Coordinate h2 = new Coordinate (14,5);
		Coordinate h3 = new Coordinate (10,1);
		Coordinate h4 = new Coordinate (14,1);
		assertEquals(m.getSector(h1).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h2).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h3).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h4).getType(), SectorType.HATCH);
		
		
	}

	@Test
	public void testGalvani() throws NumberFormatException, IOException{
		MapCreator mc = new LoadHexagonalMap();
		Map	m = mc.loadMap("Galvani");
		
		
		assertEquals(m.getAlienCoord().getX(),12);
		assertEquals(m.getAlienCoord().getY(),6);
		assertEquals(m.getAlienCoord().getX(),12);
		assertEquals(m.getAlienCoord().getY(),8);
		assertEquals(m.getHatchSectors().size(),4);
		
		Coordinate h1 = new Coordinate (6,1);
		Coordinate h2 = new Coordinate (16,1);
		Coordinate h3 = new Coordinate (22,11);
		Coordinate h4 = new Coordinate (2,10);
		assertEquals(m.getSector(h1).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h2).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h3).getType(), SectorType.HATCH);
		assertEquals(m.getSector(h4).getType(), SectorType.HATCH);
	}

}
