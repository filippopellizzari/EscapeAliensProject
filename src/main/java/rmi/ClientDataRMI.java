package rmi;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import connection.*;
import dto.*;

/**
 * This class is the socket connection, invokes the method based on rmi to receives token, sends the game preference,
 * does an action and receives the message from pub-sub
 * @author Nicola
 * @see Client Data
 */

public class ClientDataRMI extends ClientData {
	
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 39999;
	private final String NAME = "room";
	private final Actions game;
	private final Registry registry;
	
	/**
	 * Load the remote registry
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws AlreadyBoundException
	 */

	public ClientDataRMI() throws RemoteException, NotBoundException,
			AlreadyBoundException {
		super();
		registry = LocateRegistry.getRegistry(HOST, PORT);
		game = (Actions) registry.lookup(NAME);
		System.out.println("Invoking remote object...");
	}
	
	/**
	 * Takes the player's token
	 */

	@Override
	public void clickOnConnection() throws IOException,
			ClassNotFoundException {
		token = game.getToken();
	}
	
	/**
	 * Subscribes a new game, if the response is positive the view returned is not null, null otherwise
	 */
	@Override
	public void clickOnStartGame(TypeOfMap typeOfMap)
			throws IOException, ClassNotFoundException {
		view = game.subscribeGame(typeOfMap, token);
		if (view != null) {
			this.setBuffer("Partita pronta, Turno Giocatore 1");
			Thread subscribe = new Thread(new subscribeRMI(this));
			subscribe.start();
		} else
			this.setBuffer("Tempo Scaduto e 1 solo giocatore, partita annullata");
	}
	
	/**
	 * Do an action in the game
	 */
	@Override
	public void clickOnDoMove(DTOSend dtoSend) throws IOException {
		Thread rmiGame = new Thread(new RmiGame(this, dtoSend));
		rmiGame.start();
	}

	/**
	 * @return the game
	 */

	public Actions getGame() {
		return game;
	}
}
