package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SectorCards {
	private int counter;
	private List<SectorCard> deckPile=new ArrayList<SectorCard>();
	private List<SectorCard> discardPile=new ArrayList<SectorCard>();
	public SectorCards(List<SectorCard> deckPile, List<SectorCard> discardPile) {
		this.counter=-1;
		this.deckPile=deckPile;
		this.discardPile=discardPile;
	}
	public SectorCard PickOut() {
		counter++;
		if(counter==26) Shuffle();
		discardPile.set(counter, deckPile.get(counter));
		return deckPile.get(counter);
	}
	private void Shuffle() {
		Collections.shuffle(discardPile);
		deckPile=discardPile;
	}
}
