package model;

public class SectorCard {
	
	private boolean itemIcon;
	private TypeSectorCard name;
	
	public SectorCard(boolean itemIcon, TypeSectorCard name) {
		this.itemIcon=itemIcon;
		this.name=name;
	}
	public boolean isItemIcon() {
		return itemIcon;
	}
	public TypeSectorCard getName() {
		return name;
	}
}
