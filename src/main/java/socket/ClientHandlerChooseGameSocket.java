package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.*;

public class ClientHandlerChooseGameSocket extends SocketHandler implements Runnable{
	
	private IdentifyTypeOfConnection identifyTypeOfConnection;
	private final DatabaseCreateGame dataBaseForSubscribe;
	
	public ClientHandlerChooseGameSocket(Token token) throws UnknownHostException,
			IOException {
		super(token);
		dataBaseForSubscribe=DatabaseCreateGame.getinstance();		//accesso a thread che accetta le richieste
		this.identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
	}
	
	@Override
	public void run() {
		try {
			TypeOfMap chooseOfThePlayer=(TypeOfMap)inputStream.readObject();
			DetailsPlayers detailsYourGame=dataBaseForSubscribe.subscribe(chooseOfThePlayer);		//hai i dettagli della partita in corso
			identifyTypeOfConnection.getIdentification(token.getNumber()).setStatusClient(StatusClient.WAITINGFORGAME);		//mi sono iscritto e cambio stato
			outputStream.writeObject("Iscrizione Ricevuta");
			outputStream.flush();	//svuota buffer
			getBuffer(detailsYourGame);
			while(detailsYourGame.getBuffer().isEmpty()) this.wait();
			outputStream.writeObject(detailsYourGame.getBuffer());
			outputStream.flush();
			if(detailsYourGame.getBuffer()=="Preparazione partita in corso...") {
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(detailsYourGame.getGameId());	//numero partita
				identifyTypeOfConnection.getIdentification(token.getNumber()).setStatusClient(StatusClient.INGAME);		//status
				int myNumber=detailsYourGame.takeNumberOfPlayer();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myNumber); //numero giocatore
				outputStream.writeObject(detailsYourGame.getView(myNumber)); 	//manda la view al client
				outputStream.flush();
				while(detailsYourGame.getBuffer().isEmpty()) this.wait();
				outputStream.writeObject(detailsYourGame.getBuffer()); 	//manda la risposta al client
				outputStream.flush();
			}
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private void getBuffer(DetailsPlayers detailsYourGame) throws InterruptedException, IOException {
		
	}
}
