package controller;

import dto.DTOGame;
import dto.DTOTurn;

/**
 * This class controls if the player can pass or there are something else he/she
 * must to do to complete the turn
 * 
 * @author Nicola
 *
 */

public class EndTurn implements ChooseAnAction {

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
		if (gameStatus.isMoved() && !gameStatus.isMustDraw()
				&& !gameStatus.isMustDiscardItem()
				&& !gameStatus.isMustNoise()) { // fine turno
			dtoGame.setGameMessage("Hai finito il turno");
			dtoGame.setReceiver(gameStatus.getPlayer().getNumber());
		}
		dtoGame.setGameMessage("Non hai completato tutte le azioni obbligatorie per finire il turno");
		return dtoGame;
	}

}
