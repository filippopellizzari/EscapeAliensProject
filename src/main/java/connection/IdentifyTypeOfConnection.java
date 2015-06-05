package connection;

public class IdentifyTypeOfConnection {
	
	private Identification[] identificationList;

	private static IdentifyTypeOfConnection instance = new IdentifyTypeOfConnection();
	public IdentifyTypeOfConnection() {
		this.identificationList = new Identification[10000];
	}
	
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
	
	public int getSize() {
		return identificationList.length;
	}

	public void remove(Token token) {
		identificationList[token.getNumber()]=null;
	}
}
