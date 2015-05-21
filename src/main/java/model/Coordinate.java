package model;

/**
 * This class is used to find a sector, x is the letter A-W, y is the number 1-14, is
 * used in class sector 
 * 
 * @author Nicola
 * @see Sector
 *
 */

public class Coordinate {
	
	private int x;
	private int y;
	
	/**
	 * 
	 * @param x letter of the sector convert into number
	 * @param y number of the sector
	 */
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return the letter of the sector in number
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return the number of the sector
	 */
	
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
