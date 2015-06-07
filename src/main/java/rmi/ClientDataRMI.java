package rmi;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import socket.SocketChooseGame;
import socket.SocketGame;
import socket.SocketStart;
import connection.*;
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
	
	public ClientDataRMI() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry(HOST,PORT);
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
		else
			this.buffer="Tempo Scaduto e 1 solo giocatore, partita annullata";
	}
	public void clickOnDoMove(DTOSend dtoSend) throws UnknownHostException, IOException {
		dtoGame=game.doAnAction(dtoSend, token);
	}
	public static void main(String[] args) throws RemoteException, NotBoundException{
		ClientDataRMI cd=new ClientDataRMI();
	}
	
}
