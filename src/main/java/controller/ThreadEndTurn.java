package controller;

import java.util.ArrayList;
import java.util.List;

import connection.GameDescription;
import connection.StatusController;
import dto.DTOGame;

/**
 * This thread is used as a timer to complete the action after the player doesn't have more time and end the game, if player
 * finishes his turn before time, this thread is awakened and put to sleep again for another player
 * @author Nicola
 *
 */

public class ThreadEndTurn implements Runnable{

	GameDescription gameDescription;
	private int time;

	public ThreadEndTurn(GameDescription gameDescription) {
		this.gameDescription=gameDescription;
		this.time=150;
	}

	@Override
	public void run() {
		try {
			String message = null;
			Thread temporize;
			List<DTOGame> list=new ArrayList<DTOGame>();
			do{
				int turn=0;
				int numberPlayer=0;
				temporize=new Thread(new ThreadTemporize(time, gameDescription));
				temporize.start();
				gameDescription.getController().getChangeTurn(turn, numberPlayer);	//finisce il turno
				if(temporize!=null) temporize.stop();
				gameDescription.getStatus();		
				gameDescription.setStatus(StatusController.BUSY);	//prendo il controller
				list=gameDescription.getController().finishTurn();
				turn=gameDescription.getController().getTurnNumber();			//imposto il nuovo turno e giocatore
				numberPlayer=gameDescription.getController().getCurrentNumberPlayer();
				gameDescription.setStatus(StatusController.FREE);		//libera il controller
				while(list.size()>0) {
					message=list.get(0).getGameMessage();
					gameDescription.getBroker().publish(list.remove(0));	//pubblica il primo dto
				}
			}while(message!="Partita conclusa");
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
