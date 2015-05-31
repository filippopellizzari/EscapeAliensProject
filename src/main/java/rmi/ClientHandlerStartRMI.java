package rmi;

import java.util.List;

import connection.*;

public class ClientHandlerStartRMI extends RmiHandler implements Runnable, GetGameList, GetToken{
	private IdentifyTypeOfConnection tokens;
	private List<GameMap> gameMaps;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<GameDescription> getListOfGame() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}
}
