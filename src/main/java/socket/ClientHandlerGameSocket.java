package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.Broker;
import connection.GameDescription;
import connection.ListOfStartedGame;
import connection.Token;
import dto.*;

public class ClientHandlerGameSocket extends SocketHandler implements Runnable{
	
	private GameDescription gameDescription;
	private DTOSend dtoSend;
	public ClientHandlerGameSocket(Token token) throws UnknownHostException, IOException {
		super(token);
		this.dtoSend=dtoSend;
		ListOfStartedGame listOfStartedGame=ListOfStartedGame.getinstance();
		gameDescription=listOfStartedGame.getNumberGameDescription(token.getNumber());
	}
	@Override
	public void run() {
		try {
			doAnAction(dtoSend);
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.err.println(e.getMessage());
		}
	}
	public DTOGame doAnAction(DTOSend dtoSend) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		DTOGame dtoGame = gameDescription.getController().doAnAction(dtoSend);
		if(dtoGame.getDestination()==9) {
			//aggiungere la parte di invio al broker
		}
		return dtoGame;
	}
}
