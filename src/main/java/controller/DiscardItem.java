package controller;

import dto.*;
import model.ItemCardType;
import model.Player;

/**
 * This class provides the methods to discard the item card, if the player has
 * too many card (>3) or after using an item card
 * 
 * @author Nicola
 *
 */

public class DiscardItem implements ChooseAnAction {

	private GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param status
	 * 
	 */

	public DiscardItem(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * 
	 * @param type
	 *            type of itemCard to discard
	 */

	public void discardItem(ItemCardType type) {
		Player player = status.getPlayer();
		for (int i = 0; i < player.getItem().size(); i++) {
			if (player.getItem().get(i).getType().equals(type)) {
				status.getGame().getItemCards().discard(player.removeItem(i));
				return;// ne scarto una sola di quel tipo
			}
		}
	}

	/**
	 * a player can choose an action "discard item", if he has drawn the fourth
	 * itemCard but he has not used it immediately (he is obliged to discard,
	 * but he can choose when)
	 */
	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (status.isMustDiscardItem()) {
			discardItem(dtoTurn.getTypeCard());
			status.setMustDiscardItem(false);
			dtoGame.setActionType(dtoTurn.getActionType());
			dtoGame.setReceiver(9);
		} else {
			dtoGame.setReceiver(status.getPlayer().getNumber());
			dtoGame.setGameMessage("Non puoi scartare questa carta adesso");
		}
		return dtoGame;
	}

}
