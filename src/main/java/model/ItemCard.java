package model;

/**
 * This class represent an ItemCard 
 * @author Nicola
 *@see ItemCardType
 */

public class ItemCard {
	
	private final ItemCardType itemCardType;
	
	/**
	 * To create a card is enough pass an itemCardType all the effect are in controller
	 * @param itemCardType
	 */
	
	public ItemCard(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}
	
	/**
	 * 
	 * @return itemCardType used by controller to execute the appropriate method
	 */
	
	public ItemCardType getType() {
		return itemCardType;
	}
	
	
	
	
}
