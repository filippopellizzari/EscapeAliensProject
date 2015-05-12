package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.*;

public class PlayerCreator {
	
	private Game game;
	private Coordinate humanSector;
	private Coordinate alienSector;
	public PlayerCreator(Game game) {
		this.game=game;
		humanSector=game.getMap().getHumanSector();
		alienSector=game.getMap().getAlienSector();
	}
	private Player CreateHuman(int numberOfPlayer) {
		Player humanPlayer=new Player(TypePlayer.Human,game.getMap().getSector(humanSector),1,numberOfPlayer);
		return humanPlayer;
	}
	private Player CreateAlien(int numberOfPlayer) {
		Player humanPlayer=new Player(TypePlayer.Human,game.getMap().getSector(alienSector),2,numberOfPlayer);
		return humanPlayer;
	}
	public List<Player> createPlayer(int numberPlayer) {
		List<Player> players=new ArrayList<Player>(numberPlayer);
		Random random=new Random();
		for(int i=0;i<numberPlayer/2;i++) {
			int number;
			do {
				number=random.nextInt(numberPlayer);
			} while(players.get(number)!=null);
			players.set(number, CreateHuman(number));
		}
		for(int i=0;i<numberPlayer;i++) {
			if(players.get(i)!=null) players.set(i, CreateAlien(i));
		}
		return players;
	}
}
