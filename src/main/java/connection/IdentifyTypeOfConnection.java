package connection;

public class IdentifyTypeOfConnection {
	
	private Identification[] identification;

	public IdentifyTypeOfConnection(Identification[] identification) {
		super();
		this.identification = identification;
	}

	/**
	 * @return the identification
	 */
	public Identification[] getIdentification() {
		return identification;
	}

	/**
	 * @param identification the identification to set
	 */
	public void setIdentification(Identification[] identification) {
		this.identification = identification;
	}
	
	public Identification getPlayerIdentify(int number) {
		return identification[number];
	}
 
}
