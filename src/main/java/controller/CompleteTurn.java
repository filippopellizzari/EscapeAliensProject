package controller;

import java.util.Random;

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
	 * @return the action happen during the actions
	 */

	public String completeTurn() {
		TryToDoAnAction actionToDo;
		Random random = new Random(); 
		int condizione; // se arriva a 4 vuol dire che il turno è finito
		String response = "";
		do {
			condizione = 0;
			if (!gameStatus.isMove()) { // non ha mosso
				actionToDo = new MoveRules(gameStatus);
				do {
					response = actionToDo.doAction(new DTOTurn(gameStatus
							.getPlayerPlay().getSector().getAdjacent()
							.get(random.nextInt(6)), null, null));
				} while (response != "OK");
			} else
				condizione++;
			if (gameStatus.isDiscardItemDuty()) { // non ha scartato
				actionToDo = new Discard(gameStatus);
				response = actionToDo.doAction(new DTOTurn(null, gameStatus
						.getPlayerPlay().getItem().get(random.nextInt(4))
						.getType(), null));
			} else
				condizione++;
			if (gameStatus.isSolveSectorDuty() == false) {
				if (gameStatus.getPlayerPlay().getSector().getType() == SectorType.DANGEROUS) { // verifica
																								// che
																								// debba
																								// pescare
																								// la
																								// carta
																								// settore
																								// pericoloso
					actionToDo = new Draw(gameStatus);
					response = actionToDo
							.doAction(new DTOTurn(null, null, null));
				} else
					condizione++;
			} else
				condizione++;
			if (gameStatus.isNoiseInAnySector()) { // non ha usato il rumore
				Coordinate coordinateRandom;
				do {
					coordinateRandom = new Coordinate(random.nextInt(22) + 1,
							random.nextInt(13) + 1); // sorteggio coordinata a
														// caso
				} while (gameStatus.getGame().getMap().isNull(coordinateRandom) == false);
				actionToDo = new SelectSectorNoise(gameStatus);
				Coordinate coordinate; // coordinata a caso
				response = actionToDo.doAction(new DTOTurn(coordinateRandom,
						null, null));
				// usa il rumore a caso
			} else
				condizione++;
		} while (condizione <= 3);
		return response;
	}
}
