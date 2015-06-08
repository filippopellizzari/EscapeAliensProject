package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import connection.Identification;
import connection.IdentifyTypeOfConnection;
import connection.Processing;
import connection.Token;
import socket.*;

public class ClientHandler implements Runnable{
	
	private IdentifyTypeOfConnection identifyTypeOfConnection;
	private Socket socket;
	
	public ClientHandler(Socket socket){
		this.socket = socket;
		identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
	}
	
	public void run() {
		try {
			ObjectOutputStream socketOut=new ObjectOutputStream(socket.getOutputStream());
			socketOut.flush();
			ObjectInputStream socketIn=new ObjectInputStream(socket.getInputStream());
			Token token = (Token)socketIn.readObject();
			Processing processing = null;
			if(token.getNumber()==-1) {
				processing=new ClientHandlerStartSocket(token,socketOut,socketIn);
				processing.start();
			}
			else {
				Identification identify=identifyTypeOfConnection.getIdentification(token.getNumber());
				if(identify.getNumberGame()==-1) {
					processing=new ClientHandlerChooseGameSocket(token,socketOut,socketIn);
					processing.start();
				}
				else {
					processing=new ClientHandlerGameSocket(token,socketOut,socketIn);
					processing.start();
				}
			}
			socketIn.close();
			socketOut.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
}
