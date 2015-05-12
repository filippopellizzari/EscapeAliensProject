package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemCards {
	
	private List<ItemCard> deck;
	private List<ItemCard> discardPile;

	public ItemCards() {

		this.deck = new ArrayList<ItemCard>();
		this.discardPile = new ArrayList<ItemCard>();

	}
	
	public ItemCard draw() {
		
		if (deck.isEmpty()){
			deck.addAll(discardPile);
			discardPile.clear();
			Collections.shuffle(deck);

		 	}

			ItemCard current = deck.get(0);
			deck.remove(0);
			return current;			
			
	}
	
	public void discard(ItemCard current){
		discardPile.add(current);	
	}

	
}
