package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.Broker;
import dto.*;

public class ClientHandlerGameSocket extends SocketHandler implements Runnable{
	public ClientHandlerGameSocket() throws UnknownHostException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	private Broker broker;
	private DTOSend dtoSend;
	@Override
	public void run() {
	}
	public DTOGame doAnAction(DTOSend dtoSend) {
		return null;
	}
}
