package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import connection.*;

/**
 * This class read the token from the input and if the token is -1 calls the class that creates a new toke, if the 
 * game is -1 calls the class that sends a request for a new game, otherwise calls the class that does an action on 
 * a specific game
 * @author Nicola
 *
 */

public class ClientHandler implements Runnable{
	
	private DatabasePlayersIdentification identifyTypeOfConnection;
	private Socket socket;
	
	/**
	 * This costructor receives the socket of the connection from the server and saves it.
	 * @param socket
	 */
	
	public ClientHandler(Socket socket){
		this.socket = socket;
		identifyTypeOfConnection=DatabasePlayersIdentification.getinstance();
	}
	
	/**
	 * Opens the input and the output and decides what class it has to call, depends on token received
	 */
	
	public void run() {
		try {
			ObjectOutputStream socketOut=new ObjectOutputStream(socket.getOutputStream());
			socketOut.flush();
			ObjectInputStream socketIn=new ObjectInputStream(socket.getInputStream());
			Token token = (Token)socketIn.readObject();
			Processing processing = null;
			if(token.getNumber()==-1) {
				processing=new ClientHandlerStartSocket(token,socketOut,socketIn);
				processing.start();
			}
			else {
				PlayerIdentification identify=identifyTypeOfConnection.getIdentification(token.getNumber());
				if(identify.getNumberGame()==-1) {
					processing=new ClientHandlerChooseGameSocket(token,socketOut,socketIn);
					processing.start();
				}
				else {
					processing=new ClientHandlerGameSocket(token,socketOut,socketIn);
					processing.start();
				}
			}
			socketIn.close();
			socketOut.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
}
