package socket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.*;

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
	public void startClient() throws IOException {
		while (true) {
			outputStream.writeObject(token);	// sends the token to the server
			outputStream.flush();
			outputStream.writeObject(typeOfMapChoose);	//send the map
			outputStream.flush();
			String s=inputStream.readUTF();		//risposta server
			//notifica alla grafica che il server ha inviato il riscontro
			s=inputStream.readUTF();		//risposta server
			//notifica alla grafica che il server ha inviato il riscontro
			if(s=="Caricamento Gioco") {
				Thread subcriber=new Thread(new SubcriberThread());
				subcriber.start();
			}
			inputStream.close();	//close all the resource
			outputStream.close();
			socket.close();
		}
	}

}
