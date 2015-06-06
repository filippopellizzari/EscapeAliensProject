package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class SocketBase {
	private final static int PORT = 29999;
	private final static String IP="127.0.0.1";
	protected Socket socket;
	protected ClientData clientData;
	
	public SocketBase(ClientData clientData) throws UnknownHostException, IOException {
		this.socket=new Socket(IP,PORT);
		this.clientData=clientData;
	}
}
