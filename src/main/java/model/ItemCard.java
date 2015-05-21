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
	
	public ItemCardType getItemCardType() {
		return itemCardType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemCardType == null) ? 0 : itemCardType.hashCode());
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
		ItemCard other = (ItemCard) obj;
		if (itemCardType != other.itemCardType)
			return false;
		return true;
	}
	
	
}
