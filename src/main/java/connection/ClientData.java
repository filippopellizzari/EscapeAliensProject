package connection;

import java.io.IOException;
import java.net.UnknownHostException;

import dto.*;
import socket.*;

public class ClientData {
	private Token token;
	private ViewForPlayer game;
	private ViewForPlayer view;
	private DTOGame dtoGame;
	
	public ClientData() {
		this.token=new Token(-1);
	}
	public void clickOnConnectionSocket() throws UnknownHostException, IOException, ClassNotFoundException{
		Thread starSocket=new Thread(new SocketStart(token));
		starSocket.start();
	}
	public void clickOnStartGame(TypeOfMap typeOfMap) throws UnknownHostException, IOException, ClassNotFoundException {
		Thread choose=new Thread(new SocketChooseGame(token, view, typeOfMap));
		choose.start();
	}
	public void clickOnDoMove(DTOSend dtoSend, DTOGame dtoGame) throws UnknownHostException, IOException {
		Thread action=new Thread(new SocketGame(token, dtoSend, dtoGame));
		action.start();
	}
	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}
	/**
	 * @return the game
	 */
	public ViewForPlayer getGame() {
		return game;
	}
	/**
	 * @return the view
	 */
	public ViewForPlayer getView() {
		return view;
	}
	/**
	 * @return the dtoGame
	 */
	public DTOGame getDtoGame() {
		return dtoGame;
	}
	
}
