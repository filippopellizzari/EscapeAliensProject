package model;

public class SectorCard {
	
	private boolean itemIcon;
	private SectorCardType sectorCardType;
	
	public SectorCard(boolean itemIcon, SectorCardType name) {
		this.itemIcon=itemIcon;
		this.sectorCardType=name;
	}
	public boolean isItemIcon() {
		return itemIcon;
	}
	public SectorCardType getName() {
		return sectorCardType;
	}
}
