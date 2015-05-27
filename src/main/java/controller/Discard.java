package controller;

import dto.*;
import model.ItemCardType;

/**
 * This class provides the methods to discard the item card, if the player has
 * too many card (>3)
 * 
 * @author Nicola
 *
 */

public class Discard implements TryToDoAnAction {

	private GameStatus gameStatus;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param gameStatus
	 *            the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public Discard(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.dtoGame=new DTOGame();
	}

	/**
	 * 
	 * @param type
	 *            , discard the correct type of card passed from the player
	 */

	private void discard(ItemCardType type) {
		for (int i = 0; i < gameStatus.getPlayerPlay().getItem().size(); i++) {
			if (gameStatus.getPlayerPlay().getItem().get(i).getType()
					.equals(type)) {
				gameStatus.getGame().getItemCards()
						.discard(gameStatus.getPlayerPlay().removeItem(i));
			}
		}
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() && gameStatus.isDiscardItemDuty()
				&& dtoTurn.getTypeCard() != null) { // scarta carta
			discard(dtoTurn.getTypeCard());
			dtoGame.setDestination(9);	//destinatari messaggio
		} else {
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());		//destinatario messaggio
			dtoGame.setGameMessage("Non puoi scartare questa carta adesso");
		}
		return dtoGame;
	}

}
