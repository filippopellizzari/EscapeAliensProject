package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import connection.ClientData;
import rmi.ClientDataRMI;
import socket.ClientDataSocket;

/**
 * This class is used to choose the type of connection: Socket or RMI.
 * It is used both by CLI users and by GUI users.
 * 
 * @author Filippo
 *
 */
public class ChooseConnection {

	/**
	 * 
	 * @param cd
	 * @param in
	 * @return a concrete type of ClientData (Socket or RMI)
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws NotBoundException
	 * @throws AlreadyBoundException
	 */
	public ClientData choose(ClientData cd, Scanner in)
			throws UnknownHostException, ClassNotFoundException, IOException,
			InterruptedException, NotBoundException, AlreadyBoundException {
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
		Thread.sleep(1000);

		return cd;
	}

}
