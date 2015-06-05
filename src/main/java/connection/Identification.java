package connection;

public class Identification {
	private int number;
	private int numberGame;
	private int numberPlayer;
	
	public Identification(int number, int numberGame, int numberPlayer) {
		super();
		this.number = number;
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

	/**
	 * @return the numberPlayer
	 */
	public int getNumberPlayer() {
		return numberPlayer;
	}
	
}
