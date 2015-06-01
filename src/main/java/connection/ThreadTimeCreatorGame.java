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
	public ThreadTimeCreatorGame(ThreadCreateGame threadCreateGame, TypeOfMap mapName,
			ClientHandlerChooseGameSocket clientHandlerChooseGameSocket, int idGame) {
		this.motherThread=threadCreateGame;
		socketClient.add(clientHandlerChooseGameSocket);
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
		return null;
	}
	public void getView() {
		
	}
	public void ResultConnection() {
		
	}
	public String subscribe() {
		String s="ok";
		return s;
	}

}
