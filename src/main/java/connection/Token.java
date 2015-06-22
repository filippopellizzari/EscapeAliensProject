package connection;

import java.io.Serializable;

/**
 * This class is the token passed from player to server for identifies the player
 * @author Nicola
 *
 */

public class Token implements Serializable{
	private static final long serialVersionUID = 1L;
	private int number;
	
	/**
	 * @param number, number of the token
	 */
	
	public Token(int number) {
		this.number = number;
	}
	
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
}
