package controller;

import java.util.Random;

import dto.*;
import model.*;

/**
 * This class complete a turn after the time of a player ends and he has not
 * finished the turn
 * 
 * @author Nicola
 *
 */

public class CompleteTurn {
	private GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param gameStatus, the status of a turn, reference at model and the player who
	 * are playing, now is his turn
	 */

	public CompleteTurn(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * 
	 * @return the action happen during the actions, this means that can be returned more than 1 action
	 */

	public DTOGame completeTurn() {
		ChooseAnAction actionToDo;
		Random random = new Random();
		int condizione; // se arriva a 4 vuol dire che il turno è finito
		do {
			condizione = 0;
			//obbligo di muovere
			if (!status.isMoved()) { 
				actionToDo = new Move(status);
				do {
					dtoGame = actionToDo.doAction(new DTOTurn(status
							.getPlayer().getSector().getAdjacent()
							.get(random.nextInt(6)), null, null));
				} while (dtoGame.getGameMessage() != "OK");
			} else
				condizione++;
			
			//obbligo di scartare
			if (status.isMustDiscardItem()) { 
				actionToDo = new DiscardItem(status);
				dtoGame = actionToDo.doAction(new DTOTurn(null, status
						.getPlayer().getItem().get(random.nextInt(4))
						.getType(), null));
			} else
				condizione++;
			
			//obbligo di pescare
			if (status.isMustDraw()) {
				actionToDo = new DrawSectorCard(status);
				dtoGame = actionToDo.doAction(new DTOTurn(null, null, null));
			} else
				condizione++;
			
			//obbligo di scegliere settore per noise any sector
			if (status.isMustNoise()) { 
				Coordinate coordRandom;
				do {
					coordRandom = new Coordinate(random.nextInt(22) + 1,
							random.nextInt(13) + 1); 
				} while (!status.getGame().getMap().isNull(coordRandom));
				actionToDo = new SelectSectorNoise(status);
				dtoGame = actionToDo.doAction(new DTOTurn(coordRandom,null, null)); 
			} else
				condizione++;
			
		} while (condizione <= 3);
		return dtoGame;
	}
}
