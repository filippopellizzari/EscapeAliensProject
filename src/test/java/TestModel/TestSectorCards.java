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
	public void testDraw() {
		
		List<SectorCard> list = new ArrayList<SectorCard>();
		for(int i=0;i<2;i++) list.add(new SectorCard(true,TypeSectorCard.NoiseAny));
		for(int i=0;i<2;i++) list.add(new SectorCard(false,TypeSectorCard.Silence));
		SectorCards h = new SectorCards(list);
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
		
		List<SectorCard> list = new ArrayList<SectorCard>();
		for(int i=0;i<2;i++) list.add(new SectorCard(true,TypeSectorCard.NoiseAny));
		for(int i=0;i<2;i++) list.add(new SectorCard(false,TypeSectorCard.Silence));
		SectorCards h = new SectorCards(list);
		SectorCard c1 = h.draw();
		h.discard(c1);
		assertFalse(h.getDiscardPile().isEmpty());
		SectorCard c2 = h.draw();
		h.discard(c2);
		assertEquals(h.getDiscardPile().size(), 2);
		
		
	}

}
