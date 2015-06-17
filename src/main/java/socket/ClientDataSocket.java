package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.*;
import controller.ActionType;
import dto.*;

public class ClientDataSocket extends ClientData{
	
	@Override
	public void clickOnConnection() throws UnknownHostException,
			IOException, ClassNotFoundException {
		Thread starSocket = new Thread(new SocketStart(this));
		starSocket.start();
	}

	@Override
	public void clickOnStartGame(TypeOfMap typeOfMap)
			throws UnknownHostException, IOException, ClassNotFoundException {
		Thread choose = new Thread(new SocketChooseGame(this, typeOfMap));
		choose.start();
	}

	@Override
	public void clickOnDoMove(DTOSend dtoSend) throws UnknownHostException,
			IOException {
		Thread action = new Thread(new SocketGame(this, dtoSend));
		action.start();
	}

	public static void main(String[] args) {
		try {
			ClientDataSocket cd = new ClientDataSocket();
			System.out.println(cd.getToken().getNumber());
			cd.clickOnConnection();
			Thread.sleep(2000);
			System.out.println(cd.getToken().getNumber());
			cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
			Thread.sleep(40000);
			DTOSend dtoSend=new DTOSend(null , 0, null, ActionType.CHAT, "ciao");
			cd.clickOnDoMove(dtoSend);
			Thread.sleep(10000);

		} catch (IOException | ClassNotFoundException | InterruptedException e1) {
			System.err.println("Errore in clientData");
		}
	}
}
