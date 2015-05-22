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
		
		return deck.remove(0);
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
