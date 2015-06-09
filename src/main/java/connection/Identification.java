package connection;

import java.util.ArrayList;
import java.util.List;

import dto.DTOGame;

public class Identification {
	private int numberGame;
	private int numberPlayer;
	
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
	 * @param numberPlayer the numberPlayer to set
	 */

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
