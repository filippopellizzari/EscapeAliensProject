package connection;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rmi.ClientHandlerChooseGameRmi;
import socket.ClientHandlerChooseGameSocket;

public class ThreadTimeCreatorGame implements Runnable {
	private CreateEntireGame createGame;
	private final DatabaseCreateGame motherThread;
	private final int idGame;
	private TypeOfMap mapName;
	
	public ThreadTimeCreatorGame(DatabaseCreateGame threadCreateGame, TypeOfMap mapName,int idGame) {
		this.motherThread=threadCreateGame;
		this.mapName=mapName;
		this.idGame=idGame;
	}
	@Override
	public void run() {
		try {
			startCountDown();
		} catch (NumberFormatException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void startCountDown() throws NumberFormatException, IOException, InterruptedException {
		Timer countDown=new Timer();
		countDown.schedule(startGame(motherThread), 60);
	}
	private TimerTask startGame(DatabaseCreateGame motherThread2) throws NumberFormatException, IOException, InterruptedException {
		DetailsPlayers details=motherThread.getPlayerWithRelativeConnection(idGame);		//dettagli partita
		motherThread2.removeThreadToCreateGame(idGame);   		//rimuovi il thread se è scaduto il tempo
		if(details.getNumberOfPlayers()==0) {
			details.setBuffer("Tempo Scaduto e 1 solo giocatore, partita annullata");
			return null;
		}
		else {
			details.setBuffer("Preparazione partita in corso...");
			createGame=new CreateEntireGame();
			details.setView(createGame.getViews()); 		//metto le view accessibili ai players
			int gameNumber=createGame.createGameController(mapName.getMapName(), details.getNumberOfPlayers()+1,
					mapName.getTypeMap());	//i giocatori sono da 0 a 7 e devi metterne 1 in più
			details.setGameId(gameNumber);  //ora ha il numero di gioco
			while(details.getNumberOfPlayers()!=-1) putInLock();
			details.setBuffer("Partita pronta, Turno Giocatore 1");
		}
		return null;
	}
	private synchronized void putInLock() throws InterruptedException {
		this.wait();		//aspetto che tutti i giocatori abbiano recuperato il loro numero
	}
}
