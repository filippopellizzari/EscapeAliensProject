package controller;

import dto.*;
import model.*;

/**
 * This class control a player action, if the player can do the action the
 * method changes the model and notifies the player/players
 * 
 * @author Nicola
 *
 */

public class Turn {

	private GameStatus status;

	/**
	 * 
	 * @param game
	 *            reference at model
	 * @param player
	 *            reference at player who has to play
	 */

	public Turn(Game game, Player player) {
		this.status = new GameStatus(game, player);
	}

	/**
	 * 
	 * @param dtoTurn
	 *            , collection of element used to explain an action
	 * @return the message with the response about the actions executed
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */

	public DTOGame turn(DTOTurn dtoTurn) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		DTOGame response = new DTOGame();
		ChooseAnAction actionToDo;
		switch (dtoTurn.getActionType()) {
		case MOVE:
			actionToDo = new Move(status);
			response = actionToDo.doAction(dtoTurn);
			break;
		case ATTACK:
			actionToDo = new Attack(status);
			response = actionToDo.doAction(dtoTurn);
			break;
		case USEITEM:
			actionToDo = new UseItem(status);
			response = actionToDo.doAction(dtoTurn);
			break;
		case DISCARDITEM:
			actionToDo = new DiscardItem(status);
			response = actionToDo.doAction(dtoTurn);
			break;
		case DRAWSECTORCARD:
			actionToDo = new DrawSectorCard(status);
			response = actionToDo.doAction(dtoTurn);
			break;
		case SELECTSECTORNOISE:
			actionToDo = new SelectSectorNoise(status);
			response = actionToDo.doAction(dtoTurn);
			break;
		case ENDTURN:
			actionToDo = new EndTurn(status);
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
		return status;
	}
}
