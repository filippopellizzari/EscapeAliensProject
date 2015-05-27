package controller;

import dto.*;
import model.*;

/**
 * This class contains the control of validity of an attack action in the case of alien 
 * the effective attack can be done also by human
 * 
 * @author Filippo
 *
 */

public class Attack implements TryToDoAnAction {

	private GameStatus gameStatus;
	private DTOGame dtoGame;

	public Attack(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.dtoGame=new DTOGame();
	}

	/**
	 * this method contains the attack action; it invokes the private method
	 * "isDefendable" to control if the player attacked can use Defense ItemCard
	 * to not be eliminated;
	 * it is invoked by control of ItemCards, if the player is human
	 */
	
	public DTOGame attackMove() {
		dtoGame.setDestination(9);
		String s = "";
		Sector current = gameStatus.getPlayerPlay().getSector();
		for (int i = 0; i < current.getPlayers().size() - 1; i++) {
			Player attacked = current.getPlayers().get(i);
			if (isDefendable(attacked)) {
				s += attacked + " : si salva grazie alla carta Difesa!\n";
			} else {
				dtoGame.setCoordinate(attacked.getSector().getCoordinate(),attacked.getNumber());		//segnala la coordinata dei giocatori eliminati
				if (gameStatus.getPlayerPlay().getType().equals(PlayerType.ALIEN)
						&& attacked.getType().equals(PlayerType.HUMAN)) {
					gameStatus.getPlayerPlay().setSpeed(3); // alien feeding
				}
				attacked.setAlive(false);
				for (int j = 0; j < attacked.getItem().size(); j++) { // discard cards of eliminated player		
					gameStatus.getGame().getItemCards()
							.discard(attacked.removeItem(j));
				}
			}
		}
		dtoGame.setGameMessage(s);
		return dtoGame;
	}

	private boolean isDefendable(Player attacked) {
		for (int j = 0; j < attacked.getItem().size(); j++) {
			if (attacked.getItem().get(j).getType()
					.equals(ItemCardType.DEFENSE)) {
				gameStatus.getGame().getItemCards()
						.discard(attacked.removeItem(j)); // discard defense and use it
				return true;
			}
		}
		return false;
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (!gameStatus.isMove() && gameStatus.isAttack()
				&& gameStatus.isSolveSectorDuty()) { // attacco alieno
			attackMove();
		}
		else {
			dtoGame.setGameMessage("Non puoi attaccare in questo momento\n");
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber()); 	//destinatario solo utente azione
		}
		return dtoGame;
	}
}
