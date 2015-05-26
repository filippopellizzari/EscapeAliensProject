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
	
	/**
	 * 
	 * @param sectorType indicate the varius tipe each has its own propriety defined in the controller for the game rules
	 * @param closed indicate if the sector is crossable by player
	 * @param x		use for indicate the letter of a sector
	 * @param y		use for indicate the number of a sector
	 * @param adjacent	list of adjacent sectors's coordinate
	 */
	
	public HatchSector(SectorType sectorType, boolean closed, int x, int y, List<Coordinate> adjacent){
		super(sectorType, closed, x, y, adjacent);
	}
	
	@Override
	public void addPlayer(Player player) {	//a hatch sector close when a human enters
		super.addPlayer(player);
		this.closed = true;
	}
}
