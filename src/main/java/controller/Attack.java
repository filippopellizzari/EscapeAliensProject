package controller;

import model.*;

/**
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
	 * mossa di attacco (vale per tutti i tipi di giocatore)
	 */
	public String attackMove() {
		String s = "";
		Sector current = gameStatus.getPlayerPlay().getSector();
		s += gameStatus.getPlayerPlay() + " : ATTACK in " + current + "\n"; //dichiara attacco
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
				for (int j = 0; j < attacked.getItem().size(); j++) { // scarto le carte del perdente							
					gameStatus.getGame().getItemCards()
							.discard(attacked.removeItem(j));
				}
			}
		}

		return s;
	}

	/**
	 * 
	 * @param attacked
	 *            , il giocatore attaccato
	 * @return true, se il giocatore attaccato possiede la carta Difesa
	 */

	private boolean isDefendable(Player attacked) {
		for (int j = 0; j < attacked.getItem().size(); j++) {
			if (attacked.getItem().get(j).getType()
					.equals(ItemCardType.DEFENSE)) {
				gameStatus.getGame().getItemCards()
						.discard(attacked.removeItem(j)); // scarto carta difesa
				return true;
			}
		}
		return false;
	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() && gameStatus.isAttack() == false
				&& gameStatus.isSolveSectorDuty()) { // attacco alieno
			return attackMove();
		} else
			return "Non puoi attaccare in questo momento";
	}

}
