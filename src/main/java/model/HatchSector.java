package model;

import java.util.List;

/**
 * Subclass of Sector used because each time a human player enter in this sector the sector close so we have to 
 * override this method
 * @author Nicola
 * @see Sector
 *
 */

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
