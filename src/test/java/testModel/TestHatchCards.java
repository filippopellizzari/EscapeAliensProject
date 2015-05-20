package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestHatchCards {
	static CardsCreator cardCreator=new CardsCreator();
	static HatchCards listHatch=cardCreator.createHatchCards();
	static List<HatchCard> hatchCard=new ArrayList<HatchCard>();
	
	@BeforeClass public static void onlyOnce() {
		for(int i=0;i<6;i++) {									//test constructor, class HatchCards
			hatchCard.add(listHatch.draw());
		}
	}
	
	@Test
	public void testTypeOfCard() {
		for(int i=0;i<6;i++)
			assertTrue(hatchCard.get(i) instanceof HatchCard);				//test draw, class HatchCards
	}
	
	@Test
	public void testColorOfCard() {
		for(int i=0;i<6;i++)
			assertTrue(hatchCard.get(i).getColor()==HatchCardColor.GREEN||hatchCard.get(i).getColor()==HatchCardColor.RED);		//test return color, class HatchCard
	}
	
	@Test
	public void testNumberOfCardDiscard() {
		int red=0;
		int green=0;
		for(int i=0;i<6;i++) {
			if(hatchCard.get(i).getColor()==HatchCardColor.GREEN) green++;
			else red++;
		}
		assertTrue(red==3&& green==3);							//test number of each color, class HatchCards
	}
	
	@Test
	public void deckisEmpty() {
		assertTrue(listHatch.getDeck().isEmpty());		//test number of card in deck, class HatchCards
	}
	
	@Test
	public void discard() {
		listHatch.discard(hatchCard.get(0));								//test number of card in discard pile after discard a card, class HatchCards
		assertTrue(listHatch.getDiscardPile().get(0)==hatchCard.get(0) && listHatch.getDiscardPile().size()==1);	//test discardPile, class HatchCards		
	}
}
