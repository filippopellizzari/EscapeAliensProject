package TestModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.ItemCard;
import model.ItemCards;
import model.TypeItemCard;

import org.junit.Test;

public class TestItemCards {

	
	@Test
	public void testDraw() {
		
		List<ItemCard> list = new ArrayList<ItemCard>();
		for(int i=0;i<2;i++) list.add(new ItemCard(TypeItemCard.Teleport));
		for(int i=0;i<2;i++) list.add(new ItemCard(TypeItemCard.Adrenaline));
		ItemCards h = new ItemCards(list);
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
		
		List<ItemCard> list = new ArrayList<ItemCard>();
		for(int i=0;i<2;i++) list.add(new ItemCard(TypeItemCard.Teleport));
		for(int i=0;i<2;i++) list.add(new ItemCard(TypeItemCard.Adrenaline));
		ItemCards h = new ItemCards(list);
		ItemCard c1 = h.draw();
		h.discard(c1);
		assertFalse(h.getDiscardPile().isEmpty());
		ItemCard c2 = h.draw();
		h.discard(c2);
		assertEquals(h.getDiscardPile().size(), 2);
		
		
	}

}
