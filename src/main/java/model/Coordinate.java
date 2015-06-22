package model;

import java.io.Serializable;

/**
 * This class is used to find a sector, x is the letter A-W, y is the number 1-14, is
 * used in class sector
 * @author Nicola
 *
 */

public class Coordinate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	/**
	 * @param x letter of the sector convert into number
	 * @param y number of the sector
	 */
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the letter of the sector in number
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * @return the number of the sector
	 */
	
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return " [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}	
}
