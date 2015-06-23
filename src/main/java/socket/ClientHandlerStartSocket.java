package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import connection.*;

/**
 * This class is used by the server for send to a client a new token (id), the
 * class finds a free token and sends it to the client, this token is used to
 * create a new record in the class playerIdentification that contains all the
 * information about that player
 * @author Nicola
 *
 */

public class ClientHandlerStartSocket implements Processing {

	private DatabasePlayersIdentification identifyConnection;
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	/**
	 * This costructor inizialize the input and output, used to read and send
	 * objects
	 * 
	 * @param token, sended by a player
	 * @param socketOut, reads the output of the socket
	 * @param socketIn, reads the input of the socket
	 */

	public ClientHandlerStartSocket(Token token, ObjectOutputStream socketOut,
			ObjectInputStream socketIn) {
		identifyConnection = DatabasePlayersIdentification.getinstance();
		this.token = token;
		this.out = socketOut;
		this.in = socketIn;
	}

	/**
	 * This method finds a free token, sends this to the player and create a new
	 * record in the class DatabasePlayersIdentification, this record contains the
	 * information about the player
	 */
	@Override
	public void start() {
		try {
			boolean numberFound = false;
			int i = 0;
			PlayerIdentification identificationToBeWrite;
			do {
				identificationToBeWrite = identifyConnection
						.getIdentification(i);
				if (identificationToBeWrite == null) {
					identificationToBeWrite = new PlayerIdentification(-1, 0);
					identifyConnection.setIdentificationList(
							identificationToBeWrite, i); // aggiorna il database
					token = new Token(i);
					out.writeObject(token); // send the new token
					numberFound = true; // trovata posizione
				}
				i++;
			} while (numberFound == false && i < 10000);
		} catch (IOException e) {
			System.err.println("ImpallatoClientStartSocket");
		}
	}
}