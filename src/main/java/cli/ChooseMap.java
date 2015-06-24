package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.ClientData;
import connection.MapName;
import connection.MapType;
import connection.TypeOfMap;

public class ChooseMap {

	public void choose(ClientData cd, Scanner in) throws UnknownHostException, ClassNotFoundException, IOException {
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
}
