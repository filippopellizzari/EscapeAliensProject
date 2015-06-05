package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.Token;

public abstract class SocketHandler implements Runnable{
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	protected Socket socket;
	protected Token token;
	
	public SocketHandler(Token token, Socket socket) throws UnknownHostException, IOException {
		this.socket=socket;
		this.token=token;
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		outputStream.flush();
		inputStream = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {}

}
