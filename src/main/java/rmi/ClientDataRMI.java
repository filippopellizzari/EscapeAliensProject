package rmi;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import connection.*;
import controller.ActionType;
import dto.*;

public class ClientDataRMI extends ClientData {
	private Token token;
	private ViewForPlayer view;
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 39999;
	private final String NAME = "room";
	private final Actions game;
	private final Registry registry;

	public ClientDataRMI() throws RemoteException, NotBoundException,
			AlreadyBoundException {
		super();
		registry = LocateRegistry.getRegistry(HOST, PORT);
		game = (Actions) registry.lookup(NAME);
		System.out.println("Invoking remote object...");
	}

	public void clickOnConnection() throws UnknownHostException, IOException,
			ClassNotFoundException {
		token = game.getToken();
	}

	public void clickOnStartGame(TypeOfMap typeOfMap)
			throws UnknownHostException, IOException, ClassNotFoundException {
		buffer.add("Iscrizione inviata");
		view = game.subscribeGame(typeOfMap, token);
		if (view != null) {
			this.buffer.add("Partita pronta, Turno Giocatore 1");
			Thread subscribe = new Thread(new subscribeRMI(this));
			subscribe.start();
		} else
			this.buffer
					.add("Tempo Scaduto e 1 solo giocatore, partita annullata");
	}

	public void clickOnDoMove(DTOSend dtoSend) throws UnknownHostException,
			IOException {
		Thread rmiGame = new Thread(new RmiGame(this, dtoSend));
		rmiGame.start();
	}

	/**
	 * @return the game
	 */

	public Actions getGame() {
		return game;
	}

	public static void main(String[] args) throws NotBoundException,
			UnknownHostException, ClassNotFoundException, IOException,
			InterruptedException, AlreadyBoundException {
		ClientDataRMI cd = new ClientDataRMI();
		System.out.println(cd.getToken().getNumber());
		cd.clickOnConnection();
		Thread.sleep(2000);
		System.out.println(cd.getToken().getNumber());
		cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
		System.out.println(cd.getBuffer().remove(0));
		Thread.sleep(40000);
		System.out.println(cd.getBuffer().remove(0));
		DTOSend dtoSend = new DTOSend(null, 1, null, ActionType.CHAT,
				"benvenuti nel gioco");
		cd.clickOnDoMove(dtoSend);
		Thread.sleep(10000);
	}
}
