package connection;

import java.io.IOException;

import controller.GameController;

public class CreateEntireGame {
	
	ListOfStartedGame listOfStartedGame;
	GameAvailable gameAvailable;
	Costrutto costrutto;
	
	public CreateEntireGame() {
		this.listOfStartedGame = ListOfStartedGame.getinstance();
		this.gameAvailable=GameAvailable.getinstance();
	}
	
	public int createGameController(String mapName, int numberOfPlayers, String typeOfMap) throws NumberFormatException, IOException{
		GameController gameController=new GameController(mapName, numberOfPlayers, typeOfMap);
		GameDescription newGameReadyToBeRegistred=new GameDescription(mapName, numberOfPlayers, gameController);
		int gameNumber=listOfStartedGame.addGameDescription(newGameReadyToBeRegistred);
		this.costrutto=new Costrutto(gameNumber);		//da modificare
		createThreadTemporize(gameController);
		return gameNumber;
	}
	public void createThreadTemporize(GameController gameController) {
		Thread temporize=new Thread(new TemporizeThread(gameController));
		temporize.start();
	}
	public Costrutto getCostrutto() {
		return costrutto;
	}
}
