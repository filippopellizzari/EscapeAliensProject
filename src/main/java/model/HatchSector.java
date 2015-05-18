package model;

import java.util.List;

public class HatchSector extends Sector{
	
	private boolean open;
	
	public HatchSector(SectorType sectorType, int x, int y, List<Coordinate> adjacent, boolean open){
		super(sectorType, x, y, adjacent, open);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
	
}
