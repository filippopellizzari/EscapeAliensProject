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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (itemIcon ? 1231 : 1237);
		result = prime * result
				+ ((sectorCardType == null) ? 0 : sectorCardType.hashCode());
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
		SectorCard other = (SectorCard) obj;
		if (itemIcon != other.itemIcon)
			return false;
		if (sectorCardType != other.sectorCardType)
			return false;
		return true;
	}
	
	
}
