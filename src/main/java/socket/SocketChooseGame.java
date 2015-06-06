package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import connection.*;

public class SocketChooseGame extends SocketBase implements Runnable{

	private TypeOfMap typeOfMapChoose;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public SocketChooseGame(ClientData clientData, TypeOfMap typeOfMap) throws UnknownHostException, IOException {
		super(clientData);
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			this.typeOfMapChoose=typeOfMap;
		} catch (IOException e) {
			System.out.print("Errore");
		}
	}

	@Override
	public void run() {
		try {
			out.writeObject(clientData.getToken());	// sends the token to the server
			out.flush();
			out.writeObject(typeOfMapChoose);	//send the map
			out.flush();
			Message message;
			message=(Message)in.readObject();
			System.out.println(message.getMessage());
			clientData.setBuffer(message.getMessage());		//risposta server ricezione richiesta
			message=(Message)in.readObject();
			System.out.println(message.getMessage());				//il primo carattere serve per capire la risposta
			clientData.setBuffer(message.getMessage());		//risposta server su partita
			if(message.getMessage()!="Tempo Scaduto e 1 solo giocatore, partita annullata") {
				Thread subcriber=new Thread(new SubcriberThread(clientData.getToken()));		//parte il subscribe
				subcriber.start();
				clientData.setView((ViewForPlayer)in.readObject()); //ecco la view
				System.out.println(clientData.getView().getNumberPlayer());
				System.out.println(clientData.getView().getCoordinate());
				System.out.println(clientData.getView().getPlayerType());
			}
			in.close();	//close all the resource
			out.close();
			socket.close();
		}catch (IOException | ClassNotFoundException e) {
				System.err.println("ImpallatoSocketChooseGame");
		} 
	}
}