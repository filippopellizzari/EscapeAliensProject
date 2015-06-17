package connection;

/**
 * This class is the identification, every time a new player sends a token for the fist time this class is created to track the status of 
 * the player
 * @author Nicola
 *
 */

public class Identification {
	private int numberGame;
	private int numberPlayer;
	
	/**
	 * This constructor set the number of game and the number of player for this game
	 * @param numberGame
	 * @param numberPlayer
	 */
	
	public Identification(int numberGame, int numberPlayer) {
		this.numberGame = numberGame;
		this.numberPlayer=numberPlayer;
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
	 * @return the numberPlayer
	 */
	
	public int getNumberPlayer() {
		return numberPlayer;
	}

	/**
	 * @param numberPlayer the numberPlayer to set
	 */
	
	public void setNumberPlayer(int numberPlayer) {
		this.numberPlayer = numberPlayer;
	}
}
