package controller;

import dto.DTOGame;
import dto.DTOTurn;

/**
 * This class control if the player can pass or there are something else he/she
 * must to do to complete the turn
 * 
 * @author Nicola
 *
 */

public class EndTurn implements TryToDoAnAction {

	private GameStatus gameStatus;
	private DTOGame dtoGame;
	/**
	 * 
	 * @param gameStatus
	 *            , the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public EndTurn(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.dtoGame=new DTOGame();
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() && !gameStatus.isNoiseInAnySector()
				&& !gameStatus.isDiscardItemDuty()
				&& gameStatus.isSolveSectorDuty()) { // fine turno
			dtoGame.setGameMessage("Hai finito il turno");
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());
		}
		dtoGame.setGameMessage("Non hai completato tutte le azioni obbligatorie per finire il turno");
		return dtoGame;
	}

}
