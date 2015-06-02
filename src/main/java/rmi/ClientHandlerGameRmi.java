package rmi;

import connection.Broker;
import connection.Token;
import dto.*;

public class ClientHandlerGameRmi extends RmiHandler implements Runnable, DoAnAction {
	private Broker broker;
	public ClientHandlerGameRmi(Token token) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	@Override
	public DTOGame doAnAction(DTOSend dtoSend) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
