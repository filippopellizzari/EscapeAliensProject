package controller;

import dto.*;
import model.*;

/**
 * This class is used when a player is in a dangerous sector and wants to draw a
 * sector dangerous card; a sector card could have an item icon and, in this
 * case, a player must draw an item card
 * 
 * @author Filippo
 * @author Nicola
 *
 */

public class DrawSectorCard implements ChooseAnAction {

	private GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param status
	 *            the status of the turn
	 */

	public DrawSectorCard(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * Draw a dangerous sector card and solve the effect immediately if possible
	 */

	public void drawSectorCard() {
		SectorCard current = status.getGame().getSectorCards().draw();
		SectorCardType type = current.getType();
		dtoGame.setSectorCardType(type);
		switch (type) {
		case NOISEANY:
			status.setMustNoise(true);
			// unica volta che il messaggio è privato ma parte 
			// di esso va messo nel buffer
			// per essere poi dato a tutti i giocatori
			dtoGame.setReceiver(10);
			break;
		case NOISEYOUR:
			dtoGame.setReceiver(9);
			dtoGame.setCoordinate(status.getPlayer().getSector()
					.getCoordinate(), status.getPlayer().getNumber());
			break;
		case SILENCE:
			dtoGame.setReceiver(9);
			break;
		default:
			break;
		}
		if (current.isItemIcon()) { // vedi se devi pescare carta oggetto
			drawItemCard();
		}
		//scarta carta settore
		status.getGame().getSectorCards().discard(current); 
	}

	/**
	 * draw an item card; if all cards are finished, the player does not draw
	 * anything; if he draws the fourth itemCard, he is obliged to discard or
	 * use one of his items before the end of his turn
	 * 
	 */
	public void drawItemCard() {
		ItemCard current = status.getGame().getItemCards().draw();
		if (current == null) {
			dtoGame.setGameMessage("Sono finite le carte oggetto da pescare!");
		} else {
			Player player = status.getPlayer();
			ItemCardType type = current.getType();
			player.addItem(current);
			// solo chi ha pescato conosce la carta oggetto
			dtoGame.setItemCardType(type);
			if (player.getItem().size() == 4) {
				dtoGame.setGameMessage("Hai 4 carte oggetto: devi giocarne una subito o scartarne una");
				// obbligato a scartarne una (finchè non la scarta o ne gioca
				// una)
				status.setMustDiscardItem(true);
			}
		}
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (status.isMustDraw()) {
			drawSectorCard();
			status.setMustDraw(false);
			status.setAttacked(true);// non potrà più attaccare
			dtoGame.setActionType(ActionType.DRAWSECTORCARD);
		} else {
			dtoGame.setGameMessage("Non puoi pescare in questo momento");
			dtoGame.setReceiver(status.getPlayer().getNumber());
		}
		return dtoGame;
	}
	
	/**
	 * Return the dtoGame
	 */

	public DTOGame getDtoGame() {
		return dtoGame;
	}

}
