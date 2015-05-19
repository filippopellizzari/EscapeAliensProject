package model;

public class ItemCard {
	
	private final ItemCardType itemCardType;
	
	public ItemCard(ItemCardType itemCardType) {
		this.itemCardType = itemCardType;
	}
	public ItemCardType getName() {
		return itemCardType;
	}
}
