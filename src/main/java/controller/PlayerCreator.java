package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.*;

public class PlayerCreator {
	private Map map;
	private Coordinate humanSector;
	private Coordinate alienSector;
	
	public PlayerCreator(Map map) {
		this.map=map;
		humanSector=map.getHumanSector();
		alienSector=map.getAlienSector();
	}
	
	private Player createHuman(int numberOfPlayer) {
		return new Player(TypePlayer.HUMAN,map.getSector(humanSector),1,numberOfPlayer);
	}
	
	private Player createAlien(int numberOfPlayer) {
		return new Player(TypePlayer.HUMAN,map.getSector(alienSector),2,numberOfPlayer);
	}
	
	public List<Player> createPlayer(int numberPlayer) {
		List<Player> players=new ArrayList<Player>(numberPlayer);
		Random random=new Random();
		for(int i=0;i<numberPlayer/2;i++) {
			int number;
			do {
				number=random.nextInt(numberPlayer);
			} while(players.get(number)!=null);
			players.set(number, createHuman(number));
		}
		for(int i=0;i<numberPlayer;i++) {
			if(players.get(i)!=null) players.set(i, createAlien(i));
		}
		return players;
	}
}
