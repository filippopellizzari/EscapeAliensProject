package connection;

import java.io.Serializable;

import model.*;

/**
 * 
 * @author Nicola
 *
 */

public class ViewForPlayer implements Serializable{
	private static final long serialVersionUID = 1L;
	private Coordinate coordinate;
	private PlayerType playerType;
	private int numberPlayer;
	
	/**
	 * 
	 * @param coordinate
	 * @param playerType
	 * @param numberPlayer
	 */
	
	public ViewForPlayer(Coordinate coordinate, PlayerType playerType, int numberPlayer) {
		this.coordinate = coordinate;
		this.playerType = playerType;
		this.numberPlayer=numberPlayer;
	}
	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}
	/**
	 * @return the playerType
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}
	/**
	 * @return the numberPlayer
	 */
	public int getNumberPlayer() {
		return numberPlayer;
	}
	
}
