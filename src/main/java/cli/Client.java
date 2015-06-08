package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import rmi.ClientDataRMI;
import socket.ClientData;

public class Client {
	

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException, NotBoundException{
		
		Scanner in = new Scanner(System.in);
		System.out.println("Scegli la connessione:\n 1: SOCKET\n 2: RMI\n");
		int connessione = in.nextInt();
		switch(connessione){
		case 1 : 
			System.out.println("ok, socket\n");
			ClientData cd=new ClientData();
			cd.clickOnConnectionSocket();
			Thread.sleep(2000);
			System.out.println("Assegnato token "+cd.getToken().getNumber()+"\n");
			new ClientSocket(cd).play();;
			break;
		case 2: 
			System.out.println("ok, RMI");
			ClientDataRMI cdr=new ClientDataRMI();
			cdr.clickOnConnectionRMI();
			Thread.sleep(2000);
			System.out.println("Assegnato token "+cdr.getToken().getNumber()+"\n");
			new ClientRMI(cdr).play();
			break;
		}
		in.close();	
	}

}
