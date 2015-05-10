package model;

public class SectorCard {
	private boolean itemCard;
	private TypeSector name;
	public SectorCard(boolean itemCard, TypeSector typeSector) {
		this.itemCard=itemCard;
		this.name=typeSector;
	}
	public boolean isItem() {
		return itemCard;
	}
	public TypeSector getName() {
		return name;
	}
}
