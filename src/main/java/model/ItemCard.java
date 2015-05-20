package model;

public class ItemCard {
	
	private final ItemCardType itemCardType;
	
	public ItemCard(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}
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
