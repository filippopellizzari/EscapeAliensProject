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
 * This class contains the controller of the game turns and controls the end of
 * the game
 * 
 * @author Filippo
 * @author Nicola
 *
 */

public class GameController {
	private Game game;
	private Turn currentTurn;
	private int round;
	private int currentNumberPlayer;
	private final int TOT_ROUNDS = 39;

	/**
	 * 
	 * @param mapName
	 *            , name of the map to create
	 * @param numberOfPlayers
	 *            , number of players
	 * @param mapType
	 *            , type of map (standard is hexagonal)
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
	 * @param dtoSend
	 *            a collection of data used to indicate the player's actions
	 * @return the report of action happen during the move
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */

	public DTOGame doAnAction(DTOSend dtoSend) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		DTOGame dtoGame = new DTOGame();
		String error = new ControlDataReceived(dtoSend, game,
				currentNumberPlayer).verify();
		// se ci sono errori sui dati ricevuti, notifico subito il client
		if (error != null) {
			dtoGame.setGameMessage(error);
			dtoGame.setReceiver(currentNumberPlayer);
			return dtoGame;
		}
		DTOTurn dtoTurn = new DTOTurn(dtoSend.getCoordinate(),
				dtoSend.getItemCardType(), dtoSend.getActionType());
		dtoGame = currentTurn.action(dtoTurn);
		if (dtoGame.getGameMessage() == "Hai finito il turno") {
			dtoGame = endTurn(dtoGame);
			dtoGame.setActionType(ActionType.ENDTURN);
		}
		return dtoGame;
	}

	public synchronized void setChangeTurn() {
		this.notifyAll(); // notifica al thread che segue i giocatori che il
							// turno è finito
	}

	/**
	 * This method is called at the end of each turn. First of all, it controls
	 * if the game is finished before 39 rounds: if yes, communicates how game
	 * is finished. If game is not ended, then next turn is prepared. If round
	 * is over, then starts a new round: if the new round is > 39, game is
	 * finished in favor of aliens.
	 * 
	 * @param dtoGame
	 * @return
	 */
	public synchronized DTOGame endTurn(DTOGame dtoGame) {

		String end = new EndGame(game).control();
		if (end != null) {
			disconnectAll();
			dtoGame.setGameMessage(end);
			dtoGame.setReceiver(9);
			return dtoGame;
		}

		boolean nextPlayerDecided = false;
		do {
			currentNumberPlayer++;
			if (game.getPlayers().length == currentNumberPlayer) {
				currentNumberPlayer = 0;
				round++; // quando si riparte dal primo giocatore,
				// si va al round successivo
			}
			if (game.getPlayers(currentNumberPlayer).isInGame())
				nextPlayerDecided = true;
		} while (!nextPlayerDecided);

		if (round <= TOT_ROUNDS) {
			currentTurn = new Turn(game, game.getPlayers(currentNumberPlayer));
			dtoGame.setGameMessage("Turno giocatore " + currentNumberPlayer);
		} else {
			disconnectAll();
			dtoGame.setGameMessage("Finiti i turni di gioco: gli alieni vincono");
		}

		dtoGame.setReceiver(9);
		return dtoGame;

	}

	private void disconnectAll() {
		for (int i = 0; i < game.getPlayers().length; i++) {
			game.getPlayers(i).setInGame(false);
		}
	}

	/**
	 * This method invoked by an external thread finishes the turn
	 * 
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */

	public List<DTOGame> completeTurn() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<DTOGame> dtoGameList = new ArrayList<DTOGame>();
		CompleteTurn completeTurn = new CompleteTurn(currentTurn);
		// completa il turno
		dtoGameList = completeTurn.completeTurn();
		// crea prossimo turno o finisci partita
		dtoGameList.add(endTurn(new DTOGame()));
		return dtoGameList;
	}

	/**
	 * This method is used at the beginning of a game. It gives client info
	 * about his initial position, his type and his number
	 * 
	 * @return array of views for player (each memory cell is for a particular
	 *         player in game)
	 */
	public ViewForPlayer[] getViews() {
		ViewForPlayer[] views = new ViewForPlayer[game.getPlayers().length];
		for (int i = 0; i < views.length; i++) {
			views[i] = new ViewForPlayer(game.getPlayers(i).getSector()
					.getCoordinate(), game.getPlayers(i).getType(), i);
		}
		return views;
	}

	/**
	 * @return the turnNumber
	 * @throws InterruptedException
	 */
	public synchronized void getChangeTurn(int turnPreviousNumber,
			int playerPreviousNumber) throws InterruptedException {
		if (turnPreviousNumber != round
				|| playerPreviousNumber != currentNumberPlayer)
			this.wait();
	}

	/**
	 * @return the round of game
	 */
	public int getRound() {
		return round;
	}

	/**
	 * @return the number of player who is playing the turn
	 */
	public int getCurrentNumberPlayer() {
		return currentNumberPlayer;
	}

	public void setCurrentTurn(Turn currentTurn) {
		this.currentTurn = currentTurn;
	}

	public Game getGame() {
		return game;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void setCurrentNumberPlayer(int currentNumberPlayer) {
		this.currentNumberPlayer = currentNumberPlayer;
	}

}
