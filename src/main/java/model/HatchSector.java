package model;

import java.util.List;

public class HatchSector extends Sector{
	public HatchSector(SectorType sectorType, boolean close, int x, int y, List<Coordinate> adjacent){
		super(sectorType, close, x, y, adjacent);
	}
	
	@Override
	public void addPlayer(Player player) {			//because hatch sector is blocked when a human enter we close the sector after this operation
		super.addPlayer(player);
		this.close=true;
	}
}
