package model;

/**
 * This class represent a ItemCard 
 * @author Nicola
 *@see ItemCardType
 */

public class ItemCard {
	
	private final ItemCardType itemCardType;
	
	/**
	 * To create a card is enought pass a itemCardType all the effect are in controller
	 * @param itemCardType
	 */
	
	public ItemCard(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}
	
	/**
	 * 
	 * @return itemCardType used by controller to execute the appropriate method
	 */
	
	public ItemCardType getName() {
		return itemCardType;
	}
}
