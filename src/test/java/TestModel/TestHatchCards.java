package TestModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.HatchCard;
import model.HatchCards;

import org.junit.Test;

public class TestHatchCards {
	
	
	@Test
	public void testDraw() {
		
		List<HatchCard> list = new ArrayList<HatchCard>();
		for(int i=0;i<2;i++) list.add(new HatchCard(true));
		for(int i=0;i<2;i++) list.add(new HatchCard(false));
		HatchCards h = new HatchCards(list);
		assertFalse(list.isEmpty());
		assertEquals(list.size(),4);
		for(int i=0;i<3;i++) h.draw();
		assertEquals(list.size(),1);
		assertFalse(list.isEmpty());
		h.draw();
		assertEquals(list.size(),0);
		assertTrue(list.isEmpty());
	
	}

	@Test
	public void testDiscard() {
		
		List<HatchCard> list = new ArrayList<HatchCard>();
		for(int i=0;i<2;i++) list.add(new HatchCard(true));
		for(int i=0;i<2;i++) list.add(new HatchCard(false));
		HatchCards h = new HatchCards(list);
		HatchCard c1 = h.draw();
		h.discard(c1);
		assertFalse(h.getDiscardPile().isEmpty());
		HatchCard c2 = h.draw();
		h.discard(c2);
		assertEquals(h.getDiscardPile().size(), 2);
		
		
	}

}
