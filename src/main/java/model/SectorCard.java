package model;

/**
 * 
 * @author Nicola
 *
 */

public class SectorCard {
	
	private final  boolean itemIcon;
	private final SectorCardType sectorCardType;
	
	/**
	 * 
	 * @param itemIcon
	 * @param name
	 */
	
	public SectorCard(boolean itemIcon, SectorCardType sectorCardType) {
		this.itemIcon = itemIcon;
		this.sectorCardType = sectorCardType;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public boolean isItemIcon() {
		return itemIcon;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public SectorCardType getSectorCardType() {
		return sectorCardType;
	}
	
}
