package controller;

import dto.DTOTurn;
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

	public Attack(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * this method contains the attack action; it invokes the private method
	 * "isDefendable" to control if the player attacked can use Defense ItemCard
	 * to not be eliminated;
	 * it is invoked by control of ItemCards, if the player is human
	 */
	public String attackMove() {
		String s = "";
		Sector current = gameStatus.getPlayerPlay().getSector();
		s += gameStatus.getPlayerPlay() + " : ATTACK in " + current + "\n"; //declares attack
		for (int i = 0; i < current.getPlayers().size() - 1; i++) {
			Player attacked = current.getPlayers().get(i);
			s += attacked + " è sotto attacco!\n";
			if (isDefendable(attacked)) {
				s += attacked + " : si salva grazie alla carta Difesa!\n";
			} else {
				if (gameStatus.getPlayerPlay().getType()
						.equals(PlayerType.ALIEN)
						&& attacked.getType().equals(PlayerType.HUMAN)) {
					gameStatus.getPlayerPlay().setSpeed(3); // alien feeding
				}
				s += attacked
						+ " è ucciso e viene eliminato dal gioco: la sua identità era "
						+ attacked.getType() + "\n";
				attacked.setAlive(false);
				for (int j = 0; j < attacked.getItem().size(); j++) { // discard cards of eliminated player		
					gameStatus.getGame().getItemCards()
							.discard(attacked.removeItem(j));
				}
			}
		}

		return s;
	}

	private boolean isDefendable(Player attacked) {
		for (int j = 0; j < attacked.getItem().size(); j++) {
			if (attacked.getItem().get(j).getType()
					.equals(ItemCardType.DEFENSE)) {
				gameStatus.getGame().getItemCards()
						.discard(attacked.removeItem(j)); // discard and use defense card
				return true;
			}
		}
		return false;
	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if (!gameStatus.hasMoved() && gameStatus.hasAttacked()
				&& gameStatus.isSolveSectorDuty()) { // attacco alieno
			return attackMove();
		} else
			return "Non puoi attaccare in questo momento";
	}

}
