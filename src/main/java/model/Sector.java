package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class define the "cells" that compounds the map, each has a type, the coordinate to adjcent sector to know if player can move there and a 
 * list of player in this sector, the attribute close indicate if it is possible to move in this sector
 * @author Nicola
 * @author Filippo
 *@see Coordinate to know what it is
 */

public class Sector {
	
	private final SectorType sectorType;
	private final List<Player> players;
	private final List<Coordinate> adjacent;
	protected boolean closed;
	private Coordinate coordinate;
	
	/**
	 * 
	 * @param sectorType indicate the varius tipe each has its own propriety defined in the controller for the game rules
	 * @param closed indicate if the sector is crossable by player
	 * @param x		use for indicate the letter of a sector
	 * @param y		use for indicate the number of a sector
	 * @param adjacent	list of adjacent sectors's coordinate
	 */
	
	public Sector (SectorType sectorType, boolean closed, int x, int y, List<Coordinate> adjacent){
		this.coordinate = new Coordinate(x,y);
		this.sectorType = sectorType;
		this.adjacent = adjacent;
		this.players = new ArrayList<Player>();
		this.closed = closed;
	}
	
	/**
	 * 
	 * @return the type of sector
	 * @see SectorType
	 */
	
	public SectorType getType() {
		return sectorType;
	}

	/**
	 * 
	 * @return list of coordinate of adjacent sectors used by controller to indicate if player can go in these sectors
	 */
	
	public List<Coordinate> getAdjacent() {
		return adjacent;
	}

	/**
	 * 
	 * @return the list of player in the sector, used for test this class
	 */

	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * 
	 * @param player, add a player in this sector
	 */
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	/**
	 * 
	 * @return a player used when this player move to another cell, so this method cancels that player from this cell
	 */
	
	public Player removePlayer() {
		return players.remove(0);
	}
	
	/**
	 * 
	 * @return true se il settore è "chiuso", ossia non attraversabile e non raggiungibile
	 */
	public boolean isClosed() {
		return closed;
	}
	
	/**
	 * 
	 * @return the coordinate of this sector
	 * @see Coordinate
	 */
	
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	


	@Override
	public String toString() {
		return "Sector [sectorType=" + sectorType + ", getX()=" + getCoordinate().getX()
				+ ", getY()=" + getCoordinate().getY() + "]";
	}

	
}
