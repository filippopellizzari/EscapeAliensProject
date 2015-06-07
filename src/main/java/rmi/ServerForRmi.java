package rmi;

import java.io.IOException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerForRmi {
	private final static int PORT = 39999;
	private final Registry registry;
	private static final String NAME = "room";
	
	public ServerForRmi() throws IOException {
		registry = LocateRegistry.createRegistry(PORT);
		System.out.println("Constructing server implementation");
		RMIRoom game = new RMIRoom();
		Actions gameStub = (Actions) UnicastRemoteObject.exportObject(game, 0);
		System.out.println("Binding server implementation to registry...");
		registry.rebind(NAME, gameStub);
		System.out.println("Waiting for invocations from clients...");
	}
	public static final void main(String[] args) throws AlreadyBoundException, IOException {
		ServerForRmi server2=new ServerForRmi();
	}
}