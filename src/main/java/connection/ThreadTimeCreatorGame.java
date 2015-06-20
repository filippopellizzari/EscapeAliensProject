package connection;

import java.io.IOException;

/**
 * This class is used to create a game if there are at least 2 player, invokes the method to create the game and when the players have taken 
 * the views deletes the game from the database of subscribing
 * @author Nicola
 *
 */

public class ThreadTimeCreatorGame implements Runnable {
	DetailsPlayers details;
	
	/**
	 * This costructor inizialize a new detailsPlayer, a class that contains all the data used to create a game and sends the views to the players
	 * @param details
	 */
	
	public ThreadTimeCreatorGame(DetailsPlayers details) {	//dati con mappa da creare
		this.details=details;
	}
	
	/**
	 * Creates a game if there are 2 or more players, sets the game status terminated
	 */
	
	@Override
	public void run() {
		try {
			if(details.getNumberOfPlayers()==0) {
				details.setBuffer("Tempo Scaduto e 1 solo giocatore, partita annullata");
			}
			else {
				CreateEntireGame createGame=new CreateEntireGame();		//crea gioco
				details.setGameId(createGame.createGameController(details.getMapType(), details.getNumberOfPlayers()+1));
				details.setView(createGame.getViews()); 		//metto le view accessibili ai players
				details.setBuffer("Partita pronta, Turno Giocatore 1");
				putInLock(details);		//i giocatori sono da 0 a 7 e devi metterne 1 in più
			}
			details.setStatus(StatusCreation.TERMINATED);  		//il gioco è pronto e si può eliminare da quelli in creazione
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Waits until all the players have taken their view then removes the game
	 * @param details
	 */
	private synchronized void putInLock(DetailsPlayers details){
		try {
			System.out.println("thread creazione aspetto acquisizione view");
			details.allPlayersHaveTakeView();
			System.out.println("view acquisite");
		}catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}
