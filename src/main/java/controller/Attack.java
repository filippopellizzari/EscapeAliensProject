package controller;

import dto.*;
import model.*;

/**
 * This class contains the control of validity of an attack action in the case
 * of alien the effective attack can be done also by human
 * 
 * @author Filippo
 * @see ChooseAnAction
 */

public class Attack implements ChooseAnAction {

	private GameStatus status;
	private final DTOGame dtoGame;

	/**
	 * 
	 * @param status status of game, contains all the information about the action
	 * that the player has done and has to do
	 */

	public Attack(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * this method contains the attack action; it invokes the private method
	 * "isDefendable" to control if the player attacked can use Defense ItemCard
	 * to not be eliminated; it is invoked by control of ItemCards, if the
	 * player is human. For every died player a coordinate and its corresponding
	 * type are saved in the dtoGame, in the right cell corresponding by its
	 * number (numberOfPlayer)
	 */

	public DTOGame attackMove() {
		Player player = status.getPlayer();
		Sector current = player.getSector();
		// segnala attacco in coordinata corrente
		dtoGame.setCoordinate(current.getCoordinate(), player.getNumber());
		// non considero il giocatore che sta attaccando
		// (si è appena mosso, quindi è l'ultimo della lista dei giocatori in un
		// settore)
		int playersAttackable = current.getPlayers().size() - 1;
		for (int i = 0; i < playersAttackable; i++) {
			Player attacked = current.getPlayers().get(0);
			if (isDefendable(attacked)) {
				dtoGame.setNumberPlayerDefense(attacked.getNumber());
				// sposto giocatore in fondo alla lista
				current.addPlayer(current.removePlayer());
			} else {
				// segnala tipo del giocatore eliminato
				dtoGame.setPlayerType(attacked.getType(), attacked.getNumber());
				attacked.setInGame(false);
				attacked.setPlayerState(PlayerState.KILLED);
				checkAlienFeeding(player, attacked);
				new CheckLastHuman(player, attacked, status.getGame()).check();
				removeAllItems(attacked);
				attacked.setSector(null);
				// rimuovo giocatore dalla lista
				current.removePlayer();

			}
		}
		// sposto in fondo alla lista giocatore che ha attaccato
		// (non è più l'ultimo, se alcuni giocatori si sono salvati)
		current.addPlayer(current.removePlayer());
		return dtoGame;
	}

	/**
	 * Removes all items from dead players
	 * @param attacked
	 */
	
	private void removeAllItems(Player attacked) {
		int numItems = attacked.getItem().size();
		for (int i = 0; i < numItems; i++) {
			status.getGame().getItemCards().discard(attacked.removeItem(0));
		}
	}
	
	/**
	 * Controls if a player has a defense card and uses this card
	 * @param attacked
	 * @return
	 */

	private boolean isDefendable(Player attacked) {
		for (int j = 0; j < attacked.getItem().size(); j++) {
			if (attacked.getItem().get(j).getType()
					.equals(ItemCardType.DEFENSE)) {
				// discard and use defense ItemCard
				status.getGame().getItemCards().discard(attacked.removeItem(j));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * If a alien kills a players increases his movements by 1
	 * @param player
	 * @param attacked
	 */
	
	private void checkAlienFeeding(Player player, Player attacked) {
		if (player.getType().equals(PlayerType.ALIEN)
				&& attacked.getType().equals(PlayerType.HUMAN)) {
			player.setSpeed(3);
		}
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if(status.getPlayer().getType().equals(PlayerType.HUMAN)){
			dtoGame.setGameMessage("Puoi attaccare solo con una carta oggetto");
			dtoGame.setReceiver(status.getPlayer().getNumber());
			return dtoGame;
		}
		if (status.isMoved() && !status.isAttacked()) {
			attackMove();
			status.setAttacked(true);
			status.setMustDraw(false);
			dtoGame.setActionType(ActionType.ATTACK);
			dtoGame.setReceiver(9);
		} else {
			dtoGame.setGameMessage("Non puoi attaccare in questo momento");
			dtoGame.setReceiver(status.getPlayer().getNumber());
		}
		return dtoGame;
	}

}
