package connection;

import controller.GameController;

public class TemporizeThread implements Runnable {
	private GameController gameController;
	private int time;

	public TemporizeThread(GameController gameController) {
		this.gameController=gameController;
		this.time=150;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
