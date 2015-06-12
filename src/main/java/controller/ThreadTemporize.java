package controller;

import connection.GameDescription;

public class ThreadTemporize implements Runnable{
	private int time;
	private GameDescription gameDescription;
	
	public ThreadTemporize(int time, GameDescription gameDescription) {
		this.gameDescription=gameDescription;
		this.time=time;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(time*1000);
			gameDescription.getStatus();		
			gameDescription.getController().setChangeTurn(); 	//notifico il cambio di turno
			gameDescription.setStatus();		//libera il controller
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
