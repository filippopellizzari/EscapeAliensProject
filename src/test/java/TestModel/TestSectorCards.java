package TestModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import model.SectorCard;
import model.SectorCards;
import model.TypeSectorCard;

import org.junit.Test;

public class TestSectorCards {

	@Test
	public void testDrawDiscard() {
		
		List<SectorCard> list = new ArrayList<SectorCard>();
		for(int i=0;i<2;i++) list.add(new SectorCard(true,TypeSectorCard.NoiseAny));
		list.add(new SectorCard(false,TypeSectorCard.Silence));
		SectorCards h = new SectorCards(list);
		
		assertTrue(h.getDiscardPile().isEmpty());
		assertEquals(list.size(),3);
		
		SectorCard c1 = h.draw();
		h.discard(c1);
		assertFalse(h.getDiscardPile().isEmpty());
		
		SectorCard c2 = h.draw();
		h.discard(c2);
		assertEquals(h.getDiscardPile().size(), 2);
		
		SectorCard c3 = h.draw();
		h.discard(c3);
		assertTrue(list.isEmpty());
		
		SectorCard c4 = h.draw();
		assertTrue(h.getDiscardPile().isEmpty());
		h.discard(c4);
		assertEquals(h.getDiscardPile().size(),1);
			
		
	}

}
