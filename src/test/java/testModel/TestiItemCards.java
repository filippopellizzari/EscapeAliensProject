package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *With this test i want verify if itemcard deck is correct and is well draw and discard, i create the deck with the
 * ItemCardcreator from CardCreator
 * @author Nicola
 *
 */

public class TestiItemCards {
	static CardsCreator cardCreator=new CardsCreator();
	static ItemCards decksOfCards=cardCreator.createItemCards();
	static List<ItemCard> listItemCards=new ArrayList<ItemCard>();
	
	/**
	 * I create the deck and draw 12 card, 6 of them are put in the discard pile
	 */
	
	@BeforeClass public static void onlyOnce() {
		for(int i=0;i<12;i++) {									//test constructor, class ItemCards
			listItemCards.add(decksOfCards.draw());
			if(i%2==0)
				decksOfCards.discard(listItemCards.get(i));
		}
	}
	
	/**
	 * i verify if the deck is empty and the discard pile contains exactly 6 card
	 */
	
	@Test
	public void dimensionDeck() {
		assertTrue(decksOfCards.getDeck().size()==0 && decksOfCards.getDiscardPile().size()==6);		//test dimension of deck and discard, class ItemCards
	}
	
	/**
	 * test if the discard pile has the same card that i had discard
	 */
	
	@Test
	public void deckisEmpty() {
		for(int i=0;i<6;i++)
			assertEquals(decksOfCards.getDiscardPile().get(i),listItemCards.get(i*2));	//test discardPile, class ItemCards
	}
	
	/**
	 * verify the correct number of item in the deck
	 */
	
	@Test
	public void testCorrectNumberOfCard() {
		int atk=0;
		int tele=0;
		int def=0;
		int sed=0;
		int adr=0;
		int lig=0;
		for(int i=0;i<12;i++) {
			switch(listItemCards.get(i).getType()){								//test SectorCardType enumeration, 
				case ATTACK: atk++;
				break;
				case ADRENALINE: adr++;
				break;
				case DEFENSE : def++;
				break;
				case TELEPORT : tele++;
				break;
				case SEDATIVES : sed++;
				break;
				case SPOTLIGHT : lig++;
				break;
			}
		}
		assertTrue(atk==2 && def==1 && tele==2 && sed==3 && lig==2 && adr==2); //test the tipe of card in deck, class ItemCards
	}
	
	/**
	 * i verify if the card are the correct type
	 */
	
	@Test
	public void cardAreTheTypeItemCard() {
		for(int i=0;i<12;i++)									//test constructor, class ItemCards
			assertTrue(listItemCards.get(i) instanceof ItemCard);	
	}
	
	/**
	 * I verify that there aren't any others type of card in the deck
	 */
	
	@Test
	public void thereAreNoCardDifferentFromThisType() {
		for(int i=0;i<12;i++)
			assertTrue(listItemCards.get(i).getType()==ItemCardType.ADRENALINE || listItemCards.get(i).getType()==ItemCardType.ATTACK || listItemCards.get(i).getType()==ItemCardType.DEFENSE || listItemCards.get(i).getType()==ItemCardType.SEDATIVES || listItemCards.get(i).getType()==ItemCardType.SPOTLIGHT || listItemCards.get(i).getType()==ItemCardType.TELEPORT);		//test getItemCardType, class ItemCard
	}
	
	/**
	 * I verify, after draw 7 card than the first 6 are correct and the seven is null before both discard pile and deck are empty after draw 6 card
	 */
	
	@Test
	public void drawMoreCard() {
		List<ItemCard> listItemCards2=new ArrayList<ItemCard>();	//remove the card
		ItemCards decksOfCards2 = cardCreator.createItemCards();
		for(int i=0;i<12;i++) {
			if(i%2==0)
				decksOfCards2.discard(decksOfCards2.draw());
			else decksOfCards2.draw();
		}
		int contatore=0;
		for(int i=0;i<7;i++)
		{
			listItemCards2.add(decksOfCards2.draw());
			if(listItemCards2.get(i)!=null) contatore++;
		}
		assertTrue(decksOfCards2.getDeck().size()==0 && decksOfCards2.getDiscardPile().size()==0 && contatore==6 && listItemCards2.get(6)==null);	//test draw when deck is empty, after the operation deck should be 24 card, discard 0 and the card draw is SectorCard, class SectorCards
	}
}
