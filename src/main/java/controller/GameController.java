package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import connection.MapName;
import connection.MapType;
import connection.ViewForPlayer;
import creator.GameCreator;
import dto.*;
import model.*;

/**
 * This class contains the controller of the game turns and
 * controls the end of the game
 * 
 * @author Nicola
 *
 */

public class GameController {
	private Game game;
	private Turn currentTurn;
	private int round;
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
		this.currentNumberPlayer = 0;
		this.round = 1;
		currentTurn = new Turn(game, game.getPlayers(currentNumberPlayer));
		
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
		DTOGame dtoGame = new DTOGame();
		String error = new ControlDataReceived(dtoSend, game,currentNumberPlayer).verify(); 
		if(error != null){ //se ci sono errori sui dati ricevuti, notifico subito al client
			dtoGame.setGameMessage(error);
			return dtoGame;
		}
		DTOTurn dtoTurn = new DTOTurn(dtoSend.getCoordinate(),dtoSend.getItemCardType(), dtoSend.getActionType());
		dtoGame = currentTurn.turn(dtoTurn);
		if (dtoGame.getGameMessage() == "Hai finito il turno"){
			endTurn(dtoGame);
		}		
		return dtoGame;
	}

	/**
	 * This method ends a turn; prepares the next turn for another player,
	 * if the game is not finished
	 * 
	 * @param dtoGame 
	 */

	public synchronized void setChangeTurn() {
		this.notifyAll();	//notifica al thread che segue i giocatori che il turno è finito
	}
		
	private synchronized DTOGame endTurn(DTOGame dtoGame) { 
			boolean nextPlayerDecide = false; // assegna correttamente il
												// prossimo turno
			do {
				currentNumberPlayer++;
				if (game.getPlayers().length == currentNumberPlayer) {
					currentNumberPlayer = 0;
					round++; //quando si riparte dal primo giocatore, si va al round successivo
				}
				if (game.getPlayers(currentNumberPlayer).isAlive())
					nextPlayerDecide = true;
			} while (!nextPlayerDecide);
			
			if(new EndGame(game, round).control()){
				dtoGame.setGameMessage("Partita conclusa");
				return dtoGame;
			}
			currentTurn = new Turn(game, game.getPlayers(currentNumberPlayer));
			dtoGame.setGameMessage("Turno giocatore: "+currentNumberPlayer);
			return dtoGame;
		
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
		if(turnPreviousNumber!=round||playerPreviousNumber!=currentNumberPlayer)
			this.wait();
	}

	/**
	 * @return the turnNumber
	 */
	public int getTurnNumber() {
		return round;
	}

	/**
	 * @return the currentNumberPlayer
	 */
	public int getCurrentNumberPlayer() {
		return currentNumberPlayer;
	}
	
	
	
}
