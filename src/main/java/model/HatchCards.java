package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HatchCards {
	private List<HatchCard> deck;
	private List<HatchCard> discardPile;

	public HatchCards(List<HatchCard> hatchCard) {
		this.deck = hatchCard;
		this.discardPile = new ArrayList<HatchCard>();

	}
	public HatchCard draw() {
		
		if (deck.isEmpty()){
			deck.addAll(discardPile);
			discardPile.clear();
			Collections.shuffle(deck);

		 	}

			HatchCard current = deck.get(0);
			deck.remove(0);
			return current;			
			
	}
	
	public void discard(HatchCard current){
		discardPile.add(current);	
	}
	
	
	
	public List<HatchCard> getDeck() {
		return deck;
	}
	
	public List<HatchCard> getDiscardPile() {
		return discardPile;
	}
	
	
	
}
