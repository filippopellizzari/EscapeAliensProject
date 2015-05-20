package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestiItemCards {
	static CardsCreator cardCreator=new CardsCreator();
	static ItemCards decksOfCards=cardCreator.createItemCards();
	static List<ItemCard> listItemCards=new ArrayList<ItemCard>();
	
	@BeforeClass public static void onlyOnce() {
		for(int i=0;i<12;i++) {									//test constructor, class ItemCards
			listItemCards.add(decksOfCards.draw());
			if(i%2==0)
				decksOfCards.discard(listItemCards.get(i));
		}
	}
	@Test
	public void dimensionDeck() {
		assertTrue(decksOfCards.getDeck().size()==0 && decksOfCards.getDiscardPile().size()==6);		//test dimension of deck and discard, class ItemCards
	}
	
	@Test
	public void deckisEmpty() {
		for(int i=0;i<6;i++)
			assertEquals(decksOfCards.getDiscardPile().get(i),listItemCards.get(i*2));	//test discardPile, class ItemCards
	}
	
	@Test
	public void testCorrectNumberOfCard() {
		int atk=0;
		int tele=0;
		int def=0;
		int sed=0;
		int adr=0;
		int lig=0;
		for(int i=0;i<12;i++) {
			switch(listItemCards.get(i).getName()){								//test SectorCardType enumeration, 
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
	
	@Test
	public void cardAreTheTypeItemCard() {
		for(int i=0;i<12;i++)									//test constructor, class ItemCards
			assertTrue(listItemCards.get(i) instanceof ItemCard);	
	}
	
	@Test
	public void thereAreNoCardDifferentFromThisType() {
		for(int i=0;i<12;i++)
			assertTrue(listItemCards.get(i).getName()==ItemCardType.ADRENALINE || listItemCards.get(i).getName()==ItemCardType.ATTACK || listItemCards.get(i).getName()==ItemCardType.DEFENSE || listItemCards.get(i).getName()==ItemCardType.SEDATIVES || listItemCards.get(i).getName()==ItemCardType.SPOTLIGHT || listItemCards.get(i).getName()==ItemCardType.TELEPORT);		//test getName, class ItemCard
	}
	
	@Test
	public void drawMoreCard() {
		listItemCards=new ArrayList<ItemCard>();	//remove the card
		int contatore=0;
		for(int i=0;i<7;i++)
		{
			listItemCards.add(decksOfCards.draw());
			if(listItemCards.get(i)!=null) contatore++;
		}
		assertTrue(decksOfCards.getDeck().size()==0 && decksOfCards.getDiscardPile().size()==0 && contatore==6 && listItemCards.get(6)==null);	//test draw when deck is empty, after the operation deck should be 24 card, discard 0 and the card draw is SectorCard, class SectorCards
	}
}
