package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import model.SectorType;
import model.Coordinate;
import org.junit.Test;
public class TestSector {
	public void testInizializeSector() {
		List<Coordinate> coordinate=new ArrayList<Coordinate>();
		for(int i=0;i<6;i++) {
			coordinate.add(new Coordinate(i,i+1));
		}
		assertTrue(coordinate.get(0).getX()==0 && coordinate.get(0).getY()==1);
		assertTrue(coordinate.get(1).getX()==1 && coordinate.get(0).getY()==2);
		assertTrue(coordinate.get(2).getX()==2 && coordinate.get(0).getY()==3);
		assertTrue(coordinate.get(3).getX()==3 && coordinate.get(0).getY()==4);
		assertTrue(coordinate.get(4).getX()==4 && coordinate.get(0).getY()==5);
		assertTrue(coordinate.get(5).getX()==5 && coordinate.get(0).getY()==6);
		boolean open=true;
		SectorType sectort= SectorType.ALIEN;
		Sector sector=new Sector(sectort , open, 10, 10, coordinate);
		assertTrue(sector.getSectorType()==SectorType.ALIEN);
		assertTrue(sector.getX()==10);
		assertTrue(sector.getY()==10);
		assertTrue(sector.isOpen()==true);
	}
}
	/*assertTrue(h.getDiscardPile().isEmpty());
	assertEquals(list.size(),3);
	
	ItemCard c1 = h.draw();
	h.discard(c1);
	assertFalse(h.getDiscardPile().isEmpty());
	
	ItemCard c2 = h.draw();
	h.discard(c2);
	assertEquals(h.getDiscardPile().size(), 2);
	
	ItemCard c3 = h.draw();
	h.discard(c3);
	assertTrue(list.isEmpty());
	
	ItemCard c4 = h.draw();
	assertTrue(h.getDiscardPile().isEmpty());
	h.discard(c4);
	assertEquals(h.getDiscardPile().size(),1);
		
	assertEquals(h.getDiscardPile().size() + list.size(), 3);*/
