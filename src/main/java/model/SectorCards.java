package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Nicola
 * This class contains 2 decks, one is the former deck from which a player draw the card, when the action is complete the card is put in discard
 * pile, when deck is empty the discard pile is shuffle and all card are put into deck
 * @see SectorCardType
 */

public class SectorCards {
	
	private final List<SectorCard> deck;
	private final List<SectorCard> discardPile;
	
	/**
	 * 
	 * @param sectorCard called by creator to create the 2 deck, discard pile is void at the beginning
	 */

	public SectorCards(List<SectorCard> sectorCard) {

		this.deck = sectorCard;
		this.discardPile = new ArrayList<SectorCard>();

	}
	
	/**
	 * 
	 * @return a card, used by controller to do the correct action when a player go in a dangerous sector
	 */
	
	public SectorCard draw() {
		
		if (deck.isEmpty()) {
			deck.addAll(discardPile);
			discardPile.clear();
			Collections.shuffle(deck);
		}
		SectorCard current = deck.get(0);
		deck.remove(0);
		return current;
	}
	
	/**
	 * 
	 * @param current, add at the discard pile the card used after the action is completed
	 */
	
	public void discard(SectorCard current){
		discardPile.add(current);	
	}
	
	/**
	 * 
	 * @return	the deck, used only for test and future use
	 */

	public List<SectorCard> getDeck() {
		return deck;
	}
	
	/**
	 * 
	 * @return the discard pile, used for test 
	 */

	public List<SectorCard> getDiscardPile() {
		return discardPile;
	}
	
	
}
