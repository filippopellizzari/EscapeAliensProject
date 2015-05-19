package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SectorCards {
	
	private final List<SectorCard> deck;
	private final List<SectorCard> discardPile;

	public SectorCards(List<SectorCard> sectorCard) {

		this.deck = sectorCard;
		this.discardPile = new ArrayList<SectorCard>();

	}
	
	public SectorCard draw() {
		
		if (deck.isEmpty()) {
			deck.addAll(discardPile);
			discardPile.clear();
			Collections.shuffle(deck);
		}
		if(deck.isEmpty()==false) {					//if after shuffle the deck is empty return null without  this return error is deck is empty and you draw a card
			SectorCard current = deck.get(0);
			deck.remove(0);
			return current;
		}
		return null;
	}
	
	public void discard(SectorCard current){
		discardPile.add(current);	
	}

	public List<SectorCard> getDeck() {
		return deck;
	}

	public List<SectorCard> getDiscardPile() {
		return discardPile;
	}
	
	
}
