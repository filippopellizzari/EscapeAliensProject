package connection;

import java.util.ArrayList;
import java.util.List;

public class IdentifyTypeOfConnection {
	
	private List<Identification> identificationList;

	private static IdentifyTypeOfConnection instance = new IdentifyTypeOfConnection();
	public IdentifyTypeOfConnection() {
		this.identificationList = new ArrayList<Identification>();
	}
	
	public static IdentifyTypeOfConnection getinstance() {
		return instance;
	}
	
	/**
	 * @return the identification
	 */
	public List<Identification> getIdentification() {
		return identificationList;
	}
	
	public int getSize() {
		return identificationList.size();
	}

	public void remove(Token token) {
		identificationList.remove(token.getNumber());
	}
}
