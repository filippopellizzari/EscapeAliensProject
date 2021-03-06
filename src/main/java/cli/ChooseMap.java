package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.ClientData;
import connection.MapName;
import connection.MapType;
import connection.TypeOfMap;

/**
 * This class is used to choose the Map of the game, before subscribing. It is
 * used both by CLI users and by GUI users
 * 
 * @author Filippo
 *
 */
public class ChooseMap {
	/**
	 * 
	 * @param cd
	 * @param in
	 * @return
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String choose(ClientData cd, Scanner in)
			throws UnknownHostException, ClassNotFoundException, IOException {
		System.out
				.println("Scegli la mappa di gioco:\n 1: Fermi\n 2: Galilei\n 3: Galvani");

		String mapName = null;
		int mappa;
		do {
			mappa = in.nextInt();
		} while (mappa < 1 || mappa > 3);

		switch (mappa) {
		case 1:
			cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
			mapName = "Fermi";
			break;
		case 2:
			cd.clickOnStartGame(new TypeOfMap(MapName.Galilei,
					MapType.HEXAGONAL));
			mapName = "Galilei";
			break;
		case 3:
			cd.clickOnStartGame(new TypeOfMap(MapName.Galvani,
					MapType.HEXAGONAL));
			mapName = "Galvani";
			break;
		default:
			break;
		}

		return mapName;
	}
}
