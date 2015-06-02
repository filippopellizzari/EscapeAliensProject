package connection;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import dto.DTOGame;
import dto.DTOSend;
import socket.*;

public class ClientData {
	private Token token;
	List<TypeOfMap> typeOfMap=new ArrayList<TypeOfMap>();
	private ViewForPlayer game;
	private int port=27777;
	private String host="127.0.0.1";
	private ViewForPlayer view;
	private DTOGame dtoGame;
	public ClientData(String name, TypeOfConnection typeConnection) {
		this.token=new Token(-1,typeConnection,name);
	}
	private void clickOnConnection() throws UnknownHostException, IOException, ClassNotFoundException{
		if(token.getTypeConnection()==TypeOfConnection.SOCKET) {
			SocketStart starSocket=new SocketStart(port, host, token, typeOfMap);
			starSocket.startClient();
		}
	}
	private void clickOnStartGame(TypeOfMap typeOfMap) throws UnknownHostException, IOException, ClassNotFoundException {
		if(token.getTypeConnection()==TypeOfConnection.SOCKET) {
			SocketChooseGame socketChooseGame=new SocketChooseGame(port, host, token, view, typeOfMap);
			socketChooseGame.startClient();
		}
	}
	private void clickOnDoMove(DTOSend dtoSend, DTOGame dtoGame) throws UnknownHostException, IOException {
		if(token.getTypeConnection()==TypeOfConnection.SOCKET) {
			Thread action=new Thread(new SocketGame(port, host, token, view, dtoSend, dtoGame));
			action.start();
		}
	}
}
