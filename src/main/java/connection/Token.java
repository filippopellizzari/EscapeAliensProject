package connection;

public class Token {
	private int number;
	private TypeOfConnection typeConnection;
	private String name;
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
	/**
	 * @return the typeConnection
	 */
	public TypeOfConnection getTypeConnection() {
		return typeConnection;
	}
	/**
	 * @param typeConnection the typeConnection to set
	 */
	public void setTypeConnection(TypeOfConnection typeConnection) {
		this.typeConnection = typeConnection;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
