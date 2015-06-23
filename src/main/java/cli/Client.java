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
 * 
 * @author filippopellizzari
 *
 */
public class Client {

	private ClientData cd;
	private Scanner in;
	private boolean finePartita;


	public void startClient() throws UnknownHostException, ClassNotFoundException, NotBoundException, AlreadyBoundException, IOException, InterruptedException{
		in = new Scanner(System.in);
		String resultChooseMap;
		chooseConnection();

		do {
			chooseMap();
			resultChooseMap = cd.getBuffer();
			System.out.println(resultChooseMap);
		} while (resultChooseMap
				.contains("Tempo Scaduto e 1 solo giocatore, partita annullata"));

		System.out.println("NumeroGiocatore: " + cd.getView().getNumberPlayer());
		System.out.println("TipoGiocatore: " + cd.getView().getPlayerType());
		System.out.println("Casella: " + cd.getView().getCoordinate());

		Thread showMessage = new Thread(new ShowMessage(cd, this));
		showMessage.start();
		
		System.out.println("Per fare un'azione premi il numero corrispondente:\n 1: MOVE\n 2: ATTACK\n 3: USE ITEM CARD\n "
				+ "4: DISCARD ITEM CARD\n 5: DRAW SECTOR CARD\n 6: SELECT FOR NOISE IN ANY SECTOR\n "
				+ "7: END TURN\n 8: CHAT\n");	
		
		System.out.println("Se non ti ricordi le azioni, premi 9");
		
		do{
		int action = in.nextInt();
		if(action==9){
			System.out.println("Per fare un'azione premi il numero corrispondente:\n 1: MOVE\n 2: ATTACK\n 3: USE ITEM CARD\n "
					+ "4: DISCARD ITEM CARD\n 5: DRAW SECTOR CARD\n 6: SELECT FOR NOISE IN ANY SECTOR\n "
					+ "7: END TURN\n 8: CHAT\n");	
		}
		if(action > 0 && action < 9){
			new ClientPlay(cd).play(action, in);
		}
		}while(!isFinePartita());
		
	}
	
	private void chooseConnection() throws NotBoundException,
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

	private void chooseMap() {
		System.out
				.println("Scegli la mappa di gioco:\n 1: Fermi\n 2: Galilei\n 3: Galvani");
		int mappa = in.nextInt();
		switch (mappa) {
		case 1:
			try {
				cd.clickOnStartGame(new TypeOfMap(MapName.Fermi,
						MapType.HEXAGONAL));
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Non carica la mappa Fermi");
			}
			break;
		case 2:
			try {
				cd.clickOnStartGame(new TypeOfMap(MapName.Galilei,
						MapType.HEXAGONAL));
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Non carica la mappa Galilei");
			}
			break;
		case 3:
			try {
				cd.clickOnStartGame(new TypeOfMap(MapName.Galvani,
						MapType.HEXAGONAL));
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Non carica la mappa Galvani");
			}
			break;
		}
	}

	public boolean isFinePartita() {
		return finePartita;
	}

	public void setFinePartita(boolean finePartita) {
		this.finePartita = finePartita;
	}

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, NotBoundException, AlreadyBoundException, IOException, InterruptedException {
		Client client = new Client();
		client.startClient();	
	}

}
