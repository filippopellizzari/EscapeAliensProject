package controller;

import dto.*;
import model.ItemCardType;

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
	 * @param gameStatus, the status of a turn, reference at model and the player who
	 * are playing, now is his turn
	 */

	public DiscardItem(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * 
	 * @param type, type of itemCard to discard
	 */

	public void discardItem(ItemCardType type) {
		for (int i = 0; i < status.getPlayer().getItem().size(); i++) {	
			if (status.getPlayer().getItem().get(i).getType().equals(type)) {
				status.getGame().getItemCards().discard(status.getPlayer().removeItem(i));
				return;			
			}
		}
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (status.isMoved() && status.isMustDiscardItem()){ 
			discardItem(dtoTurn.getTypeCard());
			status.setMustDiscardItem(false);
			dtoGame.setReceiver(9);	
		} else {
			dtoGame.setReceiver(status.getPlayer().getNumber());		
			dtoGame.setGameMessage("Non puoi scartare questa carta adesso");
		}
		return dtoGame;
	}

}
