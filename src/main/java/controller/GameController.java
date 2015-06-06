package controller;

import java.io.IOException;

import connection.ViewForPlayer;
import creator.GameCreator;
import dto.*;
import model.*;

/**
 * This class contains the status of game ad has a reference of the model,
 * invoke all the method to do every action and control this actions
 * 
 * @author Nicola
 *
 */

public class GameController {
	private Game game;
	private Turn currentTurn;
	private int turnNumber;
	private int currentNumberPlayer;

	/**
	 * 
	 * @param mapName
	 *            name of the map to create
	 * @param numberOfPlayers
	 *            number of players
	 * @param typeMap
	 *            type of map (standard is hexagonal)
	 * @throws NumberFormatException
	 * @throws IOException
	 */

	public GameController(String mapName, int numberOfPlayers, String typeMap)
			throws NumberFormatException, IOException {
		GameCreator gameCreator = GameCreator.getinstance();
		this.game = gameCreator.createGame(mapName, numberOfPlayers, typeMap);
		currentTurn = new Turn(game, game.getPlayers(currentNumberPlayer));
		this.currentNumberPlayer = 0;
		this.turnNumber = 1;
	}

	/**
	 * 
	 * @param dtoSend, a collection of data used to indicate the player's actions
	 * @return the report of action happen during the move
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */

	public synchronized DTOGame doAnAction(DTOSend dtoSend) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		DTOGame message = null;
		ControlDataRiceived control = new ControlDataRiceived(); 
		message.setGameMessage(control.verify(dtoSend, currentNumberPlayer, game));
		if(message.getGameMessage()==null){
			DTOTurn dtoTurn = new DTOTurn(dtoSend.getCoordinate(),
					dtoSend.getTypeCard(), dtoSend.getTypeOfAction());
			message = currentTurn.turn(dtoTurn); // messaggio di come è stata eseguita l'azione
			if (message.getGameMessage() == "Hai finito il turno")
				endTurn(message);
		}
		return message;
	}

	/**
	 * This method ends a turn and prepares the next turn for another player
	 * @param message 
	 */

	private DTOGame endTurn(DTOGame message) { // aggiorna il giocatore
		{
			if(game.getPlayers().length==currentNumberPlayer) turnNumber++;		//turno finito
			ControlEndGame controlEndGame = new ControlEndGame();
			if(controlEndGame.control(game, turnNumber)) message.setGameMessage("Partita conclusa");
			boolean nextPlayerDecide = false; // assegna correttamente il
												// prossimo turno
			do {
				currentNumberPlayer++;
				if (game.getPlayers().length == currentNumberPlayer) 
					currentNumberPlayer = 0; // giocatore a cui tocca
				if (game.getPlayers(currentNumberPlayer).isAlive())
					nextPlayerDecide = true;
			} while (!nextPlayerDecide);
			currentTurn = new Turn(game, game.getPlayers(currentNumberPlayer));
			message.setGameMessage("Turno giocatore: "+currentNumberPlayer);
			return message;
		}
	}

	/**
	 * This method invoked by an external thread finishes the turn
	 * @return 
	 */

	public DTOGame finishTurn() {
		CompleteTurn completeTurn = new CompleteTurn(currentTurn.getGameStatus());
		DTOGame message = completeTurn.completeTurn(); // completa il turno
		endTurn(message); // crea il prossimo turno
		return message;
	}
	
	public ViewForPlayer[] getViews() {
		ViewForPlayer[] views=new ViewForPlayer[game.getPlayers().length];
		for(int i=0;i<views.length;i++) {
			views[i]=new ViewForPlayer(game.getPlayers(i).getSector().getCoordinate(), game.getPlayers(i).getType(),i);
		}
		return views;
	}
}
