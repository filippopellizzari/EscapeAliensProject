package gui;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import cli.ChooseConnection;
import cli.ChooseMap;
import connection.ClientData;

public class ClientGui {

	private ClientData cd;
	private Scanner in;

	public void startClientGui() throws UnknownHostException,
			ClassNotFoundException, NotBoundException, AlreadyBoundException,
			IOException, InterruptedException {

		in = new Scanner(System.in);
		
		cd = new ChooseConnection().choose(cd, in);// scegli RMI o Socket

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
		System.out.println("Tipo giocatore: " + cd.getView().getPlayerType());
		System.out.println("Settore corrente: " + cd.getView().getCoordinate());
		
		in.close();
		
		

	}

	public static void main(String[] args) throws UnknownHostException,
			ClassNotFoundException, NotBoundException, AlreadyBoundException,
			IOException, InterruptedException {
		ClientGui client = new ClientGui();
		client.startClientGui();
	}

}
