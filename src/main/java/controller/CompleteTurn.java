package controller;

import java.util.List;
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
	private GameStatus gameStatus;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param gameStatus
	 *            , the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public CompleteTurn(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * 
	 * @param dtoGameList
	 * @return the action happen during the actions, this means that can be
	 *         returned more than 1 action
	 */

	public List<DTOGame> completeTurn(List<DTOGame> dtoGameList) {
		ChooseAnAction actionToDo;
		Random random = new Random();
		int condizione; // se arriva a 4 vuol dire che il turno è finito
		do {
			condizione = 0;
			// obbligo di muovere
			if (!gameStatus.isMoved()) {
				actionToDo = new Move(gameStatus);
				do {
					dtoGame = new DTOGame();
					dtoGame = actionToDo.doAction(new DTOTurn(gameStatus
							.getPlayer().getSector().getAdjacent()
							.get(random.nextInt(6)), null, ActionType.MOVE));
					dtoGame = actionToDo.doAction(new DTOTurn(gameStatus
							.getPlayer().getSector().getAdjacent()
							.get(random.nextInt(6)), null, null));
				} while (dtoGame.getGameMessage() != "OK");
				gameStatus.setMoved(true);
				dtoGameList.add(dtoGame);
			} else
				condizione++;
			if (gameStatus.isMustDiscardItem()) { // non ha scartato
				dtoGame = new DTOGame();
				actionToDo = new DiscardItem(gameStatus);
				dtoGame = actionToDo.doAction(new DTOTurn(null,
						gameStatus.getPlayer().getItem().get(random.nextInt(4))
								.getType(), ActionType.DISCARDITEM));
				gameStatus.setMustDiscardItem(true);
				dtoGameList.add(dtoGame);
			} else
				condizione++;
			if (gameStatus.isMustDraw()) {
				dtoGame = new DTOGame();
				if (gameStatus.getPlayer().getSector().getType() == SectorType.DANGEROUS) { // verifica
																							// che
																							// debba
																							// pescare
																							// la
																							// carta
																							// settore
																							// pericoloso
					actionToDo = new DrawSectorCard(gameStatus);
					dtoGame = actionToDo.doAction(new DTOTurn(null, null,
							ActionType.DRAWSECTORCARD));
					gameStatus.setMustDraw(false);
					dtoGameList.add(dtoGame);
				}
			} else
				condizione++;
			// obbligo di scegliere settore per noise any sector
			if (gameStatus.isMustNoise()) {
				Coordinate coordRandom;
				do {
					coordRandom = new Coordinate(random.nextInt(22) + 1,
							random.nextInt(13) + 1); // sorteggio coordinata a
														// caso
				} while (gameStatus.getGame().getMap().isNull(coordRandom) == false);
				actionToDo = new SelectSectorNoise(gameStatus);
				dtoGame = actionToDo.doAction(new DTOTurn(coordRandom, null,
						ActionType.SELECTSECTORNOISE)); // usa il rumore a
														// casodto
				dtoGameList.add(dtoGame);
			} else
				condizione++;
		} while (condizione <= 3);
		return dtoGameList;
	}
}
