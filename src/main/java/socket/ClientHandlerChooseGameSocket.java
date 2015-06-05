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
			Message message=new Message("Iscrizione Ricevuta");
			out.writeObject(message);
			out.flush();	//svuota buffer
			putInWait(detailsYourGame);
			message=new Message(detailsYourGame.getBuffer());
			out.writeObject(message);
			out.flush();
			if(message.getMessage()=="Preparazione partita in corso...") {
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(detailsYourGame.getGameId());	//numero partita
				int myNumber=detailsYourGame.takeNumberOfPlayer();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myNumber); //numero giocatore
				out.writeObject(detailsYourGame.getView(myNumber)); 	//manda la view al client
				out.flush();
				putInWait(detailsYourGame);
				message=new Message(detailsYourGame.getBuffer());
				out.writeObject(message);	//manda la risposta al client
				out.flush();
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private synchronized void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		System.out.println("Sono il thread connessione mi metto in wait");
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione mi sveglio dallo wait");
	}
}
