package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.Token;
import dto.*;

public class SocketGame extends SocketBase implements Runnable{
	
	DTOSend dtoToSend;
	DTOGame dtoGame;
	public SocketGame(int port, String host, Token token, DTOSend dtoToSend) throws UnknownHostException,
			IOException {
		super(port, host, token);
		this.dtoToSend=dtoToSend;
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
		this.dtoGame=(DTOGame)inputStream.readObject();
	}

}
