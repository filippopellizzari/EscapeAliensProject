package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represent the 2 deck relative at itemcard, everyone draw a card from the deck and discard in the discardPile
 * @author Nicola
 * @see ItemCard
 *
 */

public class ItemCards {
	
	private final List<ItemCard> deck;
	private final List<ItemCard> discardPile;

	/**
	 * the 2 deck are composed by itemcard, deck has at beginning 12 card, discardPile is void
	 * @param itemcard
	 */
	
	public ItemCards(List<ItemCard> itemcard) {

		this.deck = itemcard;
		this.discardPile = new ArrayList<ItemCard>();

	}
	
	/**
	 * 
	 * @return if the deck is empty so all the cards in discardPile are shuffle and the put into deck, then a card is passed, if discardPile is
	 * void return null
	 */
	
	public ItemCard draw() {
		
		if (deck.isEmpty()){
			if (discardPile.isEmpty()){
				return null;            //può capitare che non ci siano carte scartate
			}
			deck.addAll(discardPile);
			discardPile.clear();
			Collections.shuffle(deck);
		}
						 
		return deck.remove(0);
		
	}
	
	/**
	 * 
	 * @param current, discard a card and this card is added at the discard Pile
	 */
	
	public void discard(ItemCard current){
		discardPile.add(current);	
	}
	
	/**
	 * 
	 * @return deck, this method is used only for test and it isn't used in the game
	 */

	public List<ItemCard> getDeck() {
		return deck;
	}
	
	/**
	 * 
	 * @return discard, this method is used only for test and it isn't used in the game
	 */

	public List<ItemCard> getDiscardPile() {
		return discardPile;
	}	
}
