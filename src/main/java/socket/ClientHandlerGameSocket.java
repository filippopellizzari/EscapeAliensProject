package socket;

import connection.Broker;
import dto.*;

public class ClientHandlerGameSocket extends SocketHandler implements Runnable{
	private Broker broker;
	private DTOSend dtoSend;
	@Override
	public void run() {
	}
	public DTOGame doAnAction(DTOSend dtoSend) {
		return null;
	}
}
