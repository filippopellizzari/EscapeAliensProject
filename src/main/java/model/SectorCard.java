package model;

public class SectorCard {
	
	private boolean itemCard;
	private TypeSectorCard name;
	
	public SectorCard(boolean itemCard, TypeSectorCard name) {
		this.itemCard=itemCard;
		this.name=name;
	}
	public boolean isItem() {
		return itemCard;
	}
	public TypeSectorCard getName() {
		return name;
	}
}
