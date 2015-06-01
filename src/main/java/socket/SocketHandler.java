package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketHandler implements Runnable{
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	protected Socket socket;
	private int port=27777;
	private String host="127.0.0.1";
	
	public SocketHandler() throws UnknownHostException, IOException {
		this.socket=new Socket(host, port);
		inputStream = new ObjectInputStream(socket.getInputStream());
		outputStream = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {}

}
