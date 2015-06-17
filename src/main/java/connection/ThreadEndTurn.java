package connection;

import java.util.ArrayList;
import java.util.List;

import dto.DTOGame;

/**
 * This thread is used as a timer to complete the action after the player
 * doesn't have more time and end the game, if player finishes his turn before
 * time, this thread is awakened and put to sleep again for another player
 * 
 * @author Nicola
 *
 */

public class ThreadEndTurn implements Runnable {

	GameDescription gameDescription;
	private int time;

	/**
	 * This constructor sets the timer and the game to cronometer
	 * @param gameDescription
	 */
	
	public ThreadEndTurn(GameDescription gameDescription) {
		this.gameDescription = gameDescription;
		this.time = 150;
	}
	
	/**
	 * Until the end of the game this thread for each players' turn create a thread, wait until the game's turn changes then
	 * then if the turn is complete begins again, else completes the player's turn
	 */

	@Override
	public void run() {
		try {
			String message = null;
			Thread temporize;
			List<DTOGame> list = new ArrayList<DTOGame>();
			do {
				int turn = 0;
				int numberPlayer = 0;
				temporize = new Thread(new ThreadTemporize(time,gameDescription));
				temporize.start();
				gameDescription.getController().getChangeTurn(turn,
						numberPlayer); // finisce il turno
				if (temporize != null)
					temporize.stop(); // ferma timer
				gameDescription.getStatus();
				try {
					list = gameDescription.getController().completeTurn();
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException e) {
					System.err.print("Errore nel fine turno");
				}
				turn = gameDescription.getController().getRound(); //imposto il nuovo turno e giocatore
				numberPlayer = gameDescription.getController().getCurrentNumberPlayer();
				gameDescription.setStatus(); // libera il controller
				while (list.size() > 0) {
					message = list.get(0).getGameMessage();	//pubblica il primo dto
					gameDescription.getBroker().publish(list.remove(0)); 
				}
			} while (message != "Partita conclusa");

		} catch (InterruptedException e) {
			System.err.print("Errore nel fine turno");
		}
	}

}
