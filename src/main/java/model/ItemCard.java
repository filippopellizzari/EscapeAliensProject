package model;

public class ItemCard {
	private TypeItemCard type;
	public ItemCard(TypeItemCard typeItemCard) {
		this.type=typeItemCard;
	}
	public TypeItemCard getName() {
		return type;
	}
}
