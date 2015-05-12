package model;

import java.util.List;

public class HatchSector extends Sector {
	
	public HatchSector(TypeSector typeSector, boolean crossable, int x, int y, List<Coordinate> nextSector) {
		super(typeSector, crossable, x, y, nextSector);
	}
	@Override
	public void addPlayer(Player player) {
		super.addPlayer(player);
		crossable=false;
	}
	
}
