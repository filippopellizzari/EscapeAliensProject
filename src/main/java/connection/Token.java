package connection;

import java.io.Serializable;

public class Token implements Serializable{
	/**
	 * 
	 */
	private int number;
	
	
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
