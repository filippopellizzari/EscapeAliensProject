package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.*;

public class ClientHandlerChooseGameSocket extends SocketHandler implements Runnable{
	
	private IdentifyTypeOfConnection identifyTypeOfConnection;
	private final ThreadCreateGame threadForSubscribe;
	private String buffer;
	private Costrutto costrutto;
	
	public ClientHandlerChooseGameSocket(Token token) throws UnknownHostException,
			IOException {
		super(token);
		threadForSubscribe=ThreadCreateGame.getinstance();		//accesso a thread che accetta le richieste
		this.identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
	}
	
	@Override
	public void run() {
		try {
			TypeOfMap chooseOfThePlayer=(TypeOfMap)inputStream.readObject();
			threadForSubscribe.subscribe(chooseOfThePlayer,this);
			while(buffer.isEmpty()) wait();
			outputStream.writeObject(buffer);
			outputStream.flush();
			buffer=null;						//svuota buffer
			while(buffer.isEmpty()) wait();
			outputStream.writeObject(buffer);
			outputStream.flush();
			buffer=null;
			if(buffer=="Preparazione partita in corso...") {
				while(costrutto==null) wait();	//elaboro il costrutto
				identifyTypeOfConnection.getIdentification().get(token.getNumber()).setNumberGame(costrutto.getNumberGame);	//numero partita
				identifyTypeOfConnection.getIdentification().get(token.getNumber()).setNumberPlayer(costrutto.getNumberPlayer); //numero giocatore
				identifyTypeOfConnection.getIdentification().get(token.getNumber()).setStatusClient(StatusClient.INGAME);	//status
				//crea un nuovo oggetto da mandare al client
				outputStream.writeObject(costrutto); 	//manda la view al client
				outputStream.flush();
				while(buffer==null) wait();
				outputStream.writeObject(buffer); 	//manda la risposta al client
				outputStream.flush();
			}
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	/**
	 * @param costrutto the costrutto to set
	 */
	public void setCostrutto(Costrutto costrutto) {
		this.costrutto = costrutto;
	}
}
