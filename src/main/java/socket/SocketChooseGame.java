package socket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.*;
import dto.DTOSend;

public class SocketChooseGame extends SocketBase {

	private Scanner in;
	private ViewForPlayer view;
	private TypeOfMap typeOfMapChoose;
	public SocketChooseGame(int port, String host, Token token, ViewForPlayer view, TypeOfMap typeOfMap) throws UnknownHostException,
			IOException {
		super(port, host,token);
		this.view=view;
	}

	@Override
	public void startClient() throws IOException, ClassNotFoundException {
		while (true) {
			outputStream.writeObject(token);	// sends the token to the server
			outputStream.flush();
			outputStream.writeObject(typeOfMapChoose);	//send the map
			outputStream.flush();
			String s=inputStream.readUTF();		//risposta server
			//notifica alla grafica che il server ha inviato il riscontro
			if(s=="Preparazione partita in corso...") {
				Thread subcriber=new Thread(new SubcriberThread());		//parte il subscribe
				subcriber.start();
				Costrutto costrutto=(Costrutto)inputStream.readObject(); //ecco la view
				s=inputStream.readUTF();		//risposta server
			}
			inputStream.close();	//close all the resource
			outputStream.close();
			socket.close();
		}
	}

}
