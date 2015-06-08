package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerForSocket {
	private final static int PORT = 29999;
	
	public void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server socket ready on port: " + PORT);
		System.out.println("Server ready");
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				Thread client=new Thread(new ClientHandler(socket));
				client.start();
			} catch (IOException e) {
				break;
			}
		}
		serverSocket.close();
	}
	public static void main(String[] args) {
		ServerForSocket server = new ServerForSocket();
		try {
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
