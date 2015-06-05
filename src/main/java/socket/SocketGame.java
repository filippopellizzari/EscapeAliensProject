package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import connection.Token;
import connection.ViewForPlayer;
import dto.*;

public class SocketGame extends SocketBase implements Runnable{
	
	DTOSend dtoToSend;
	DTOGame dtoGame;			//se viene aggiunto azione finita
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Token token;
	public SocketGame(Token token, DTOSend dtoToSend, DTOGame dtoGame) throws UnknownHostException, IOException {
		super();
		try {
			this.dtoToSend=dtoToSend;
			this.dtoGame=dtoGame;
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			this.token=token;
		} catch (IOException e) {
			System.out.print("Errore");
		}
	}

	@Override
	public void run() {
		try {
			out.writeObject(token);
			out.flush();
			out.writeObject(dtoToSend);
			out.flush();
			this.dtoGame=(DTOGame)in.readObject();
			in.close();	//close all the resource
			out.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("ImpallatoSocketGame");
		} 
	}

}
