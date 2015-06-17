package connection;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import dto.*;

public abstract class ClientData {
	protected Token token;
	protected ViewForPlayer view;
	protected List<DTOGame> dtoGameList;
	protected List<String> buffer;

	public ClientData() {
		this.token = new Token(-1);
		this.dtoGameList = new ArrayList<DTOGame>();
		this.buffer = new ArrayList<String>();
	}

	public abstract void clickOnConnection() throws UnknownHostException,
			IOException, ClassNotFoundException;

	public abstract void clickOnStartGame(TypeOfMap typeOfMap)
			throws UnknownHostException, IOException, ClassNotFoundException;

	public abstract void clickOnDoMove(DTOSend dtoSend)
			throws UnknownHostException, IOException;

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * @param dtoGameList
	 */

	public void setDtoGameList(DTOGame dtoGame) {
		this.dtoGameList.add(dtoGame);
	}

	/**
	 * @return the buffer
	 */

	public List<String> getBuffer() {
		return buffer;
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
