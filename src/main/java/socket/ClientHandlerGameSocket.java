package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import connection.*;
import controller.GameController;
import dto.*;

public class ClientHandlerGameSocket implements Processing{

	private GameDescription gameDescription;
	private DTOSend dtoSend;
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientHandlerGameSocket(Token token, ObjectOutputStream socketOut,
			ObjectInputStream socketIn) {
		super();
		this.token=token;
		this.out=socketOut;
		this.in=socketIn;
		IdentifyTypeOfConnection identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
		Identification identification=identifyTypeOfConnection.getIdentification(token.getNumber());	//prendo l'identificatore del giocatore per avere il gioco
		ListOfStartedGame listOfStartedGame=ListOfStartedGame.getinstance();
		gameDescription=listOfStartedGame.getNumberGameDescription(identification.getNumberGame());		//prendo il gioco associato al giocatore
	}
	@Override
	public void start() {
		try {
			this.dtoSend=(DTOSend)in.readObject();				//ricevo i dati
			System.out.println("Azione dto: "+dtoSend.getTypeOfAction());
			DTOGame dtoGame=new DTOGame();
			putInWait();
			dtoGame=gameDescription.getController().doAnAction(dtoSend);
			gameDescription.setStatus();	//ho finito
			if(dtoGame.getDestination()==9) {
				//aggiungere la parte di invio al broker
			}
			out.writeObject((DTOGame)dtoGame);
			out.flush();
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void putInWait() throws InterruptedException {
		System.out.println("Sono il thread connessione mi metto in wait");
		gameDescription.getStatus();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione mi sveglio dallo wait");
	}
}
