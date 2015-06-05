package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.*;

public class SocketChooseGame extends SocketBase implements Runnable{

	private Scanner output;
	private ViewForPlayer view;
	private TypeOfMap typeOfMapChoose;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Token token;
	
	public SocketChooseGame(Token token, ViewForPlayer view, TypeOfMap typeOfMap) throws UnknownHostException, IOException {
		super();
		try {
			this.view=view;
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			this.token=token;
		} catch (IOException e) {
			System.out.print("Errore");
		}
	}

	@Override
	public void run() {
		try {
			out.writeObject(token);	// sends the token to the server
			out.flush();
			out.writeObject(typeOfMapChoose);	//send the map
			out.flush();
			String s=(String) in.readObject();		//risposta server
			//notifica alla grafica che il server ha inviato il riscontro
			s=(String) in.readObject();		//risposta server
			//notifica alla grafica che il server ha inviato il riscontro
			if(s=="Preparazione partita in corso...") {
				Thread subcriber=new Thread(new SubcriberThread(token));		//parte il subscribe
				subcriber.start();
				view=(ViewForPlayer)in.readObject(); //ecco la view
				//visualizza la view
				s=in.readUTF();		//risposta server
				//notifica alla grafica che il server ha inviato il riscontro
			}
			in.close();	//close all the resource
			out.close();
			socket.close();
		}catch (IOException | ClassNotFoundException e) {
				System.err.println("ImpallatoSocketChooseGame");
		} 
	}
}