package rmi;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import socket.SocketStart;
import model.Coordinate;
import connection.*;
import controller.TypeOfAction;
import dto.*;


public class ClientDataRMI{
	private Token token;
	private ViewForPlayer view;
	private List<DTOGame> dtoGameList;
	private List<String> buffer;
	private final static String HOST="127.0.0.1";
	private final static int PORT=39999;
	private final String NAME = "room";
	private final Actions game;
	private final Registry registry;
	private SetClientParameter setParameter;
	
	public ClientDataRMI() throws RemoteException, NotBoundException, AlreadyBoundException {
		registry = LocateRegistry.getRegistry(HOST,PORT);
		game = (Actions) registry.lookup(NAME);
		System.out.println("Invoking remote object...");
		this.token=new Token(-1);
		this.dtoGameList=new ArrayList<DTOGame>();
		this.buffer=new ArrayList<String>();
		setParameter=new ClientStub(dtoGameList, buffer, view);
	}
	
	public void clickOnConnectionRMI() throws UnknownHostException, IOException, ClassNotFoundException{
		token=game.getToken();
	}
	
	public void clickOnStartGame(TypeOfMap typeOfMap) throws UnknownHostException, IOException, ClassNotFoundException {
		game.subscribeGame(typeOfMap, token,setParameter);
		if(view!=null) {
			this.buffer.add("Partita pronta, Turno Giocatore 1");
			System.out.println(view.getNumberPlayer());
			System.out.println(view.getCoordinate());
			System.out.println(view.getPlayerType());
			game.subscribe(setParameter);
		}
		else 
			this.buffer.add("Tempo Scaduto e 1 solo giocatore, partita annullata");
	}
	
	public void clickOnDoMove(DTOSend dtoSend) throws UnknownHostException, IOException {
		Thread rmiGame=new Thread(new RmiGame(this,dtoSend));
		rmiGame.start();
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
	
	/**
	 * @return the dtoGameList
	 */
	public List<DTOGame> getDtoGameList() {
		return dtoGameList;
	}
	/**
	 * @param dtoGameList the dtoGameList to set
	 */
	public void setDtoGameList(DTOGame dtoGame) {
		this.dtoGameList.add(dtoGame);
	}
	/**
	 * @return the buffer
	 */
	public List<String> getBuffer() {
		return buffer;
	}
	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(String buffer) {
		this.buffer.add(buffer);
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}
	/**
	 * @param view the view to set
	 */
	public void setView(ViewForPlayer view) {
		this.view = view;
	}
	
	/**
	 * @return the game
	 */
	public Actions getGame() {
		return game;
	}
	
	/**
	 * @return the setParameter
	 */
	public SetClientParameter getSetParameter() {
		return setParameter;
	}
	public static void main(String[] args) throws NotBoundException, UnknownHostException, ClassNotFoundException, IOException, InterruptedException, AlreadyBoundException{
			ClientDataRMI cd=new ClientDataRMI();
			System.out.println(cd.getToken().getNumber());
			cd.clickOnConnectionRMI();
			Thread.sleep(2000);
			System.out.println(cd.getToken().getNumber());
			cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
			Thread.sleep(40000);
			System.out.println(cd.getBuffer());
			DTOSend dtoSend=new DTOSend(new Coordinate(12, 123) , cd.getView().getNumberPlayer(), null, TypeOfAction.MOVE, null);
			cd.clickOnDoMove(dtoSend);
			Thread.sleep(10000);
			System.out.println(cd.getDtoGameList().size());
	}
	
}