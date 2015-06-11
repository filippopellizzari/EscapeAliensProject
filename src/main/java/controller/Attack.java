package controller;

import dto.*;
import model.*;

/**
 * This class contains the control of validity of an attack action in the case
 * of alien the effective attack can be done also by human
 * 
 * @author Filippo
 *
 */

public class Attack implements ChooseAnAction {

	private GameStatus status;
	private final DTOGame dtoGame;

	/**
	 * 
	 * @param status
	 *            , status of game, contails all the information about the
	 *            action that the player has done and has to do
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
		dtoGame.setCoordinate(current.getCoordinate(), player.getNumber()); //segnala attacco in coordinata
		for (int i = 0; i < current.getPlayers().size() - 1; i++) {
			Player attacked = current.getPlayers().get(i);
			if (isDefendable(attacked)) {
				dtoGame.setGameMessage(attacked
						+ " : si salva grazie alla carta Difesa\n"); 
			} else {
				dtoGame.setPlayerType(attacked.getType(), attacked.getNumber());//segnala tipo del giocatore eliminato 
				alienFeeding(player,attacked);
				attacked.setAlive(false);
				attacked.setSector(null);
				current.getPlayers().remove(i);
				for (int j = 0; j < attacked.getItem().size(); j++) { 
					status.getGame().getItemCards().discard(attacked.removeItem(j));
				}
			}
		}
		return dtoGame;
	}

	private boolean isDefendable(Player attacked) {
		for (int j = 0; j < attacked.getItem().size(); j++) {
			if (attacked.getItem().get(j).getType()
					.equals(ItemCardType.DEFENSE)) {
				status.getGame().getItemCards()
						.discard(attacked.removeItem(j)); // discard and use
															// defense card
				return true;
			}
		}
		return false;
	}
	
	private void alienFeeding(Player player, Player attacked){
		if (player.getType().equals(PlayerType.ALIEN)
				&& attacked.getType().equals(PlayerType.HUMAN)) {
			player.setSpeed(3); 
		}
		
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) { 
		if (status.isMoved() && !status.isAttacked()){ //solo per attacco alieno
			attackMove();
			status.setAttacked(true);
			status.setMustDraw(false);
			dtoGame.setReceiver(9);
		} else {
			dtoGame.setGameMessage("Non puoi attaccare in questo momento\n"); 
			dtoGame.setReceiver(status.getPlayer().getNumber()); //notifica privata
		}
		return dtoGame;
	}


	
	
	
}
