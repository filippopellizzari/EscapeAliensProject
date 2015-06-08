package connection;

import java.io.IOException;

import controller.GameController;

public class CreateEntireGame {
	
	private ListOfStartedGame listOfStartedGame;
	private ViewForPlayer[] views;
	
	public CreateEntireGame() {
		this.listOfStartedGame = ListOfStartedGame.getinstance();
	}
	
	public int createGameController(TypeOfMap typeOfMap, int numberOfPlayers) throws NumberFormatException, IOException{
		GameController gameController=new GameController(typeOfMap.getMapName(), numberOfPlayers, typeOfMap.getTypeMap());
		views=gameController.getViews();
		GameDescription newGameReadyToBeRegistred=new GameDescription(typeOfMap.getMapName(), numberOfPlayers, gameController);
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
