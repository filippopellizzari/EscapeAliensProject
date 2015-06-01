package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.Token;

public class SocketGame extends SocketBase implements Runnable{

	public SocketGame(int port, String host, Token token) throws UnknownHostException,
			IOException {
		super(port, host, token);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startClient() {
		// TODO Auto-generated method stub
		
	}

}
