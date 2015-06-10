package controller;

import java.util.ArrayList;
import java.util.List;

import connection.GameDescription;
import dto.DTOGame;

/**
 * This thread is used as a timer to complete the action after the player doesn't have more time and end the game, if player
 * finishes his turn before time, this thread is awakened and put to sleep again for another player
 * @author Nicola
 *
 */

public class ThreadTemporize implements Runnable{

	GameDescription gameDescription;
	private int time;

	public ThreadTemporize(GameDescription gameDescription) {
		this.gameDescription=gameDescription;
		this.time=150;
	}

	@Override
	public void run() {
		try {
			List<DTOGame> list=new ArrayList<DTOGame>();
			Thread.sleep(time*1000);
			gameDescription.getStatus();
			list=gameDescription.getController().finishTurn();	//finisce il turno
			while(list.size()>0) {
				gameDescription.getBroker().publish(list.remove(0));	//pubblica il primo dto
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
