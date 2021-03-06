package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.*;
import dto.*;

/**
 * This class is the socket connection, invokes the method based on socket to receives token,
 * sends the game preference, does an action on a specified game
 * @author Nicola
 * @see Client Data
 */

public class ClientDataSocket extends ClientData{
	
	@Override
	public void clickOnConnection() throws UnknownHostException,
			IOException, ClassNotFoundException {
		Thread starSocket = new Thread(new SocketStart(this));
		starSocket.start();
	}

	@Override
	public void clickOnStartGame(TypeOfMap typeOfMap)
			throws IOException, ClassNotFoundException {
		Thread choose = new Thread(new SocketChooseGame(this, typeOfMap));
		choose.start();
	}

	@Override
	public void clickOnDoMove(DTOSend dtoSend) throws IOException {
		Thread action = new Thread(new SocketGame(this, dtoSend));
		action.start();
	}
}
