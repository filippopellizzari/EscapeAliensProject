package controller;

import creator.GameCreator;
import model.*;

/**
 * 
 * @author Filippo
 *
 */

public class GameController {
	private static int numberOfGames = 0;
	private final int numberOfThisGame;
	private Game game;
	private Turn currentTurn;
	
	public GameController(String mapName, int numberOfPlayers, String typeMap) {
		this.numberOfGames ++;
		this.numberOfThisGame = numberOfGames;
		GameCreator gameCreator = GameCreator.getinstance();
		this.game = gameCreator.createGame(mapName, numberOfPlayers, typeMap);
		currentTurn = new Turn(numberOfPlayers, game);
	}
	
	/**
	 * @return the numberOfThisGame
	 */
	
	public int getNumberOfThisGame() {
		return numberOfThisGame;
	}
	
	
	
	//public doAnAction(Game DTO)
	
	public static void main(String[] args) {
		
		GameCreator gameCreator = GameCreator.getinstance();
		Game model = gameCreator.createGame("Galilei", 6, "Exagonal");
		Player player = model.getPlayers(0);
		Sector currentSector = model.getMap().getSector(new Coordinate (12,3));
		player.setSector(currentSector);
		
		
	}
}
