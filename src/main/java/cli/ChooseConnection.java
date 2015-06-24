package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import connection.ClientData;
import rmi.ClientDataRMI;
import socket.ClientDataSocket;

public class ChooseConnection {

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
