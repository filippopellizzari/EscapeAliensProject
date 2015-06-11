package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Coordinate;
import connection.MapName;
import connection.MapType;
import connection.TypeOfMap;
import controller.ActionType;
import dto.DTOGame;
import dto.DTOSend;
import rmi.ClientDataRMI;

/**
 * this class is a client who choosed connection RMI
 * 
 * @author filippopellizzari
 *
 */

public class ClientRMI {

	private ClientDataRMI cdr;

	public ClientRMI(ClientDataRMI cdr) {
		this.cdr = cdr;
	}

	public void play() throws UnknownHostException, ClassNotFoundException,
			IOException {
		Scanner in = new Scanner(System.in);
		System.out
				.println("Scegli la mappa di gioco:\n 1: Fermi\n 2: Galilei\n 3: Galvani\n");
		int mappa = in.nextInt();
		switch (mappa) {
		case 1:
			cdr.clickOnStartGame(
					new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL),
					cdr.getSetClientParameter());
			break;
		case 2:
			cdr.clickOnStartGame(new TypeOfMap(MapName.Galilei,
					MapType.HEXAGONAL), cdr.getSetClientParameter());
			break;
		case 3:
			cdr.clickOnStartGame(new TypeOfMap(MapName.Galvani,
					MapType.HEXAGONAL), cdr.getSetClientParameter());
			break;
		}

		while (true) {
			System.out.println("Fai un'azione:\n 1: Mossa\n 2: ");

			int azione = in.nextInt();
			switch (azione) {
			case 1:
				System.out
						.println("Inserisci le coordinate di destinazione:\n Lettera:\n");
				char lettera = in.next().charAt(0);
				int x = (int) lettera - 96; // converto char in intero
				System.out.println("Numero:\n");
				int y = in.nextInt();
				Coordinate coord = new Coordinate(x, y);
				DTOSend dtoSend = new DTOSend(coord, cdr.getView()
						.getNumberPlayer(), null, ActionType.MOVE, null);
				cdr.clickOnDoMove(dtoSend, cdr.getSetClientParameter());

			}

		}

	}

}
