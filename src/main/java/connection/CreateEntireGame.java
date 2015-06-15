package connection;

import java.io.IOException;

import controller.GameController;

/**
 * This class is used to create a game and saves this game in the Database ListOfStartedGame 
 * @author Nicola
 *
 */

public class CreateEntireGame {
	
	private ListOfStartedGame listOfStartedGame;
	private ViewForPlayer[] views;
	
	/**
	 * Takes a instance of the listOfStartedGame, after that findes a free space where saves the game
	 */
	
	public CreateEntireGame() {
		this.listOfStartedGame = ListOfStartedGame.getinstance();
	}
	
	/**
	 * Create a game and return its number
	 * @param typeOfMap, the map chooses
	 * @param numberOfPlayers, the number of players in this game 
	 * @return the number of array's cell where the games is
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	
	public int createGameController(TypeOfMap typeOfMap, int numberOfPlayers) throws NumberFormatException, IOException{
		GameController gameController=new GameController(typeOfMap.getMapName(), numberOfPlayers, typeOfMap.getTypeMap());
		views=gameController.getViews();
		GameDescription newGameReadyToBeRegistred=new GameDescription(typeOfMap.getMapName(), numberOfPlayers, gameController);
		int gameNumber=listOfStartedGame.addGameDescription(newGameReadyToBeRegistred);
		createThreadTemporize(listOfStartedGame.getNumberGameDescription(gameNumber));
		return gameNumber;
	}
	
	/**
	 * Create a thread used to temporize the player's turn
	 * @param gameDescription, creates a Thread End game, this thread is used to temporize the player's turn, if the player finishes
	 * his time, this thread do some moves and change the turn
	 */
	
	public void createThreadTemporize(GameDescription gameDescription) {
		Thread temporize=new Thread(new ThreadEndTurn(gameDescription));
		temporize.start();
	}

	/**
	 * @return the views, these views are used by players
	 */
	public ViewForPlayer[] getViews() {
		return views;
	}
}
