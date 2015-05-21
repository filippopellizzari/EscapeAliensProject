package model;

/**
 * 
 * @author Nicola
 *
 */

public class ItemCard {
	
	private final ItemCardType itemCardType;
	
	/**
	 * 
	 * @param itemCardType
	 */
	
	public ItemCard(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public ItemCardType getName() {
		return itemCardType;
	}
}
