package connection;

/**
 * This class contains all the token of all player, when a player sends a request sends his token, it is confronted with the correspective cell
 * and the server understands the type of request 
 * @author Nicola
 *
 */

public class IdentifyTypeOfConnection {
	
	private Identification[] identificationList;
	private static IdentifyTypeOfConnection instance = new IdentifyTypeOfConnection();
	
	/**
	 * This constructor creates a new arrays of identifications
	 */
	
	public IdentifyTypeOfConnection() {
		this.identificationList = new Identification[10000];
	}
	
	/**
	 * Takes an instance of this class
	 * @return
	 */
	
	public static IdentifyTypeOfConnection getinstance() {
		return instance;
	}
	
	/**
	 * @return the identification
	 */
	public Identification getIdentification(int number) {
		if(identificationList[number]==null) return null;
		return identificationList[number];
	}
	
	/**
	 * Get the size of the tokens' array
	 * @return
	 */
	
	public int getSize() {
		return identificationList.length;
	}
	
	/**
	 * Remove a token
	 * @param token
	 */

	public void remove(Token token) {
		identificationList[token.getNumber()]=null;
	}

	/**
	 * @param identificationList the identificationList to set
	 */
	public void setIdentificationList(Identification identification, int number) {
		this.identificationList[number] = identification;
	}
}
