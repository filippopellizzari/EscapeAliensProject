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
 * @author Filippo
 *
 */

public class Client {

	private ClientData cd;
	private Scanner in;
	private boolean finePartita;

	public void startClient() throws ClassNotFoundException, NotBoundException,
			AlreadyBoundException, InterruptedException, IOException {
		in = new Scanner(System.in);
		String resultChooseMap;
		chooseConnection();

		int next;
		do {

			do {
				chooseMap();
				System.out.println("Attendi partita disponibile...");
				resultChooseMap = cd.getBuffer();
				System.out.println(resultChooseMap);
			} while (resultChooseMap
					.contains("Tempo Scaduto e 1 solo giocatore, partita annullata"));

			System.out.println("Numero giocatore: "
					+ (cd.getView().getNumberPlayer() + 1));
			System.out.println("Tipo giocatore: "
					+ cd.getView().getPlayerType());
			System.out.println("Settore corrente: "
					+ cd.getView().getCoordinate());

			ClientModel model = new ClientModel();
			model.setCoordinate(cd.getView().getCoordinate());
			
			Thread showMessage = new Thread(new ShowMessage(cd, this, model));
			showMessage.start();

			System.out
					.println("Per fare un'azione premi il numero corrispondente:\n 1: MOVE\n 2: ATTACK\n 3: USE ITEM CARD\n "
							+ "4: DISCARD ITEM CARD\n 5: DRAW SECTOR CARD\n 6: SELECT FOR NOISE IN ANY SECTOR\n "
							+ "7: END TURN\n 8: CHAT\n");

			System.out.println("Per info, premi 9\n");

			System.out.println("Round 1\n");

			do {
				int action = in.nextInt();

				if (action == 9) {
					System.out.println("Numero giocatore: "
							+ (cd.getView().getNumberPlayer() + 1));
					System.out.println("Tipo giocatore: "
							+ cd.getView().getPlayerType());
					System.out.println("Settore corrente: "
							+ model.getCoordinate());
					System.out.println("Carte oggetto: "+model.getItems());
					System.out
							.println("Per fare un'azione premi il numero corrispondente:\n 1: MOVE\n 2: ATTACK\n 3: USE ITEM CARD\n "
									+ "4: DISCARD ITEM CARD\n 5: DRAW SECTOR CARD\n 6: SELECT FOR NOISE IN ANY SECTOR\n "
									+ "7: END TURN\n 8: CHAT\n");
				}
				if (action > 0 && action < 9) {
					new ClientPlay(cd).play(action, in);
				}
			} while (!isFinePartita());

			System.out.println("Premi 1 per nuova partita, 2 per uscire");

			do {
				next = in.nextInt();
			} while (next < 1 || next > 2);

		} while (next == 1);
		
		in.close();
	}

	private void chooseConnection() throws NotBoundException,
			AlreadyBoundException, ClassNotFoundException,
			InterruptedException, IOException {
		System.out.println("Scegli la connessione:\n 1: SOCKET\n 2: RMI");
		
		int connessione;
		do {
			connessione = in.nextInt();
		} while (connessione < 1 || connessione > 2);
		
		switch (connessione) {
		case 1:
			cd = new ClientDataSocket();
			break;
		case 2:
			cd = new ClientDataRMI();
			break;
		default:
			break;
		}
		cd.clickOnConnection();
		Thread.sleep(2000);

	}

	private void chooseMap() throws UnknownHostException,
			ClassNotFoundException, IOException {
		System.out
				.println("Scegli la mappa di gioco:\n 1: Fermi\n 2: Galilei\n 3: Galvani");
		
		int mappa;
		do {
			mappa = in.nextInt();
		} while (mappa < 1 || mappa > 3);
		
		switch (mappa) {
		case 1:
			cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
			break;
		case 2:
			cd.clickOnStartGame(new TypeOfMap(MapName.Galilei,
					MapType.HEXAGONAL));
			break;
		case 3:
			cd.clickOnStartGame(new TypeOfMap(MapName.Galvani,
					MapType.HEXAGONAL));
			break;
		default:
			break;
		}
	}

	public boolean isFinePartita() {
		return finePartita;
	}

	public void setFinePartita(boolean finePartita) {
		this.finePartita = finePartita;
	}

	public static void main(String[] args) throws UnknownHostException,
			ClassNotFoundException, NotBoundException, AlreadyBoundException,
			IOException, InterruptedException {
		Client client = new Client();
		client.startClient();
	}

}
