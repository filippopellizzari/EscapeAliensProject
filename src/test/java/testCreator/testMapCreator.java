package testCreator;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import creator.*;
import model.*;


public class testMapCreator {

	@Test
	public void testGalilei(){
		MapCreator mc = new MapCreator();
		Map m = null;
		try {
			m = mc.createMap("Galilei");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(m.getAlienSector().getX(),12);
		assertEquals(m.getAlienSector().getY(),6);
		assertEquals(m.getHumanSector().getX(),12);
		assertEquals(m.getHumanSector().getY(),8);
		assertEquals(m.getHatchSectors().size(),4); 
		
		
	}
	
	@Test
	public void testFermi(){
		MapCreator mc = new MapCreator();
		Map m = null;
		try {
			m = mc.createMap("Fermi");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(m.getAlienSector().getX(),12);
		assertEquals(m.getAlienSector().getY(),9);
		assertEquals(m.getHumanSector().getX(),12);
		assertEquals(m.getHumanSector().getY(),10);
		assertEquals(m.getHatchSectors().size(),4);
		
	}

}
