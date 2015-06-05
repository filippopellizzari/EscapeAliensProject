package connection;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class ThreadTimeCreatorGame implements Runnable {
	DetailsPlayers details;
	
	public ThreadTimeCreatorGame(DetailsPlayers details) {	//dati con mappa da creare
		this.details=details;
		System.out.println("creazione di una nuova partita");
		System.out.println(details.getMapType().getMapName());
		System.out.println(details.getStatus());
		System.out.println(details.getNumberOfPlayers());
	}
	
	@Override
	public void run() {
		try {
			if(details.getNumberOfPlayers()==0) {
				System.out.println("partita senza 2 giocatori");
				details.setBuffer("Tempo Scaduto e 1 solo giocatore, partita annullata");
			}
			else {
				System.out.println("start creazine");
				details.setBuffer("Preparazione partita in corso...");
				CreateEntireGame createGame=new CreateEntireGame();		//crea gioco
				details.setView(createGame.getViews()); 		//metto le view accessibili ai players
				details.setGameId(createGame.createGameController(details.getMapType(), details.getNumberOfPlayers()+1));
				putInLock(details);		//i giocatori sono da 0 a 7 e devi metterne 1 in più
				details.setBuffer("Partita pronta, Turno Giocatore 1");
			}
			details.setStatus(StatusCreation.TERMINATED);  		//il gioco è pronto e si può eliminare da quelli in creazione
		} catch (IOException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	private synchronized void putInLock(DetailsPlayers details) throws InterruptedException {
		while(details.getNumberOfPlayers()!=-1) 
			this.wait();		//aspetto che tutti i giocatori abbiano recuperato il loro numero
	}
}
