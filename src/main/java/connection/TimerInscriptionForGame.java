package connection;

/**
 * This class is used to cronometer the time while a game is open to subscribe, when the time stops the 
 * game is closed and is created or cancelled
 * @author Nicola
 *
 */

public class TimerInscriptionForGame implements Runnable {

	private int time;
	private DatabaseInscriptionsForGames database;
	private TypeOfMap mapType;

	/**
	 * This Constructor inizialize the time and sets the parameter of the game
	 * @param time
	 * @param databaseGame
	 * @param mapType
	 */
	
	public TimerInscriptionForGame(int time, DatabaseInscriptionsForGames databaseGame,
			TypeOfMap mapType) {
		this.time = time;
		this.database = databaseGame;
		this.mapType = mapType;
	}
	
	/**
	 * Sleep for tot seconds then closes the inscription, wait until the game is created then cancel the game 
	 * from the database
	 */

	@Override
	public void run() {
		try {
			System.out.println("Sono il thread creato per cronometrare la partita: mi metto a dormire");
			Thread.sleep(time * 1000);
			System.out.println("Sono il thread creato per cronometrare la partita: mi sveglio");
			for (int i = 0; i < database.getPlayerWithRelativeConnection()
					.size(); i++) { // rimuovi la giusta partita dal database
				if (database.getPlayerWithRelativeConnection().get(i).getMapType() == mapType) {
					database.blockGame(i);
					System.out.println("Sono il thread creato per cronometrare la partita: mi metto a dormire in attesa che sia creata");
					database.getPlayerWithRelativeConnection().get(i).deleteGame();
					System.out.println("Sono il thread creato per cronometrare la partita: mi sveglio dopo che è stata creata");
					database.removeGame(i); // rimuovilo
				}
			}
			System.out.println("Sono il thread creato per cronometrare la partita: ho finito");
		} catch (InterruptedException e) {
			System.err.print("Errore nel fine turno");
		}
	}

}
