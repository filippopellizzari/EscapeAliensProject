package connection;

import java.io.Serializable;

/**
 * This class is used to pass a string from server to client
 * @author Nicola
 *
 */

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	
	/**
	 * Constructor for the message, a string is setteds in the camp message
	 * @param message
	 */
	
	public Message(String message) {
		this.message=message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
}
