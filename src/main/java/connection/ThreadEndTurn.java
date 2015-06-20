package connection;

import java.util.ArrayList;
import java.util.List;

import controller.ActionType;
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
		this.time = 50;
	}
	
	/**
	 * Until the end of the game this thread for each players' turn create a thread, wait until the game's turn changes then
	 * then if the turn is complete begins again, else completes the player's turn
	 */

	@Override
	public void run() {
		try {
			boolean goOn=true;
			Thread temporize;
			List<DTOGame> list = new ArrayList<DTOGame>();
			int turn = 1;
			int numberPlayer = 0;
			do {
				temporize = new Thread(new ThreadTemporize(time,gameDescription,turn,numberPlayer));
				temporize.start();
				System.out.println("Sono il fine turno mi metto in attesa");
				gameDescription.getController().getChangeTurn(turn,	numberPlayer); // aspetta un'azione
				gameDescription.getStatus();
				if(turn == gameDescription.getController().getRound()&&
						numberPlayer==gameDescription.getController().getCurrentNumberPlayer()) {	//vedo se devo finire il turno
					try {
						System.out.println("completo il turno");
						list = gameDescription.getController().completeTurn();
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException e) {
						System.err.println("Errore nel fine turno");
					}
				}
				turn = gameDescription.getController().getRound(); //imposto il nuovo turno e giocatore
				numberPlayer = gameDescription.getController().getCurrentNumberPlayer();
				System.out.println("Sono il fine turno ho cambiato il turno");
				gameDescription.setStatus(); // libera il controller
				System.out.println("Lunghezza lista: "+list.size());
				while (list.size() > 0)
					gameDescription.getBroker().publish(list.remove(0));
				if(turn>=40) {
					goOn=false;
					DTOGame end= new DTOGame();
					end.setActionType(ActionType.ENDGAME);
					end.setReceiver(9);
					end.setPlayerNumber(9); 		//tutti lo devono ricevere
					end.setGameMessage("Fine gioco");
					gameDescription.getBroker().publish(end);
				}
			} while (goOn);

		} catch (InterruptedException e) {
			System.err.println("Errore nel fine turno");
		}
	}

}
