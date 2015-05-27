package controller;

import dto.DTOTurn;
import model.*;

/**
 * This class control a player action, if the player can do the action the
 * method changes the model and notifies the player/players
 * 
 * @author Nicola
 *
 */

public class Turn {

	GameStatus gameStatus;
	
	/**
	 * 
	 * @param game, reference at model
	 * @param player, reference at player that has to play
	 */
	
	public Turn(Game game, Player player) {
		this.gameStatus = new GameStatus(game, player);
	}

	/**
	 * 
	 * @param dtoTurn, collection of element used to explain an action
	 * @return the message with the response about the actions executed
	 */

	public String turn(DTOTurn dtoTurn) {
		String response = "";
		TryToDoAnAction actionToDo;

		switch (dtoTurn.getTypeOfAction()) {
		case MOVE:
			actionToDo = new Move(gameStatus);
			response = actionToDo.doAction(dtoTurn);
			break;
		case ATTACK:
			actionToDo = new Attack(gameStatus);
			response = actionToDo.doAction(dtoTurn);

			break;
		case USEITEM:
			actionToDo = new UseItem(gameStatus);
			response = actionToDo.doAction(dtoTurn);
			break;
		case DISCARD:
			actionToDo = new Discard(gameStatus);
			response = actionToDo.doAction(dtoTurn);
			break;
		case DRAWSECTORCARD:
			actionToDo = new Draw(gameStatus);
			response = actionToDo.doAction(dtoTurn);
			break;
		case ENDTURN:
			actionToDo = new EndTurn(gameStatus);
			response = actionToDo.doAction(dtoTurn);
			break;
		case SELECTSECTORFORNOISE:
			actionToDo = new SelectSectorNoise(gameStatus);
			response = actionToDo.doAction(dtoTurn);
			break;
		default:
			break;
		}
		return response;
	}

	/**
	 * @return the gameStatus
	 */

	public GameStatus getGameStatus() {
		return gameStatus;
	}
}
