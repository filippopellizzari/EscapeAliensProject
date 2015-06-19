package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import connection.*;
import controller.ActionType;
import dto.DTOGame;

/**
 * This class is used when a player chooses a game to play, sends a type of map, the server saves the request and
 * the response is sended when the timer of the game stop, if the game has at least 2 player the game start, otherwise
 * the response is negative and the player has to send another request.
 * @author Nicola
 *
 */

public class SocketChooseGame extends SocketBase implements Runnable{

	private TypeOfMap typeOfMapChoose;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	/**
	 * This costructor inizializes the input and output, uses object because transfer dto instead of strings
	 * @param clientData, to inizialize its parameters
	 * @param typeOfMap
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	
	public SocketChooseGame(ClientDataSocket clientData, TypeOfMap typeOfMap) throws UnknownHostException, IOException {
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
			String message;
			message=(String)in.readObject();
			System.out.println(message);
			message=(String)in.readObject();
			clientData.setBuffer(message);		//risposta server su partita
			if(message.contains("Partita pronta, Turno Giocatore 1")) {
				clientData.setView((ViewForPlayer)in.readObject()); //ecco la view
				DTOGame dtoGame=new DTOGame();
				do {
					System.out.println("Aspetto messaggi dal Pub-Sub");
					dtoGame=(DTOGame)in.readObject();
					System.out.println("Messaggio arrivato");
					clientData.setDtoGameList(dtoGame);
				}while(dtoGame.getActionType()!=ActionType.ENDGAME);
			}
			in.close();	//close all the resource
			out.close();
			socket.close();
		}catch (IOException | ClassNotFoundException e) {
				System.err.println("ImpallatoSocketChooseGame");
		} 
	}
}