package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import rmi.ClientDataRMI;
import socket.ClientDataSocket;
/**
 * this class starts Client and the user can choose the connection type (RMI or Socket)
 * 
 * @author filippopellizzari
 *
 */
public class Client {
	
/**
 * user can choose connection (RMI or Socket)
 * 
 * @param args
 * @throws UnknownHostException 
 * @throws ClassNotFoundException 
 * @throws IOException
 * @throws InterruptedException
 * @throws NotBoundException
 * @throws AlreadyBoundException 
 */
	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException, NotBoundException, AlreadyBoundException{
		
		Scanner in = new Scanner(System.in);
		System.out.println("Scegli la connessione:\n 1: SOCKET\n 2: RMI\n");
		int connessione = in.nextInt();
		switch(connessione){
		case 1 : 
			System.out.println("ok, socket\n");
			ClientDataSocket cd=new ClientDataSocket();
			cd.clickOnConnection();
			Thread.sleep(2000);
			System.out.println("Assegnato token "+cd.getToken().getNumber()+"\n");
			new ClientSocket(cd).play();;
			break;
		case 2: 
			System.out.println("ok, RMI");
			ClientDataRMI cdr=new ClientDataRMI();
			cdr.clickOnConnection();
			Thread.sleep(2000);
			System.out.println("Assegnato token "+cdr.getToken().getNumber()+"\n");
			new ClientRMI(cdr).play();
			break;
		}
		in.close();	
	}

}
