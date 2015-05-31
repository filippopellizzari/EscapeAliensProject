package socket;

import java.util.List;

import connection.GameMap;
import connection.Token;

public class ClientHandlerStartSocket extends SocketHandler implements Runnable{
	private Token token;
	private List<GameMap> gamesMap;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}
	/**
	 * @return the gamesMap
	 */
	public List<GameMap> getGamesMap() {
		return gamesMap;
	}
	
	
}