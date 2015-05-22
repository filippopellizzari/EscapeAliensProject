package model;

/**
 * This class represent a Sector Card ,it has 2 attributes name that indicates the type of card and item which means that who draw this card must
 * draw an object card
 * @author Nicola
 * @see SectorCardType
 *
 */

public class SectorCard {
	
	private final  boolean itemIcon;
	private final SectorCardType sectorCardType;
	
	/**
	 * 
	 * @param itemIcon, true if this card allow to draw a item card
	 * @param name, typeOf sector card 3 types but only noise in your sector and noise in any sector can have an object card
	 */
	
	public SectorCard(boolean itemIcon, SectorCardType sectorCardType) {
		this.itemIcon = itemIcon;
		this.sectorCardType = sectorCardType;
	}
	
	/**
	 * 
	 * @return true if a player must draw an object, false otherwise
	 */
	
	public boolean isItemIcon() {
		return itemIcon;
	}
	
	/**
	 * 
	 * @return the Type of card sector used by controller to do the correct actions
	 * @see SectorCardType
	 */
	
	public SectorCardType getSectorCardType() {
		return sectorCardType;
	}
	
}
