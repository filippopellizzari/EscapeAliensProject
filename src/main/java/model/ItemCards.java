package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemCards {
	private int numberDeckPile;
	private int numberDiscardPile;
	private List<ItemCard> deckPile=new ArrayList<ItemCard>();
	private List<ItemCard> discardPile=new ArrayList<ItemCard>();
	public ItemCards(List<ItemCard> deckPile, List<ItemCard> discardPile) {
		this.numberDeckPile=12;
		this.numberDiscardPile=0;
		this.deckPile=deckPile;
		this.discardPile=discardPile;
	}
	public ItemCard PickOut() {
		if(numberDeckPile==0) return null;
		else {
			ItemCard cardToGive;
			cardToGive=deckPile.remove(12-numberDeckPile);
			numberDeckPile--;
			if(numberDeckPile==0) Shuffle();
			return cardToGive;
		}
	}
	private void Shuffle() {
		if(numberDiscardPile==0) return;
		Collections.shuffle(discardPile);
		deckPile=discardPile;
		numberDeckPile=numberDiscardPile;
		numberDiscardPile=0;
	}
	public void Discard(ItemCard cardToDiscard) {
		discardPile.set(numberDiscardPile, cardToDiscard);
		numberDiscardPile++;
	}
}
