package creator;

import java.util.Random;

import model.*;

public class PlayerCreator {
	private Map map;
	private Coordinate humanSector;
	private Coordinate alienSector;
	
	public PlayerCreator(Map map) {
		this.map = map;
		humanSector = map.getHumanSector();
		alienSector = map.getAlienSector();
	}
	
	private Player createHuman(int numberOfPlayer) {
		return new Player(PlayerType.HUMAN, map.getSector(humanSector), 1, numberOfPlayer);
	}
	
	private Player createAlien(int numberOfPlayer) {
		return new Player(PlayerType.ALIEN, map.getSector(alienSector), 2, numberOfPlayer);
	}
	
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
			if(players[i] != null){
				players[i] = createAlien(i);
			}
		}
		return players;
	}
}
