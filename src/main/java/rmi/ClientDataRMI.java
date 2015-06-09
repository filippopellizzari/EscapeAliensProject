package rmi;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.Coordinate;
import pubSub.SubscriberThread;
import connection.*;
import controller.TypeOfAction;
import dto.*;


public class ClientDataRMI {
	private Token token;
	private ViewForPlayer view;
	private DTOGame dtoGame;
	private String buffer;
	private final static String HOST="127.0.0.1";
	private final static int PORT=39999;
	private final String NAME = "room";
	private final Actions game;
	private final Registry registry;
	
	public ClientDataRMI() throws RemoteException, NotBoundException {
		registry = LocateRegistry.getRegistry(HOST,PORT);
		game = (Actions) registry.lookup(NAME);
		System.out.println("Invoking remote object...");
		this.token=new Token(-1);
	}
	public void clickOnConnectionRMI() throws UnknownHostException, IOException, ClassNotFoundException{
		token=game.getToken();
	}
	public void clickOnStartGame(TypeOfMap typeOfMap) throws UnknownHostException, IOException, ClassNotFoundException {
		this.buffer="Iscrizione Ricevuta";
		view=game.subscribeGame(typeOfMap,token);
		if(view==null)
			this.buffer="Partita pronta, Turno Giocatore 1";
		else {
			Thread subcriber=new Thread(new SubscriberThread(token));		//parte il subscribe
			subcriber.start();
			this.buffer="Tempo Scaduto e 1 solo giocatore, partita annullata";
			System.out.println(view.getNumberPlayer());
			System.out.println(view.getCoordinate());
			System.out.println(view.getPlayerType());
		}
	}
	public void clickOnDoMove(DTOSend dtoSend) throws UnknownHostException, IOException {
		dtoGame=game.doAnAction(dtoSend, token);
	}
	
	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}
	/**
	 * @return the view
	 */
	public ViewForPlayer getView() {
		return view;
	}
	
	public static void main(String[] args) throws RemoteException, NotBoundException{
		try {
			ClientDataRMI cd=new ClientDataRMI();
			System.out.println(cd.getToken().getNumber());
			cd.clickOnConnectionRMI();
			Thread.sleep(2000);
			System.out.println(cd.getToken().getNumber());
			cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
			Thread.sleep(40000);
			DTOSend dtoSend=new DTOSend(new Coordinate(12, 123) , cd.getView().getNumberPlayer(), null, TypeOfAction.MOVE, null);
			cd.clickOnDoMove(dtoSend);
			Thread.sleep(10000);
			System.out.println(cd.dtoGame.getGameMessage());
		} catch (IOException | ClassNotFoundException | InterruptedException e1) {
			System.err.println("Errore in clientData");
		}
	}
	
}
