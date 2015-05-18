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
	
	public Player[] createPlayer(int numberPlayer) {
		Player[] players=new Player[numberPlayer];
		Random random=new Random();
		for(int i=0;i<numberPlayer/2;i++) {
			int number;
			do {
				number=random.nextInt(numberPlayer);
			} while(players[number]!=null);
			players[number]=createHuman(number);
		}
		for(int i=0;i<numberPlayer;i++) {
			if(players[i]!=null) players[i]=createAlien(i);
		}
		return players;
	}
}
