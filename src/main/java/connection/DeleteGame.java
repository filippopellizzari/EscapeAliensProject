package connection;

/**
 * This thread eliminates a game when the game is finished 
 * @author Nicola
 *
 */

public class DeleteGame implements Runnable {
	private int numberOfGame;
	private ListOfStartedGame listOfGames;
	
	/**
	 * Eliminate the game with the right number
	 * @param i
	 */

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