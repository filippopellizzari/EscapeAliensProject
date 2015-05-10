package model;

public class HatchSector extends Sector {
	public HatchSector(TypeSector typeSector, boolean crossable, int x, int y) {
		super(typeSector, crossable, x, y);
	}
	@Override
	public void addPlayer(Player player) {
		super.addPlayer(player);
		crossable=false;
	}
	
}
