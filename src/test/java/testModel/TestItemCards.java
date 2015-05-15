package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.ItemCard;
import model.ItemCards;
import model.TypeItemCard;

import org.junit.Test;

public class TestItemCards {

	@Test
	public void testDrawDiscard() {
		
		List<ItemCard> list = new ArrayList<ItemCard>();
		for(int i=0;i<2;i++) list.add(new ItemCard(TypeItemCard.ADRENALINE));
		list.add(new ItemCard(TypeItemCard.ATTACK));
		ItemCards h = new ItemCards(list);
		
		assertTrue(h.getDiscardPile().isEmpty());
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
			
		assertEquals(h.getDiscardPile().size() + list.size(), 3);
	}
	

}
