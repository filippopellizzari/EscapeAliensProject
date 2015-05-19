package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.Test;

public class TestiItemCards {
	
	
	@Test
	public void testDrawDiscard() {
		CardsCreator cardCreator=new CardsCreator();
		ItemCards decksOfCards=cardCreator.createItemCards();
		List<ItemCard> listItemCards=new ArrayList<ItemCard>();
		int atk=0;
		int tele=0;
		int def=0;
		int sed=0;
		int adr=0;
		int lig=0;
		int j=0; //mazzo di scarto
		assertTrue(decksOfCards.getDeck().size()==12 && decksOfCards.getDiscardPile().size()==0);		//test dimension of deck and discard, class ItemCards
		for(int i=0;i<12;i++) {									//test constructor, class ItemCards
			listItemCards.add(decksOfCards.draw());
			assertTrue(listItemCards.get(i) instanceof ItemCard);				//test draw, class SectorCards
			assertTrue(listItemCards.get(i).getName()==ItemCardType.ADRENALINE || listItemCards.get(i).getName()==ItemCardType.ATTACK || listItemCards.get(i).getName()==ItemCardType.DEFENSE || listItemCards.get(i).getName()==ItemCardType.SEDATIVES || listItemCards.get(i).getName()==ItemCardType.SPOTLIGHT || listItemCards.get(i).getName()==ItemCardType.TELEPORT);		//test getName, class ItemCard
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
			if(i%2==0) {
				decksOfCards.discard(listItemCards.get(i));
				assertEquals(decksOfCards.getDiscardPile().get(j),listItemCards.get(i));	//test discardPile, class ItemCards
				j++;
			}
		}
		assertTrue(atk==2 && def==1 && tele==2 && sed==3 && lig==2 && adr==2); //test the tipe of card in deck, class ItemCards
		assertTrue(decksOfCards.getDiscardPile().size()==6 && decksOfCards.getDeck().size()==0);			//test number of card in discard pile and deck, class SectorCards
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
