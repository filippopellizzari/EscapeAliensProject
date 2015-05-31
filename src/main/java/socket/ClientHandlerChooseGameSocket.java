package socket;

import connection.*;

public class ClientHandlerChooseGameSocket extends SocketHandler implements Runnable{
	private ViewForPlayer game1;
	ThreadCreateGame reference;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	public ViewForPlayer getView() {
		return game1;
	}
	public String subscribeGame(String game) {
		return null;	
	}
	public String resultConnection() {
		return null;		
	}
}
