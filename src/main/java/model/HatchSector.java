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
	public HatchSector(SectorType sectorType, boolean closed, int x, int y, List<Coordinate> adjacent){
		super(sectorType, closed, x, y, adjacent);
	}
	
	@Override
	public void addPlayer(Player player) {	//a hatch sector close when a human enters
		super.addPlayer(player);
		this.closed = true;
	}
}
