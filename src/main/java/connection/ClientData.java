package connection;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import dto.*;

/**
 * This class contains all the data needed by client, the method to starts a game and calls the actions
 * @author Nicola
 *
 */

public abstract class ClientData {
	protected Token token;
	protected ViewForPlayer view;
	protected List<DTOGame> dtoGameList;
	protected List<String> buffer;
	
	/**
	 * Initialize a new Client Data
	 */

	public ClientData() {
		this.token = new Token(-1);
		this.dtoGameList = new ArrayList<DTOGame>();
		this.buffer = new ArrayList<String>();
	}

	public abstract void clickOnConnection() throws UnknownHostException,
			IOException, ClassNotFoundException;
	
	/**
	 * Begin a new game, the response can be ok or no if there is only 1 request and the game is aborted
	 * @param typeOfMap
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public abstract void clickOnStartGame(TypeOfMap typeOfMap)
			throws UnknownHostException, IOException, ClassNotFoundException;
	
	/**
	 * Do an action in the game
	 * @param dtoSend
	 * @throws UnknownHostException
	 * @throws IOException
	 */

	public abstract void clickOnDoMove(DTOSend dtoSend)
			throws UnknownHostException, IOException;

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}
	
	/**
	 * return dtoGame
	 * @return 
	 * @throws InterruptedException 
	 */
	
	public synchronized DTOGame getDtoGame() throws InterruptedException {
		while(dtoGameList.size()==0) 
			this.wait();
		return dtoGameList.remove(0);
	}

	/**
	 * @param dtoGameList
	 */

	public synchronized void setDtoGameList(DTOGame dtoGame) {
		this.dtoGameList.add(dtoGame);
		this.notifyAll();
	}

	/**
	 * @return the buffer
	 */

	public String getBuffer() {
		return buffer.remove(0);
	}

	/**
	 * @param buffer
	 */

	public void setBuffer(String buffer) {
		this.buffer.add(buffer);
	}

	/**
	 * @param token
	 */

	public void setToken(Token token) {
		this.token = token;
	}

	/**
	 * @param view
	 */

	public void setView(ViewForPlayer view) {
		this.view = view;
	}

	/**
	 * @return the view
	 */

	public ViewForPlayer getView() {
		return view;
	}
}
