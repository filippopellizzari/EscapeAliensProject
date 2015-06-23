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

public class ThreadCompleteTurn implements Runnable {

	GameDescription gameDescription;
	private int time;

	/**
	 * This constructor sets the timer and the game to cronometer
	 * @param gameDescription
	 */
	
	public ThreadCompleteTurn(GameDescription gameDescription) {
		this.gameDescription = gameDescription;
		this.time = 120;
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
				temporize = new Thread(new ThreadTemporizeTurn(time,gameDescription,turn,numberPlayer));
				temporize.start();
				gameDescription.getController().getChangeTurn(turn,	numberPlayer); // aspetta un'azione
				gameDescription.getStatus();
				if(turn == gameDescription.getController().getRound()&&
						numberPlayer==gameDescription.getController().getCurrentNumberPlayer()) {	//vedo se devo finire il turno
					try {
						list = gameDescription.getController().completeTurn();
						addMessageToPlayer(list);
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException e) {
					}
				}
				turn = gameDescription.getController().getRound(); //imposto il nuovo turno e giocatore
				numberPlayer = gameDescription.getController().getCurrentNumberPlayer();
				gameDescription.setStatus(); // libera il controller
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

	private void addMessageToPlayer(List<DTOGame> list) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getReceiver()==9) {		//serve per far pervenire l'azione anche a chi la fatta 
				//simulando la comunicazione client server
				DTOGame dtoGameSimulationClientServer=new DTOGame();
				for(int j=0;j<8;j++) {
					dtoGameSimulationClientServer.setCoordinate(list.get(i).getCoordinate(j), j);
					dtoGameSimulationClientServer.setPlayerType(list.get(i).getPlayerType(j), j);
				}
				dtoGameSimulationClientServer.setCoordinate(list.get(i).getCoordinate()[list.get(i).getPlayerNumber()], list.get(i).getPlayerNumber());
				dtoGameSimulationClientServer.setActionType(list.get(i).getActionType());
				dtoGameSimulationClientServer.setGameMessage(list.get(i).getGameMessage());
				dtoGameSimulationClientServer.setHatchCardColor(list.get(i).getHatchCardColor());
				dtoGameSimulationClientServer.setItemCardType(list.get(i).getItemCardType());
				dtoGameSimulationClientServer.setPlayerNumber(list.get(i).getPlayerNumber());
				dtoGameSimulationClientServer.setReceiver(list.get(i).getPlayerNumber());
				dtoGameSimulationClientServer.setSectorCardType(list.get(i).getSectorCardType());
				list.add(dtoGameSimulationClientServer);
			}
		}
		
	}
}
