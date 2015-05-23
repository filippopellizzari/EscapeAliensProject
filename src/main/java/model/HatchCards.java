package model;

import java.util.ArrayList;

import java.util.List;

/**
 * This class contains 2 decks, one is used ad a common deck and you can draw a card the others is used as a discard
 * deck and take the cards that you have used
 * @author Nicola
 *@see HatchCard
 */

public class HatchCards {
	
	private final List<HatchCard> deck;
	private final List<HatchCard> discardPile;
	
	/**
	 * 
	 * @param hatchCard, is used to prepare the deck, discard pile is null at the beginning
	 * @see HatchCard to know how each card is composed
	 */

	public HatchCards(List<HatchCard> hatchCard) {
		this.deck = hatchCard;
		this.discardPile = new ArrayList<HatchCard>();
	}
	
	/**
	 * 
	 * @return one HatchCard, because there are only 4 sector in the map i don't have include any control, the deck 
	 * can't be void at any moment
	 */
	
	public HatchCard draw() {
			return deck.remove(0);					
	}
	
	/**
	 * 
	 * @param current add at the discard deck the card used for know if the hatchSector is close or not
	 */
	
	public void discard(HatchCard current){
		discardPile.add(current);	
	}

	/**
	 * @return the deck used only for test, this method don't has any use in the game
	 */
	public List<HatchCard> getDeck() {
		return deck;
	}

	/**
	 * @return the discardPile, used only for test, this method don't has any use in the game
	 */
	public List<HatchCard> getDiscardPile() {
		return discardPile;
	}
	
	
}
