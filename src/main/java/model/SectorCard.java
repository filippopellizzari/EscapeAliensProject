package model;

public class SectorCard {
	
	private final  boolean itemIcon;
	private final SectorCardType sectorCardType;
	
	public SectorCard(boolean itemIcon, SectorCardType name) {
		this.itemIcon=itemIcon;
		this.sectorCardType=name;
	}
	public boolean isItemIcon() {
		return itemIcon;
	}
	public SectorCardType getSectorCardType() {
		return sectorCardType;
	}
}
