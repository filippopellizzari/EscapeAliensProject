package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.ClientData;
import connection.Token;

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
