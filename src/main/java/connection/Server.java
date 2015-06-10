package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.*;
import socket.ClientHandler;

public class Server {
	private final static int PORT = 29999;
	private final static int Port = 39999;
	private Registry registry;
	private static final String NAME = "room";
	
	public void startServer() {
		ServerSocket serverSocket;
		try {
			registry = LocateRegistry.createRegistry(Port);
			System.out.println("Constructing server implementation");
			RMIRoom game = new RMIRoom();
			Actions gameStub = (Actions) UnicastRemoteObject.exportObject(game, Port);
			System.out.println("Binding server implementation to registry...");
			registry.rebind(NAME, gameStub);
			System.out.println("Waiting for invocations from clients...");
			serverSocket = new ServerSocket(PORT);
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
		} catch (IOException e1) {
			System.err.println("errore nella run");
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.startServer();
	}
}
