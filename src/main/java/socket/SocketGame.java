package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.Token;
import connection.ViewForPlayer;
import dto.*;

public class SocketGame extends SocketBase implements Runnable{
	
	DTOSend dtoToSend;
	DTOGame dtoGame;			//se viene aggiunto azione finita
	public SocketGame(int port, String host, Token token, ViewForPlayer view, 
			DTOSend dtoToSend, DTOGame dtoGame) throws UnknownHostException,
			IOException {
		super(port, host, token);
		this.dtoToSend=dtoToSend;
		this.dtoGame=dtoGame;
	}

	@Override
	public void run() {
		try {
			startClient();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void startClient() throws IOException, ClassNotFoundException {
		outputStream.writeObject(token);
		outputStream.flush();
		outputStream.writeObject(dtoToSend);
		outputStream.flush();
		try {
			while(dtoGame==null) putInLock();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		this.dtoGame=(DTOGame)inputStream.readObject();
		inputStream.close();	//close all the resource
		outputStream.close();
		socket.close();
	}

	private void putInLock() throws InterruptedException {
		this.wait();
	}

}
