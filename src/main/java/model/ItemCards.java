package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemCards {
	
	private final List<ItemCard> deck;
	private final List<ItemCard> discardPile;

	public ItemCards(List<ItemCard> itemcard) {

		this.deck = itemcard;
		this.discardPile = new ArrayList<ItemCard>();

	}
	
	public ItemCard draw() {
		
		if (deck.isEmpty()){
			deck.addAll(discardPile);
			discardPile.clear();
			Collections.shuffle(deck);

		}
		if(deck.isEmpty()==false) {					//if after shuffle the deck is empty return null without  this return error is deck is empty and you draw a card
			ItemCard current = deck.get(0);
			deck.remove(0);
			return current;
		}
		else 
			return null;
		
	}
	
	public void discard(ItemCard current){
		discardPile.add(current);	
	}

	public List<ItemCard> getDeck() {
		return deck;
	}

	public List<ItemCard> getDiscardPile() {
		return discardPile;
	}
	
	

	
	
}
