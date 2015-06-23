package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import connection.ClientData;
import connection.MapName;
import connection.MapType;
import connection.TypeOfMap;
import rmi.ClientDataRMI;
import socket.ClientDataSocket;

/**
 * this class starts Client and the user can choose the connection type (RMI or
 * Socket)
 * 
 * @author Filippo
 *
 */
public class Client {

	private static ClientData cd;
	private static Scanner in;

	/**
	 * user can choose connection (RMI or Socket)
	 * @param args
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws NotBoundException
	 * @throws AlreadyBoundException
	 */
	public static void main(String[] args) throws UnknownHostException,
			ClassNotFoundException, IOException, InterruptedException,
			NotBoundException, AlreadyBoundException {
		in = new Scanner(System.in);
		String resultChooseMap;
		chooseConnection();
		
		do {
			chooseMap();
			resultChooseMap = cd.getBuffer();
			System.out.println(resultChooseMap);
		}while(resultChooseMap.contains("Tempo Scaduto e 1 solo giocatore, partita annullata"));
		
		System.out.println("NumeroGiocatore: "+cd.getView().getNumberPlayer());
		System.out.println("TipoGiocatore: "+cd.getView().getPlayerType());
		System.out.println("Casella: "+cd.getView().getCoordinate());
		Thread showMessage = new Thread(new ShowMessage(cd));
		showMessage.start();
		
		while(true){
		new ClientPlay(cd).play();
		}
	}

	private static void chooseConnection() throws NotBoundException,
			AlreadyBoundException, UnknownHostException,
			ClassNotFoundException, IOException, InterruptedException {
		System.out.println("Scegli la connessione:\n 1: SOCKET\n 2: RMI");
		int connessione = in.nextInt();
		switch (connessione) {
		case 1:
			cd = new ClientDataSocket();
			break;
		case 2:
			cd = new ClientDataRMI();
			break;
		}
		cd.clickOnConnection();
		Thread.sleep(2000);

	}

	private static void chooseMap() throws UnknownHostException,
			ClassNotFoundException, IOException {
		System.out.println("Scegli la mappa di gioco:\n 1: Fermi\n 2: Galilei\n 3: Galvani");
		int mappa = in.nextInt();
		switch (mappa) {
		case 1:
			cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
			break;
		case 2:
			cd.clickOnStartGame(new TypeOfMap(MapName.Galilei, MapType.HEXAGONAL));
			break;
		case 3:
			cd.clickOnStartGame(new TypeOfMap(MapName.Galvani, MapType.HEXAGONAL));
			break;
		}
	}
}
