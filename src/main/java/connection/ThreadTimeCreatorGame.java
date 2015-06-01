package connection;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rmi.ClientHandlerChooseGameRmi;
import socket.ClientHandlerChooseGameSocket;

public class ThreadTimeCreatorGame implements Runnable {
	private CreateEntireGame createGame;
	private List<ClientHandlerChooseGameRmi> rmiClient;
	private List<ClientHandlerChooseGameSocket> socketClient;
	private final ThreadCreateGame motherThread;
	private final int idGame;
	private TypeOfMap mapName;
	
	public ThreadTimeCreatorGame(ThreadCreateGame threadCreateGame, TypeOfMap mapName,int idGame) {
		this.motherThread=threadCreateGame;
		this.mapName=mapName;
		this.idGame=idGame;
	}
	@Override
	public void run() {
		startCountDown();
	}
	private void startCountDown() {
		Timer countDown=new Timer();
		countDown.schedule(startGame(motherThread), 60);
	}
	private TimerTask startGame(ThreadCreateGame motherThread2) {
		DetailsPlayers details=motherThread.getPlayerWithRelativeConnection(idGame);
		this.rmiClient=details.getRmiPlayers();
		this.socketClient=details.getSocketPlayers();
		motherThread2.removeThreadToCreateGame(idGame);   		//rimuovi il thread se è scaduto il tempo
		if(rmiClient.size()+socketClient.size()==1) {
			ResultConnection("Tempo Scaduto e 1 solo giocatore, partita annullata");
			return null;
		}
		else {
			ResultConnection("Preparazione partita in corso...");
			createGame=new CreateEntireGame();
			createGame.createGameController(mapName.getMapName(), details.getNumberOfPlayers()+1,
					mapName.getTypeMap());	//i giocatori sono da 0 a 7 e devi metterne 1 in più
			ResultConnection("Partita pronta, Turno Giocatore 1");
		}
		return null;
	}
	public void ResultConnection(String s) {
		for(int i=0;i<socketClient.size();i++) {		//notifica ai socket
			socketClient.get(i).setBuffer(s);
			socketClient.get(i).notify();
		}
		for(int i=0;i<rmiClient.size();i++) {		//notifica ai Rmi per la view
		}
	}
}
