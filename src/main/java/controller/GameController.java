package controller;

import java.io.IOException;

import creator.GameCreator;
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
	 *             name of the map to create
	 * @param numberOfPlayers
	 *             number of players
	 * @param typeMap
	 *             type of map (standard is hexagonal)
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
	 * @param dtoSend
	 *            , a collection of data used to indicate the player's actions
	 * @return the report of action happen during the move
	 */

	public String doAnAction(DTOSend dtoSend) {
		String message = "";
		ControlDataRiceived control = new ControlDataRiceived(); //controllo validità dati
		if (control.verify(dtoSend, currentNumberPlayer, game) == "OK") { 														
			DTOTurn dtoTurn = new DTOTurn(dtoSend.getCoordinate(),
					dtoSend.getTypeCard(), dtoSend.getTypeOfAction());
			message = currentTurn.turn(dtoTurn); // messaggio di come è stata eseguita l'azione
			if (message == "Hai finito il turno")
				endTurn();
		}
		return message;
	}

	/**
	 * This method ends a turn and prepares the next turn for another player
	 */

	private void endTurn() { // aggiorna il giocatore
		{
			ControlEndGame controlEndGame = new ControlEndGame(game, turnNumber); 
			turnNumber++; // turno finito
			boolean nextPlayerDecide = false; //assegna correttamente il prossimo turno
			do {
				currentNumberPlayer++;
				if (game.getPlayers().length == currentNumberPlayer)
					currentNumberPlayer = 0; // giocatore a cui tocca
				if (game.getPlayers(currentNumberPlayer).isAlive())
					nextPlayerDecide = true;
			} while (!nextPlayerDecide);
			currentTurn = new Turn(game, game.getPlayers(currentNumberPlayer));
		}
	}

	/**
	 * This method invoched by a extern thread finishes the turn
	 */

	public void finishTurn() {
		CompleteTurn completeTurn = new CompleteTurn(currentTurn.getGameStatus());
		String message = completeTurn.completeTurn(); // completa il turno
		endTurn(); // crea il prossimo turno
	}

	public static void main(String[] args) {
	}
}
