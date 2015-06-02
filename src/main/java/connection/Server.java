package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import rmi.*;
import socket.*;

public class Server {
	private IdentifyTypeOfConnection identifyTypeOfConnection;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private Token token;
	boolean continueServer;
	public Server() {
		identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
		continueServer=true;
	}
	private final static int PORT = 27777;
	
	public void startServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server socket ready on port: " + PORT);
			System.out.println("Server ready");
			while (continueServer) {
				Socket socket = serverSocket.accept();
				token=(Token)inputStream.readObject();
				if(token.getNumber()==-1) {
					if(token.getTypeConnection()==TypeOfConnection.RMI) {
						Thread RMI=new Thread(new ClientHandlerStartRMI(token));
						RMI.start();
					}
					else {
						Thread Socket=new Thread(new ClientHandlerStartSocket(token));
						Socket.start();
					}
				}
				else {
					Identification identikit=identifyTypeOfConnection.getIdentification(token.getNumber());		//ora ho i suoi dati
					switch(identikit.getStatusClient()) {
						case LOADINGMAPS :
							outputStream.writeObject(new String("Non puoi fare altre operazioni mentre il server carica le mappe disponibili"));
						break;
						case WAITINGFORGAME :
							outputStream.writeObject(new String("Non si possono avviare più partite contemporaneamente aspetta il riscontro della tua"));
						break;
						case CHOOSEGAME :
							if(identikit.getTipeOfConnection()==TypeOfConnection.SOCKET) {
								Thread Socket=new Thread(new ClientHandlerChooseGameSocket(token));
								Socket.start();
							}
							else {
								Thread RMI=new Thread(new ClientHandlerChooseGameRmi(token));
								RMI.start();
							}
						break;
						case INGAME :
							if(identikit.getTipeOfConnection()==TypeOfConnection.SOCKET) {
								Thread Socket=new Thread(new ClientHandlerGameSocket(token));
								Socket.start();
							}
							else {
								Thread RMI=new Thread(new ClientHandlerGameRmi(token));
								RMI.start();
							}
						break;
					}
				}
				socket.close();
			}
			outputStream.close();
			inputStream.close();
			serverSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
}
