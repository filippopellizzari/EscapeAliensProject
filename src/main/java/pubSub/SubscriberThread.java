package pubSub;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import connection.Token;
import dto.DTOGame;
import socket.ClientData;

public class SubscriberThread extends Thread{
	private Socket socket;
	private final static int PORT = 27777;
	private final static String IP="127.0.0.1";
	private ClientData clientData;
	private ObjectInputStream in;
	
	public SubscriberThread(ClientData clientData) throws UnknownHostException, IOException {
		this.socket=new Socket(IP,PORT);
		this.clientData=clientData;
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public SubscriberThread(Token token) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		/*DTOGame dtoGame = null;
		do {
			try {
				dtoGame=(DTOGame)in.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				System.err.println("Errore nello sleep");
			}
			clientData.setDtoGame(dtoGame);  			//setto il dto game
		} while(dtoGame.getGameMessage()!="Fine Partita");
		close();*/
	}
	
	private void close(){
			try {
				in.close();
				socket.close();
			} catch (IOException e) {
				System.out.println("Errore nella chiusura");
			}
	}
}