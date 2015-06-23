package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import connection.*;
import controller.ActionType;
import dto.DTOGame;

/**
 * This class receives a type of map from the player, sends a new request for that type of map and wait, when 
 * the buffer is setted, if the response is positive take the view and sends these at the player, then waits
 * for a new message from broken and sends it as publisher, else terminates
 * @author Nicola
 *
 */

public class ClientHandlerChooseGameSocket implements Processing{
	
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private DatabasePlayersIdentification identifyTypeOfConnection;		//serve per settare i dettagli del giocatore
	private final DatabaseInscriptionsForGames dataBaseForSubscribe;
	
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
		this.dataBaseForSubscribe=DatabaseInscriptionsForGames.getinstance();		//accesso a thread che accetta le richieste
		this.identifyTypeOfConnection=DatabasePlayersIdentification.getinstance();
	}

	/**
	 * Send a requesta at the databaseForSubscribe, then wait for the response, if the response is positive sets the
	 * parameters of the current player, now the player has a game and a number, then send the view at the player, 
	 * waits for a new dtoGame from the broker in the player's buffer, when a new message arrived sends it at the player
	 * as publisher
	 */
	@Override
	public void start() {
		try {
			TypeOfMap chooseOfThePlayer=(TypeOfMap)in.readObject();
			DetailsPlayers detailsYourGame=dataBaseForSubscribe.subscribe(chooseOfThePlayer);		//hai i dettagli della partita in corso
			String message="Iscrizione Ricevuta";
			out.writeObject(message);
			out.flush();	//svuota buffer
			putInWait(detailsYourGame);
			message=detailsYourGame.getBuffer();
			if(message.contains("Partita pronta, Turno Giocatore 1")) {
				int numberGame=detailsYourGame.getGameId();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(numberGame);	//numero partita
				ViewForPlayer myView=detailsYourGame.getView();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myView.getNumberPlayer()); //numero giocatore
				out.writeObject(message);								//manda il messaggio
				out.flush();
				out.writeObject(myView); 	//manda la view al client
				out.flush();
				ListOfStartedGame list=ListOfStartedGame.getinstance();
				int numberOfPlayer=myView.getNumberPlayer();
				DTOGame dtoGame=new DTOGame();
				GameDescription description=list.getNumberGameDescription(numberGame);
				do {
					dtoGame=description.getBroker().getPlayersBuffer(numberOfPlayer).getBuffer();
					out.writeObject(dtoGame);
					out.flush();
				}while(dtoGame.getActionType()!=ActionType.ENDGAME);
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(-1);	//numero partita
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(0);
			}
			else {
				out.writeObject(message);								//manda il messaggio
				out.flush();
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
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
	}
}
