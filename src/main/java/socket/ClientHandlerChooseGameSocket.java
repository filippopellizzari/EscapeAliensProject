package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.*;

public class ClientHandlerChooseGameSocket extends SocketHandler implements Runnable{
	
	private GameAvailable gameAvailable;
	private Token token;
	private final ThreadCreateGame threadForSubscribe;
	private String buffer;
	
	public ClientHandlerChooseGameSocket(Token token) throws UnknownHostException,
			IOException {
		super();
		threadForSubscribe=ThreadCreateGame.getinstance();		//accesso a thread che accetta le richieste
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
			if(buffer=="Preparazione partita in corso...") {
				buffer=null;
				while(buffer==null) wait();
				outputStream.writeObject(buffer); 	//manda la view al client
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
}
