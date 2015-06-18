package connection;

public class DeleteGame implements Runnable {
	private int numberOfGame;
	private ListOfStartedGame listOfGames;

	public DeleteGame(int i) {
		this.numberOfGame=i;
		this.listOfGames=ListOfStartedGame.getinstance();
	}

	@Override
	public void run() {
		try {
			listOfGames.getGameDescriptionList(numberOfGame).getStatusGame();
			listOfGames.removeGameDescription(numberOfGame);
		} catch (InterruptedException e) {
			System.err.println("Errore nel thread per eliminare il gioco");
		}
	}

}