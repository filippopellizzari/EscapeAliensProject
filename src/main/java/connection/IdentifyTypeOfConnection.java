package connection;

import java.util.ArrayList;
import java.util.List;

public class IdentifyTypeOfConnection {
	
	private List<Identification> identificationList;

	public IdentifyTypeOfConnection(Identification[] identification) {
		this.identificationList = new ArrayList<Identification>();
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
