package connection;

import controller.GameController;

public class TemporizeThread implements Runnable {
	GameController gameController;

	public TemporizeThread(GameController gameController) {
		this.gameController=gameController;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
