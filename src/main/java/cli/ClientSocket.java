package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Coordinate;
import connection.MapName;
import connection.MapType;
import connection.TypeOfMap;
import controller.ActionType;
import dto.DTOSend;
import socket.ClientData;
/**
 * this class is a client who choosed connection Socket
 * 
 * @author filippopellizzari
 *
 */
public class ClientSocket {
	
	
	private ClientData cd;
	private DTOSend dtoSend;
	/**
	 * 
	 * @param cd data 
	 */
	public ClientSocket(ClientData cd){
		this.cd = cd;
	}

	
	public void play() throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException{

		Scanner in = new Scanner(System.in);
		System.out.println("Scegli la mappa di gioco:\n 1: Fermi\n 2: Galilei\n 3: Galvani");
		int mappa = in.nextInt();
		switch(mappa){
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
		
		Thread.sleep(40000);
		
		while(true){
			System.out.println("Fai un'azione:\n 1: Mossa\n 2: Usa carta oggetto ");	
			
			int azione = in.nextInt();
			switch(azione){
			case 1:
				System.out.println("Inserisci le coordinate di destinazione:\n Lettera:\n");
				char lettera = in.next().charAt(0);
				int x = (int)lettera - 96; //converto char in intero
				System.out.println("Numero:\n");
				int y = in.nextInt();
				Coordinate coord = new Coordinate (x,y);
				dtoSend = new DTOSend(coord, cd.getView().getNumberPlayer(), null, ActionType.MOVE, null);
				cd.clickOnDoMove(dtoSend);
				break;
			case 2: 
				System.out.println("Scegli tipo:\n 1: ATTACK\n 2: TELEPORT\n 3: SEDATIVES\n 4: SPOTLIGHT\n 5: ADRENALINE");
				int type = in.nextInt();
				switch(type){
				case 1: 
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null, ActionType.ATTACK, null);
					cd.clickOnDoMove(dtoSend);
					break;
				default: 
					break;
				}
			default:
				break;
				
			}
			
			
		
		
		}
	}
		
		
		
		
		
		
		
	
	
	
	
	
}
