package connection;

import java.io.IOException;

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
