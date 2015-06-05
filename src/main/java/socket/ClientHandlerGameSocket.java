package socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import connection.*;
import dto.*;

public class ClientHandlerGameSocket implements Processing{
	
	private GameDescription gameDescription;
	private DTOSend dtoSend;
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientHandlerGameSocket(Token token, ObjectOutputStream socketOut,
			ObjectInputStream socketIn) {
		super();
		this.token=token;
		this.out=socketOut;
		this.in=socketIn;
		this.dtoSend=dtoSend;
		ListOfStartedGame listOfStartedGame=ListOfStartedGame.getinstance();
		gameDescription=listOfStartedGame.getNumberGameDescription(token.getNumber());
	}
	@Override
	public void start() {
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
