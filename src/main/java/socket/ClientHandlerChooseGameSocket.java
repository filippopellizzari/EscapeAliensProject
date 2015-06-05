package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import connection.*;

public class ClientHandlerChooseGameSocket implements Processing{
	
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private IdentifyTypeOfConnection identifyTypeOfConnection;
	private final DatabaseCreateGame dataBaseForSubscribe;
	
	public ClientHandlerChooseGameSocket(Token token, ObjectOutputStream socketOut, ObjectInputStream socketIn) {
		this.token=token;
		this.out=socketOut;
		this.in=socketIn;
		dataBaseForSubscribe=DatabaseCreateGame.getinstance();		//accesso a thread che accetta le richieste
		this.identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
	}

	@Override
	public void start() {
		try {
			TypeOfMap chooseOfThePlayer=(TypeOfMap)in.readObject();
			DetailsPlayers detailsYourGame=dataBaseForSubscribe.subscribe(chooseOfThePlayer);		//hai i dettagli della partita in corso
			out.writeObject("Iscrizione Ricevuta");
			out.flush();	//svuota buffer
			putInWait(detailsYourGame);
			out.writeObject(detailsYourGame.getBuffer());
			out.flush();
			if(detailsYourGame.getBuffer()=="Preparazione partita in corso...") {
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(detailsYourGame.getGameId());	//numero partita
				int myNumber=detailsYourGame.takeNumberOfPlayer();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myNumber); //numero giocatore
				out.writeObject(detailsYourGame.getView(myNumber)); 	//manda la view al client
				out.flush();
				putInWait2(detailsYourGame);
				out.writeObject(detailsYourGame.getBuffer()); 	//manda la risposta al client
				out.flush();
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private void putInWait2(DetailsPlayers detailsYourGame) throws InterruptedException {
		while(detailsYourGame.getBuffer().isEmpty()) 
			this.wait();
	}

	private synchronized void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		while(detailsYourGame.getBuffer()!="Iscrizione Ricevuta")
			this.wait();
	}
}
