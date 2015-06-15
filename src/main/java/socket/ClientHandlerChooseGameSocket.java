package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import connection.*;
import dto.DTOGame;

/**
 * This class receives a type of map from the player, sends a new request for that type of map and wait, when 
 * the buffer is setted, if the response is positive take the view and sends these at the player, else terminates
 * @author Nicola
 *
 */

public class ClientHandlerChooseGameSocket implements Processing{
	
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private IdentifyTypeOfConnection identifyTypeOfConnection;		//serve per settare i dettagli del giocatore
	private final DatabaseCreateGame dataBaseForSubscribe;
	
	/**
	 * This costructor inizialize the input and output, used to read and send objects
	 * @param token, sended by a player
	 * @param socketOut, reads the output of the socket
	 * @param socketIn, reads the input of the socket 
	 */
	
	public ClientHandlerChooseGameSocket(Token token, ObjectOutputStream socketOut, ObjectInputStream socketIn) {
		this.token=token;
		this.out=socketOut;
		this.in=socketIn;
		this.dataBaseForSubscribe=DatabaseCreateGame.getinstance();		//accesso a thread che accetta le richieste
		this.identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
	}

	/**
	 * Send a requesta at the databaseForSubscribe, then wait for the response, if the response is positive sets the
	 * parameters of the current player, now the player has a game and a number, then send the view at the player
	 */
	
	public void start() {
		try {
			TypeOfMap chooseOfThePlayer=(TypeOfMap)in.readObject();
			DetailsPlayers detailsYourGame=dataBaseForSubscribe.subscribe(chooseOfThePlayer);		//hai i dettagli della partita in corso
			Message message=new Message("Iscrizione Ricevuta");
			out.writeObject(message);
			out.flush();	//svuota buffer
			putInWait(detailsYourGame);
			message=new Message(detailsYourGame.getBuffer());
			if(message.getMessage()=="Partita pronta, Turno Giocatore 1") {
				int numberGame=detailsYourGame.getGameId();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(numberGame);	//numero partita
				ViewForPlayer myView=detailsYourGame.getView();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myView.getNumberPlayer()); //numero giocatore
				out.writeObject(message);								//manda il messaggio
				out.flush();
				out.writeObject(myView); 	//manda la view al client
				out.flush();
				System.out.println("Scritto view");
				ListOfStartedGame list=ListOfStartedGame.getinstance();
				int numberOfPlayer=myView.getNumberPlayer();
				DTOGame dtoGame=new DTOGame();
				GameDescription description=list.getNumberGameDescription(numberGame);
				do {
					dtoGame=description.getBroker().getPlayersBuffer(numberOfPlayer).getBuffer();
					out.writeObject(dtoGame);
					out.flush();
				}while(dtoGame.getGameMessage()!="Partita conclusa");
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Puts in wait this thread if the buffer is empty
	 * @param detailsYourGame
	 * @throws InterruptedException
	 */

	private void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		System.out.println("Sono il thread connessione aspetto il buffer");
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione ricevuto il buffer");
	}
}
