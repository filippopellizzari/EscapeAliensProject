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

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HatchCard other = (HatchCard) obj;
		if (color != other.color)
			return false;
		return true;
	}
	
	
	
	
}
