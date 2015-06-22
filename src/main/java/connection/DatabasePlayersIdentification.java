package connection;

/**
 * This class contains all the token of all player, when a player sends a request sends his token, it is confronted with the correspective cell
 * and the server understands the type of request 
 * @author Nicola
 *
 */

public class DatabasePlayersIdentification {
	
	private PlayerIdentification[] identificationList;
	private static DatabasePlayersIdentification instance = new DatabasePlayersIdentification();
	
	/**
	 * This constructor creates a new arrays of identifications
	 */
	
	public DatabasePlayersIdentification() {
		this.identificationList = new PlayerIdentification[10000];
	}
	
	/**
	 * Takes an instance of this class
	 * @return
	 */
	
	public static DatabasePlayersIdentification getinstance() {
		return instance;
	}
	
	/**
	 * @return the identification
	 */
	public PlayerIdentification getIdentification(int number) {
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
	public void setIdentificationList(PlayerIdentification identification, int number) {
		this.identificationList[number] = identification;
	}
}
