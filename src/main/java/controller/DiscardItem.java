package controller;

import dto.DTOTurn;
import model.ItemCardType;

/**
 * This class provides the methods to discard the item card, if the player has
 * too many card (>3)
 * 
 * @author Nicola
 *
 */

public class DiscardItem implements TryToDoAnAction {

	private GameStatus gameStatus;

	/**
	 * 
	 * @param gameStatus
	 *            the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public DiscardItem(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
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
	public String doAction(DTOTurn dtoTurn) {
		if (gameStatus.hasMoved() && gameStatus.isDiscardItemDuty()
				&& dtoTurn.getTypeCard() != null) { // scarta carta
			discard(dtoTurn.getTypeCard());
			return "Carta scartata";
		} else
			return "Non puoi scartare questa carta adesso";
	}

}
