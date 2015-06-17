package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class connect a client's request with the corrects port and id
 * @author Nicola
 *
 */

public abstract class SocketBase {
	private final static int PORT = 29999;
	private final static String IP="127.0.0.1";
	protected Socket socket;
	protected ClientDataSocket clientData;
	
	/**
	 * This method create a socket that is used to send a message to the game's server
	 * @param clientData, to inizialize its parameters
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	
	public SocketBase(ClientDataSocket clientData) throws UnknownHostException, IOException {
		this.socket=new Socket(IP,PORT);
		this.clientData=clientData;
	}
}
