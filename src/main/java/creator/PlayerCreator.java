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
		humanSector = map.getHumanCoord();
		alienSector = map.getAlienCoord();
	}
	
	/**
	 * 
	 * @param numberOfPlayer number of player to create from 1 to 8
	 * @return a human player
	 */
	
	private Player createHuman(int numberOfPlayer) {
		Player human =new Player(PlayerType.HUMAN, map.getSector(humanSector), 1, numberOfPlayer);
		map.getSector(humanSector).addPlayer(human);	//aggiunge il giocatore al settore
		return human;
	}
	
	/**
	 * 
	 * @param numberOfPlayer player to create from 1 to 8
	 * @return a alien player
	 */
	
	private Player createAlien(int numberOfPlayer) {
		Player alien =new Player(PlayerType.ALIEN, map.getSector(alienSector), 2, numberOfPlayer);
		map.getSector(humanSector).addPlayer(alien);	//aggiunge il giocatore al settore
		return alien;
	}
	

	/**
	 * 
	 * @param totPlayers, number of player in this game, this method use a random generator to create the human players then 
	 * the other players are alien and are created with the method createAlien
	 * @return array of player
	 */
	
	public Player[] createPlayer(int totPlayers) {  
		Player[] players = new Player[totPlayers];
		Random random = new Random();
		int human=totPlayers/2;
		int alien=totPlayers-human;
		for(int i=0; i<totPlayers; i++) {
			if(alien>0 && human>0) {
				boolean chooseAlien=random.nextBoolean();
				if(chooseAlien) {
					players[i]=createAlien(i);
					alien--;
				}
				else {
					players[i] = createHuman(i);
					human--;
				}
			}
			else {
				if(human>0) {
					players[i] = createHuman(i);
					human--;
				}
				else{
					players[i]=createAlien(i);
					alien--;
				}
			}
		}
		return players;
	}
}
