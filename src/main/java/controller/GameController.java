package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import connection.ListOfStartedGame;
import connection.MapName;
import connection.MapType;
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
	 * @param mapName, name of the map to create
	 * @param numberOfPlayers, number of players
	 * @param mapType, type of map (standard is hexagonal)
	 * @throws NumberFormatException
	 * @throws IOException
	 */

	public GameController(MapName mapName, int numberOfPlayers, MapType mapType)
			throws NumberFormatException, IOException {
		GameCreator gameCreator = GameCreator.getinstance();
		this.game = gameCreator.createGame(mapName, numberOfPlayers, mapType);
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

	public DTOGame doAnAction(DTOSend dtoSend) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		DTOGame dtoGame=new DTOGame();
		ControlDataRiceived control = new ControlDataRiceived(); 
		dtoGame.setGameMessage(control.verify(dtoSend, currentNumberPlayer, game));
		if(dtoGame.getGameMessage()==null){
			DTOTurn dtoTurn = new DTOTurn(dtoSend.getCoordinate(),
					dtoSend.getTypeCard(), dtoSend.getTypeOfAction());
			dtoGame = currentTurn.turn(dtoTurn); // messaggio di come è stata eseguita l'azione
			if (dtoGame.getGameMessage() == "Hai finito il turno")
				endTurn(dtoGame);
		}
		return dtoGame;
	}

	/**
	 * This method ends a turn and prepares the next turn for another player
	 * @param message 
	 */

	private synchronized DTOGame endTurn(DTOGame message) { // aggiorna il giocatore
		{
			if(game.getPlayers().length==currentNumberPlayer) turnNumber++;		//turno finito
			ControlEndGame controlEndGame = new ControlEndGame();
			if(controlEndGame.control(game, turnNumber))
				message.setGameMessage("Partita conclusa");
			else {
				boolean nextPlayerDecide = false; // assegna correttamente il prossimo turno
				do {
					currentNumberPlayer++;
					if (game.getPlayers().length == currentNumberPlayer) 
						currentNumberPlayer = 0; // giocatore a cui tocca
					if (game.getPlayers(currentNumberPlayer).isAlive())
						nextPlayerDecide = true;
				} while (!nextPlayerDecide);
				setChangeTurn();
				currentTurn = new Turn(game, game.getPlayers(currentNumberPlayer));
				message.setGameMessage("Turno giocatore: "+currentNumberPlayer);
			}
			return message;
		}
	}

	public synchronized void setChangeTurn() {
		this.notifyAll();	//notifica al thread che segue i giocatori che il turno è finito
		
	}

	/**
	 * This method invoked by an external thread finishes the turn
	 * @return 
	 */

	public List<DTOGame> finishTurn() {
		CompleteTurn completeTurn = new CompleteTurn(currentTurn.getGameStatus());
		List<DTOGame> dtoGameList=new ArrayList<DTOGame>();
		dtoGameList = completeTurn.completeTurn(dtoGameList); // completa il turno
		dtoGameList.add(endTurn(new DTOGame())); // crea il prossimo turno oppure la partita finisce
		return dtoGameList;
	}
	
	public ViewForPlayer[] getViews() {
		ViewForPlayer[] views=new ViewForPlayer[game.getPlayers().length];
		for(int i=0;i<views.length;i++) {
			views[i]=new ViewForPlayer(game.getPlayers(i).getSector().getCoordinate(), game.getPlayers(i).getType(),i);
		}
		return views;
	}

	/**
	 * @return the turnNumber
	 * @throws InterruptedException 
	 */
	public synchronized void getChangeTurn(int turnPreviousNumber, int playerPreviousNumber) throws InterruptedException {
		if(turnPreviousNumber!=turnNumber||playerPreviousNumber!=currentNumberPlayer)
			this.wait();
	}

	/**
	 * @return the turnNumber
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/**
	 * @return the currentNumberPlayer
	 */
	public int getCurrentNumberPlayer() {
		return currentNumberPlayer;
	}
	
	
	
}
