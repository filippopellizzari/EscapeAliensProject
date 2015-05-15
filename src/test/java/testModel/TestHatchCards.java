package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.HatchCard;
import model.HatchCards;

import org.junit.Test;

public class TestHatchCards {
	
	
	@Test
	public void testDrawDiscard() {
		
		List<HatchCard> list = new ArrayList<HatchCard>();
		for(int i=0;i<2;i++) list.add(new HatchCard(true));
		list.add(new HatchCard(false));
		HatchCards h = new HatchCards(list);
		
		assertTrue(h.getDiscardPile().isEmpty());
		assertEquals(list.size(),3);
		
		HatchCard c1 = h.draw();
		h.discard(c1);
		assertFalse(h.getDiscardPile().isEmpty());
		
		HatchCard c2 = h.draw();
		h.discard(c2);
		assertEquals(h.getDiscardPile().size(), 2);
		
		HatchCard c3 = h.draw();
		h.discard(c3);
		assertTrue(list.isEmpty());
		
		assertEquals(h.getDiscardPile().size() + list.size(), 3);
			
		
	}
	
		
	

}
