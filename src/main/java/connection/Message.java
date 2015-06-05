package connection;

import java.io.Serializable;

public class Message implements Serializable{
	private String message;
	
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
