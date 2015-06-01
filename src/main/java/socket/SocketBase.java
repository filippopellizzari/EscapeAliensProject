package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import connection.Token;

public abstract class SocketBase {
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	protected Socket socket;
	protected Token token;
	
	public SocketBase(int port, String host, Token token) throws UnknownHostException, IOException {
		this.socket=new Socket(host, port);
		inputStream = new ObjectInputStream(socket.getInputStream());
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		this.token=token;
	}

	public abstract void startClient() throws IOException, ClassNotFoundException;
}
