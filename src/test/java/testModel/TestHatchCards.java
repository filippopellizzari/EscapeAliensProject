package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.Test;

public class TestHatchCards {
	
	
	@Test
	public void testDrawDiscard() {
		CardsCreator cardCreator=new CardsCreator();
		HatchCards listHatch=cardCreator.createHatchCards();
		List<HatchCard> hatchCard=new ArrayList<HatchCard>();
		int green=0;
		int red=0;
		for(int i=0;i<6;i++) {									//test constructor, class HatchCards
			hatchCard.add(listHatch.draw());
			assertTrue(hatchCard.get(i) instanceof HatchCard);				//test draw, class HatchCards
			assertTrue(hatchCard.get(i).getColor()==HatchCardColor.GREEN||hatchCard.get(i).getColor()==HatchCardColor.RED);		//test return color, class HatchCard
			if(hatchCard.get(i).getColor()==HatchCardColor.GREEN) green++;
			else red++;
		}
		assertTrue(red==3&& green==3);							//test number of each color, class HatchCards
		assertTrue(listHatch.getDeck().isEmpty());		//test number of card in deck, class HatchCards
		listHatch.discard(hatchCard.get(0));
		assertEquals(listHatch.getDiscardPile().get(0),hatchCard.get(0));	//test discardPile, class HatchCards
		assertTrue(listHatch.getDiscardPile().size()==1);			//test number of card in discard pile after discard a card, class HatchCards
	}
}
