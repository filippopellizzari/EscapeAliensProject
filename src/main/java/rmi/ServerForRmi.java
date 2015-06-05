package rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerForRmi {
	private final static int PORT = 39999;
	public void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server rmi ready on port: " + PORT);
		System.out.println("Server ready");
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				Thread client=new Thread(new ClientHandlerRmi(socket));
				client.start();
			} catch (IOException e) {
				break;
			}
		}
		serverSocket.close();
	}
	public static void main(String[] args) {
		ServerForRmi server = new ServerForRmi();
		try {
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
