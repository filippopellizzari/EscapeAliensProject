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
		while(dtoGame==null)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.dtoGame=(DTOGame)inputStream.readObject();
		inputStream.close();	//close all the resource
		outputStream.close();
		socket.close();
	}

}
