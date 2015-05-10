package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HatchCards {
	private int counter;
	private List<HatchCard> deckPile=new ArrayList<HatchCard>();
	private List<HatchCard> discardPile=new ArrayList<HatchCard>();
	public HatchCards(List<HatchCard> deckPile, List<HatchCard> discardPile) {
		this.counter=-1;
		this.deckPile=deckPile;
		this.discardPile=discardPile;
	}
	public HatchCard PickOut() {
		counter++;
		discardPile.set(counter, deckPile.get(counter));
		return deckPile.get(counter);
	}
}
