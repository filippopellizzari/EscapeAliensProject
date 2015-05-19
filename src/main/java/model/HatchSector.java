package model;

import java.util.List;

public class HatchSector extends Sector{
	
	private boolean open;
	
	public HatchSector(SectorType sectorType, boolean open, int x, int y, List<Coordinate> adjacent){
		super(sectorType, open, x, y, adjacent);
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
	
}
