package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * With this test i want verify if hatchcard deck is correct and is well draw and discard, i create the deck with the hatchcard
 * creator from CardCreator
 * @author Nicola
 *
 */

public class TestHatchCards {
	static CardsCreator cardCreator=new CardsCreator();
	static HatchCards listHatch=cardCreator.createHatchCards();
	static List<HatchCard> hatchCard=new ArrayList<HatchCard>();
	
	/**
	 * Draw 6 card from the deck and put in a new array to veriry this card
	 */
	
	@BeforeClass public static void onlyOnce() {
		for(int i=0;i<6;i++) {									//test constructor, class HatchCards
			hatchCard.add(listHatch.draw());
		}
	}
	
	/**
	 * Verify if all card draw are the correct type
	 */
	
	@Test
	public void testTypeOfCard() {
		for(int i=0;i<6;i++)
			assertTrue(hatchCard.get(i) instanceof HatchCard);				//test draw, class HatchCards
	}
	
	/**
	 * Verify if all card have the correct attributes (green or red, no other and exatly one of this)
	 */
	
	@Test
	public void testColorOfCard() {
		for(int i=0;i<6;i++)
			assertTrue(hatchCard.get(i).getColor()==HatchCardColor.GREEN||hatchCard.get(i).getColor()==HatchCardColor.RED);		//test return color, class HatchCard
	}
	
	/**
	 * Verify if the red card and green card are 3 for each type
	 */
	
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
	
	/**
	 * Verify if the deck is empty now (i have already draw 6 card)
	 */
	
	@Test
	public void deckisEmpty() {
		assertTrue(listHatch.getDeck().isEmpty());		//test number of card in deck, class HatchCards
	}
	
	/**
	 * I discard a card and verify if this is the same that i have discard
	 */
	
	@Test
	public void discard() {
		listHatch.discard(hatchCard.get(0));								//test number of card in discard pile after discard a card, class HatchCards
		assertTrue(listHatch.getDiscardPile().get(0)==hatchCard.get(0) && listHatch.getDiscardPile().size()==1);	//test discardPile, class HatchCards		
	}
}
