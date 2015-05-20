package controller;

import creator.GameCreator;
import model.*;

public class GameController {
	
	
	public static void main(String[] args) {
		
	int totPlayers = 8;
	Game model = GameCreator.getinstance().createGame("Galilei", totPlayers, "Exagonal");
	System.out.println("Alien Sector: "+model.getMap().getAlienSector());
	
	


	

	}
	
	
 

	
}
