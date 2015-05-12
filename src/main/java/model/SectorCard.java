package model;

public class SectorCard {
	
	private boolean itemCard;
	private TypeSector name;
	
	public SectorCard(boolean itemCard, TypeSector name) {
		this.itemCard=itemCard;
		this.name=name;
	}
	public boolean isItem() {
		return itemCard;
	}
	public TypeSector getName() {
		return name;
	}
}
