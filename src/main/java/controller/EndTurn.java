package controller;

/**
 * This class control if the player can pass or there are something else he/she
 * must to do to complete the turn
 * 
 * @author Nicola
 *
 */

public class EndTurn implements TryToDoAnAction {

	private GameStatus gameStatus;

	/**
	 * 
	 * @param gameStatus
	 *            , the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public EndTurn(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() && !gameStatus.isNoiseInAnySector()
				&& !gameStatus.isDiscardItemDuty()
				&& gameStatus.isSolveSectorDuty()) { // fine turno
			return "Hai finito il turno";
		}
		return "Non hai completato tutte le azioni obbligatorie per finire il turno";
	}

}
