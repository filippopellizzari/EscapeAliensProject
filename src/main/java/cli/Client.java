package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import connection.ClientData;

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

		cd = new ChooseConnection().choose(cd, in);// scegli RMI o Socket

		int next;
		do {
			// ciclo finchè una partita non è disponibile
			String resultChooseMap;
			do {
				new ChooseMap().choose(cd, in);
				System.out.println("Attendi partita disponibile...");
				resultChooseMap = cd.getBuffer();
				System.out.println(resultChooseMap);
			} while (resultChooseMap
					.contains("Tempo Scaduto e 1 solo giocatore, partita annullata"));

			// da qui la partita è creata
			System.out.println("Numero giocatore: "
					+ (cd.getView().getNumberPlayer() + 1));
			System.out.println("Tipo giocatore: "
					+ cd.getView().getPlayerType());
			System.out.println("Settore corrente: "
					+ cd.getView().getCoordinate());

			// modello del client(posizione corrente, carte oggetto)
			ClientModel model = new ClientModel();
			model.setCoordinate(cd.getView().getCoordinate());

			Thread showMessage = new Thread(new ShowMessage(cd, this, model));
			showMessage.start();

			// istruzioni generali utili al client
			System.out
					.println("Per fare un'azione premi il numero corrispondente:\n 1: MOVE\n 2: ATTACK\n 3: USE ITEM CARD\n "
							+ "4: DISCARD ITEM CARD\n 5: DRAW SECTOR CARD\n 6: SELECT FOR NOISE IN ANY SECTOR\n "
							+ "7: END TURN\n 8: CHAT\n");

			System.out.println("Per info, premi 9\n");

			// primo turno
			System.out.println("Round 1\n");
			System.out.println("Turno giocatore 1\n");

			// si possono inviare comandi fino alla fine della partita
			do {
				int action = in.nextInt();

				if (action == 9) {
					info(model);
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

	private void info(ClientModel model) throws InterruptedException {
		System.out.println("Numero giocatore: "
				+ (cd.getView().getNumberPlayer() + 1));
		System.out.println("Tipo giocatore: " + cd.getView().getPlayerType());
		System.out.println("Settore corrente: " + model.getCoordinate());
		System.out.println("Carte oggetto: " + model.getItems());
		System.out
				.println("Per fare un'azione premi il numero corrispondente:\n 1: MOVE\n 2: ATTACK\n 3: USE ITEM CARD\n "
						+ "4: DISCARD ITEM CARD\n 5: DRAW SECTOR CARD\n 6: SELECT FOR NOISE IN ANY SECTOR\n "
						+ "7: END TURN\n 8: CHAT\n");
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
