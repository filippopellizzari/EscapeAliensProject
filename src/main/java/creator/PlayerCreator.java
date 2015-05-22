package creator;

import java.util.Random;

import model.*;

/**
 * This class create one array of player which will be used by client as they characters in the game
 * @author Nicola
 * @see Player
 *
 */

public class PlayerCreator {
	private Map map;
	private Coordinate humanSector;
	private Coordinate alienSector;
	
	/**
	 * 
	 * @param map, used to put the player in the right sector (alien sector for alien player and human sector for human
	 * player)
	 */
	
	public PlayerCreator(Map map) {
		this.map = map;
		humanSector = map.getHumanSector();
		alienSector = map.getAlienSector();
	}
	
	/**
	 * 
	 * @param numberOfPlayer number of player to create from 1 to 8
	 * @return a human player
	 */
	
	private Player createHuman(int numberOfPlayer) {
		return new Player(PlayerType.HUMAN, map.getSector(humanSector), 1, numberOfPlayer);
	}
	
	/**
	 * 
	 * @param numberOfPlayer player to create from 1 to 8
	 * @return a alien player
	 */
	
	private Player createAlien(int numberOfPlayer) {
		return new Player(PlayerType.ALIEN, map.getSector(alienSector), 2, numberOfPlayer);
	}
	
	/**
	 * 
	 * @param totPlayers, number of player in this game, this method use a random generator to create the human players then 
	 * the other player are alien and are created with the method createAlien
	 * @return array of player
	 */
	
	public Player[] createPlayer(int totPlayers) {  //me la devi spiegare
		Player[] players = new Player[totPlayers];
		Random random = new Random();
		
		for(int i=0; i<totPlayers/2; i++) {
			int number;
			do {
				number = random.nextInt(totPlayers);
			} while(players[number] != null);
			
			players[number] = createHuman(number);
		}
		for(int i=0; i<totPlayers; i++) {
			if(players[i] == null){
				players[i] = createAlien(i);
			}
		}
		return players;
	}
}
