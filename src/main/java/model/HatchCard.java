package model;

/**
 * This class simulate one hatchCard, all of this cards are in a deck 
 * 
 * @see HatchCards
 * @author Nicola
 *
 */

public class HatchCard {
	
	private final HatchCardColor color;
	
	/**
	 * 
	 * @param color use for create the card, it is the only attribute of this class
	 */
	
	public HatchCard(HatchCardColor color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return return the color of the card this can be red or green
	 * @see HatchCardColor
	 */

	public HatchCardColor getColor() {
		return color;
	}


	
}
