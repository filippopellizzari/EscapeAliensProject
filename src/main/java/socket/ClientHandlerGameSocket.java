package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import connection.*;
import controller.ActionType;
import dto.*;

/**
 * This class receives a dtoSend from the player, referring at the player's token take the correct game and 
 * does an action in the game's controller, then return the result, if the result is public saves this in the broker
 * @author Nicola
 *
 */

public class ClientHandlerGameSocket implements Processing{

	private int numberPlayer;
	private GameDescription gameDescription;
	private DTOSend dtoSend;
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	/**
	 * This costructor inizialize the input and output, used to read and send objects
	 * @param token, sended by a player
	 * @param socketOut, reads the output of the socket
	 * @param socketIn, reads the input of the socket 
	 */
	
	public ClientHandlerGameSocket(Token token, ObjectOutputStream socketOut,
			ObjectInputStream socketIn) {
		super();
		this.token=token;
		this.out=socketOut;
		this.in=socketIn;
		DatabasePlayersIdentification identifyTypeOfConnection=DatabasePlayersIdentification.getinstance();
		PlayerIdentification identification=identifyTypeOfConnection.getIdentification(token.getNumber());	//prendo l'identificatore del giocatore per avere il gioco
		ListOfStartedGame listOfStartedGame=ListOfStartedGame.getinstance();
		gameDescription=listOfStartedGame.getNumberGameDescription(identification.getNumberGame());		//prendo il gioco associato al giocatore
		this.numberPlayer=identification.getNumberPlayer();
	}
	
	/**
	 * This method receives a dtoSend, if the type of action's of the dto is chat, saves this to the broker, else
	 * calls the controller of the correct game, the controller returns a object, if the object is public is saves
	 * in the broker then send to the player
	 */
	@Override
	public void start() {
		try {
			this.dtoSend=(DTOSend)in.readObject();				//ricevo i dati
			dtoSend.setNumberPlayer(numberPlayer);
			DTOGame dtoGame=new DTOGame();			//giocatore che manda il messaggio
			if(dtoSend.getActionType()==ActionType.CHAT) {
				dtoGame.setReceiver(9);
				dtoGame.setActionType(ActionType.CHAT);
				dtoGame.setChat(dtoSend.getChat());
				dtoGame.setPlayerNumber(numberPlayer); 
			}
			else {
				putInWait();
				dtoGame=gameDescription.getController().doAnAction(dtoSend);
				gameDescription.setStatus();	//ho finito
			}
			if(dtoGame.getReceiver()==9 || dtoGame.getReceiver()==10) {		//usa il publisher
				gameDescription.getBroker().publish(dtoGame);
			}
			out.writeObject((DTOGame)dtoGame);
			out.flush();
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Puts in wait this thread if the controller is busy
	 * @throws InterruptedException
	 */
	
	private void putInWait() throws InterruptedException {
		gameDescription.getStatus();
	}
}
