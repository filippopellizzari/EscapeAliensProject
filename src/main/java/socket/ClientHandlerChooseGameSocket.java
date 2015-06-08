package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		this.dataBaseForSubscribe=DatabaseCreateGame.getinstance();		//accesso a thread che accetta le richieste
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
			if(message.getMessage()=="Partita pronta, Turno Giocatore 1") {
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(detailsYourGame.getGameId());	//numero partita
				ViewForPlayer myView=detailsYourGame.getView();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myView.getNumberPlayer()); //numero giocatore
				out.writeObject(message);								//manda il messaggio
				out.flush();
				out.writeObject(myView); 	//manda la view al client
				out.flush();
				System.out.println("Scritto view");
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		System.out.println("Sono il thread connessione mi metto in wait");
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione mi sveglio dallo wait");
	}
}
