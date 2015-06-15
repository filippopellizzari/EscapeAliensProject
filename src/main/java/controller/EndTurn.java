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

	private GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param status
	 *            , the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public EndTurn(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	public boolean isEndTurn(){
		return status.isMoved() 
				&& !status.isMustDraw()
				&& !status.isMustDiscardItem() 
				&& !status.isMustNoise();
	}
	
	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (isEndTurn()) { 
			dtoGame.setGameMessage("Hai finito il turno");
			dtoGame.setReceiver(status.getPlayer().getNumber());
		}
		dtoGame.setGameMessage("Non hai completato tutte le azioni obbligatorie per finire il turno");
		return dtoGame;
	}

}
