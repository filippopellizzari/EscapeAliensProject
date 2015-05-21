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
	
	public SectorCard(boolean itemIcon, SectorCardType name) {
		this.itemIcon=itemIcon;
		this.sectorCardType=name;
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
