package rmi;

import connection.*;

public class ClientHandlerChooseGameRmi extends RmiHandler implements Runnable, ConnectionResult, GetView{

	private ThreadCreateGame instance;
	@Override
	public String subscribeGame(String gameMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resultConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.RmiHandler#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

	@Override
	public ViewForPlayer getView(String MapName, String TypeMap) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
