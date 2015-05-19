package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.Test;

public class TestSectorCards {
	
	
	@Test
	public void testDrawDiscard() {
		CardsCreator cardCreator=new CardsCreator();
		SectorCards decksOfCards=cardCreator.createSectorCards();
		List<SectorCard> listSectorCards=new ArrayList<SectorCard>();
		int noiseInYourType0=0;
		int noiseInYourType1=0;
		int noiseInAnyType0=0;
		int noiseInAnyType1=0;
		int silence=0;
		assertTrue(decksOfCards.getDeck().size()==25 && decksOfCards.getDiscardPile().size()==0);		//test dimension of deck and discard, class SectorCards
		for(int i=0;i<25;i++) {									//test constructor, class SectorCards
			listSectorCards.add(decksOfCards.draw());
			assertTrue(listSectorCards.get(i) instanceof SectorCard);				//test draw, class SectorCards
			assertTrue(listSectorCards.get(i).getName()==SectorCardType.NOISEANY|| listSectorCards.get(i).getName()==SectorCardType.NOISEYOUR || listSectorCards.get(i).getName()==SectorCardType.SILENCE);		//test getName, class SectorCard
			switch(listSectorCards.get(i).getName()){								//test SectorCardType enumeration, 
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
			decksOfCards.discard(listSectorCards.get(i));
			assertEquals(decksOfCards.getDiscardPile().get(i),listSectorCards.get(i));	//test discardPile, class SectorCards
		}
		assertTrue(noiseInAnyType0==6 && noiseInAnyType1==4 && noiseInYourType0==6 && noiseInYourType1==4 && silence==5); //test the tipe of card in deck, class SectorCards
		assertTrue(decksOfCards.getDiscardPile().size()==25 && decksOfCards.getDeck().size()==0);			//test number of card in discard pile and deck, class SectorCards
		listSectorCards=new ArrayList<SectorCard>();	//remove the card
		listSectorCards.add(decksOfCards.draw());
		assertTrue(decksOfCards.getDeck().size()==24 && decksOfCards.getDiscardPile().size()==0 && listSectorCards.get(0) instanceof SectorCard);	//test draw when deck is empty, after the operation deck should be 24 card, discard 0 and the card draw is SectorCard, class SectorCards
	}
}