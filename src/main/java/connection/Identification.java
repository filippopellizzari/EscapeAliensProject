package connection;

public class Identification {
	private int number;
	private TypeOfConnection tipeOfConnection;
	private StatusClient statusClient;
	private int numberGame;
	private int numberPlayer;
	
	public Identification(int number, TypeOfConnection tipeOfConnection,
			StatusClient statusClient, int numberGame, int numberPlayer) {
		super();
		this.number = number;
		this.tipeOfConnection = tipeOfConnection;
		this.statusClient = statusClient;
		this.numberGame = numberGame;
		this.numberPlayer = numberPlayer;
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
	/**
	 * @return the tipeOfConnection
	 */
	public TypeOfConnection getTipeOfConnection() {
		return tipeOfConnection;
	}
	/**
	 * @param tipeOfConnection the tipeOfConnection to set
	 */
	public void setTipeOfConnection(TypeOfConnection tipeOfConnection) {
		this.tipeOfConnection = tipeOfConnection;
	}
	/**
	 * @return the statusClient
	 */
	public StatusClient getStatusClient() {
		return statusClient;
	}
	/**
	 * @param statusClient the statusClient to set
	 */
	public void setStatusClient(StatusClient statusClient) {
		this.statusClient = statusClient;
	}
	/**
	 * @return the numberGame
	 */
	public int getNumberGame() {
		return numberGame;
	}
	/**
	 * @param numberGame the numberGame to set
	 */
	public void setNumberGame(int numberGame) {
		this.numberGame = numberGame;
	}
	/**
	 * @param numberPlayer the numberPlayer to set
	 */
	public void setNumberPlayer(int numberPlayer) {
		this.numberPlayer = numberPlayer;
	}
	
}
