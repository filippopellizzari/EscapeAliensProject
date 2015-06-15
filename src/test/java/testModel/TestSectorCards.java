package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * With this test i want verify if sectorard deck is correct and is well draw and discard, i create the deck with the
 * SectorCardcreator from CardCreator
 * @author Nicola
 *
 */

public class TestSectorCards {
	static CardsCreator cardsCreator = new CardsCreator();
	static SectorCards sectorCards = cardsCreator.createSectorCards();
	static List<SectorCard> listSectorCards = new ArrayList<SectorCard>();
	
	/**
	 * i draw 25 card and put these in the discard pile
	 */
	
	@BeforeClass public static void onlyOnce() {
		for(int i=0;i<25;i++) {									//test constructor, class SectorCards
			listSectorCards.add(sectorCards.draw());
			sectorCards.discard(listSectorCards.get(i));
		}
	}
	
	/**
	 * I test that the deck is empty and the discard pile is full (25 cards)
	 */
	
	@Test
	public void numberOfCardInDeckAndDiscard() {
		assertTrue(sectorCards.getDeck().size()==0 && sectorCards.getDiscardPile().size()==25);		//test dimension of deck and discard, class SectorCards
	}
	
	/**
	 * I verify the correct type of sector card
	 */
	
	@Test
	public void cardOfCorrectType() {
		for(int i=0;i<25;i++) 
			assertTrue(listSectorCards.get(i) instanceof SectorCard);				//test draw, class SectorCards
	}
	
	/**
	 * I test that there aren't any other type of card different from the pre-defined card
	 */
	
	@Test
	public void testOfCorrectTypeOfCard() {
		for(int i=0;i<25;i++)
			assertTrue(listSectorCards.get(i).getType()==SectorCardType.NOISEANY|| listSectorCards.get(i).getType()==SectorCardType.NOISEYOUR || listSectorCards.get(i).getType()==SectorCardType.SILENCE);		//test getName, class SectorCard
	}
	
	/**
	 * I verify the correct number of cards in the deck
	 */
	
	@Test
	public void testNumberAndTypeOfCard() {
		
		int noiseInYourType0=0;
		int noiseInYourType1=0;
		int noiseInAnyType0=0;
		int noiseInAnyType1=0;
		int silence=0;
		for(int i=0;i<25;i++) {									//test constructor, class SectorCards
			switch(listSectorCards.get(i).getType()){								//test SectorCardType enumeration, 
				case NOISEYOUR:
					if(listSectorCards.get(i).isItemIcon()==true) noiseInYourType1++; //use the method isItem, class SectorCard
					else noiseInYourType0++;
				break;
				case NOISEANY:
					if(listSectorCards.get(i).isItemIcon()==true) noiseInAnyType1++;
					else noiseInAnyType0++;
				break;
				case SILENCE: silence++;
				break;
			}
			assertEquals(sectorCards.getDiscardPile().get(i),listSectorCards.get(i));	//test discardPile, class SectorCards
		}
		assertTrue(noiseInAnyType0==6 && noiseInAnyType1==4 && noiseInYourType0==6 && noiseInYourType1==4 && silence==5); //test the tipe of card in deck, class SectorCards
		
	}
	/**
	 * test verifies that, when a deck is empty and a player 
	 * wants to draw a card, cards in the DiscardPile
	 * are shuffled and put in the deck; discardPile remains empty.
	 */
	@Test
	public void testDeckEmpty(){
		List<SectorCard> sectorCards = new ArrayList<SectorCard>();
		SectorCards sc = new SectorCards(sectorCards);
		sc.getDiscardPile().add(new SectorCard(true, SectorCardType.NOISEANY));
		sc.getDiscardPile().add(new SectorCard(true, SectorCardType.NOISEYOUR));
		sc.getDiscardPile().add(new SectorCard(true, SectorCardType.SILENCE));
		sc.getDiscardPile().add(new SectorCard(true, SectorCardType.NOISEYOUR));

		sc.draw();
		
		assertTrue(sc.getDiscardPile().isEmpty());
	}
	
	
	
	
}
