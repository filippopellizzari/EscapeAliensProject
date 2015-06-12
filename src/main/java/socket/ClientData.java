package socket;

import java.io.IOException;
import java.net.UnknownHostException;

import model.Coordinate;
import connection.*;
import controller.ActionType;
import dto.*;

public class ClientData {
	private Token token;
	private ViewForPlayer view;
	private DTOGame dtoGame;
	private String buffer; // risposte del server (Partita pronta,
							// iscrizione...)

	public ClientData() {
		this.token = new Token(-1);
	}

	public void clickOnConnectionSocket() throws UnknownHostException,
			IOException, ClassNotFoundException {
		Thread starSocket = new Thread(new SocketStart(this));
		starSocket.start();
	}

	public void clickOnStartGame(TypeOfMap typeOfMap)
			throws UnknownHostException, IOException, ClassNotFoundException {
		Thread choose = new Thread(new SocketChooseGame(this, typeOfMap));
		choose.start();
	}

	public void clickOnDoMove(DTOSend dtoSend) throws UnknownHostException,
			IOException {
		Thread action = new Thread(new SocketGame(this, dtoSend));
		action.start();
	}

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}

	/**
	 * @return the view
	 */
	public ViewForPlayer getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(ViewForPlayer view) {
		this.view = view;
	}

	/**
	 * @return the dtoGame
	 */
	public DTOGame getDtoGame() {
		return dtoGame;
	}

	/**
	 * @param dtoGame
	 *            the dtoGame to set
	 */
	public void setDtoGame(DTOGame dtoGame) {
		this.dtoGame = dtoGame;
	}

	/**
	 * @return the buffer
	 */
	public String getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer
	 *            the buffer to set
	 */
	public void setBuffer(String buffer) {
		this.buffer = buffer;
	}

	public static void main(String[] args) {
		try {
			ClientData cd = new ClientData();
			System.out.println(cd.getToken().getNumber());
			cd.clickOnConnectionSocket();
			Thread.sleep(2000);
			System.out.println(cd.getToken().getNumber());
			cd.clickOnStartGame(new TypeOfMap(MapName.Fermi, MapType.HEXAGONAL));
			Thread.sleep(40000);
			DTOSend dtoSend = new DTOSend(new Coordinate(12, 123) , cd.getView().getNumberPlayer(), null, ActionType.MOVE, null);
			cd.clickOnDoMove(dtoSend);
			Thread.sleep(10000);
			System.out.println(cd.dtoGame.getGameMessage());

		} catch (IOException | ClassNotFoundException | InterruptedException e1) {
			System.err.println("Errore in clientData");
		}
	}

}
