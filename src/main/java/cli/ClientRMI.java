package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Coordinate;
import connection.TypeOfMap;
import controller.TypeOfAction;
import dto.DTOGame;
import dto.DTOSend;
import rmi.ClientDataRMI;

public class ClientRMI {
	
	private ClientDataRMI cdr;
	
	public ClientRMI(ClientDataRMI cdr){
		this.cdr = cdr;
	}
	
	public void play() throws UnknownHostException, ClassNotFoundException, IOException{
		Scanner in = new Scanner(System.in);
		System.out.println("Scegli la mappa di gioco:\n 1: Fermi\n 2: Galilei\n 3: Galvani\n");
		int mappa = in.nextInt();
		switch(mappa){
		case 1:
			cdr.clickOnStartGame(new TypeOfMap("Fermi", "Hexagonal"));
			break;
		case 2:
			cdr.clickOnStartGame(new TypeOfMap("Galilei", "Hexagonal"));
			break;
		case 3:
			cdr.clickOnStartGame(new TypeOfMap("Galvani", "Hexagonal"));
			break;
		}
		
		while(true){
		System.out.println("Fai un'azione:\n 1: Mossa\n 2: ");	
		
		int azione = in.nextInt();
		switch(azione){
		case 1:
			System.out.println("Inserisci le coordinate di destinazione:\n Lettera:\n");
			int x = in.nextInt();//devo trasformare lettera in numero!
			System.out.println("Numero:\n");
			int y = in.nextInt();
			Coordinate coord = new Coordinate (x,y);
			DTOSend dtoSend = new DTOSend(coord, 1, null, TypeOfAction.MOVE, null);
			cdr.clickOnDoMove(dtoSend); //il DTO game??
			
			
		}
				
		}
		
		
		
		
	}
	

}
