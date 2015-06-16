package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import dto.*;

/**
 * This class is used during the game, sends a dtoSend for the server, the server analizes the dto and send a
 * response saved in a list.
 * @author Nicola
 *
 */

public class SocketGame extends SocketBase implements Runnable{
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private DTOSend dtoSend;

	/**
	 * This costructor inizializes the input and output, uses object because transfer dto instead of strings
	 * @param clientData, to inizialize its parameters
	 * @param dtoSend, object that has the information about a player's action
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	
	public SocketGame(ClientSocketData clientData, DTOSend dtoSend) throws UnknownHostException, IOException {
		super(clientData);
		try {
			this.dtoSend=dtoSend;
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.print("Errore");
		}
	}

	@Override
	public void run() {
		try {
			out.writeObject(clientData.getToken());
			out.flush();
			out.writeObject(dtoSend);
			out.flush();
			clientData.setDtoGameList((DTOGame)in.readObject());
			in.close();	//close all the resource
			out.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("ImpallatoSocketGame");
		} 
	}

}
