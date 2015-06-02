package connection;

import java.io.IOException;

import controller.GameController;

public class CreateEntireGame {
	
	private ListOfStartedGame listOfStartedGame;
	private ViewForPlayer[] views;
	
	public CreateEntireGame() {
		this.listOfStartedGame = ListOfStartedGame.getinstance();
	}
	
	public int createGameController(String mapName, int numberOfPlayers, String typeOfMap) throws NumberFormatException, IOException{
		GameController gameController=new GameController(mapName, numberOfPlayers, typeOfMap);
		views=gameController.getViews();
		GameDescription newGameReadyToBeRegistred=new GameDescription(mapName, numberOfPlayers, gameController);
		int gameNumber=listOfStartedGame.addGameDescription(newGameReadyToBeRegistred);
		createThreadTemporize(gameController);
		return gameNumber;
	}
	public void createThreadTemporize(GameController gameController) {
		Thread temporize=new Thread(new TemporizeThread(gameController));
		temporize.start();
	}

	/**
	 * @return the views
	 */
	public ViewForPlayer[] getViews() {
		return views;
	}
}
