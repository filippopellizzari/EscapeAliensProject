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

	/**
	 * 
	 * @param gameStatus, status of game, contails all the information about the action that the player has done
	 * and has to do
	 */
	
	public Attack(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.dtoGame=new DTOGame();
	}

	/**
	 * this method contains the attack action; it invokes the private method
	 * "isDefendable" to control if the player attacked can use Defense ItemCard
	 * to not be eliminated;
	 * it is invoked by control of ItemCards, if the player is human
	 * For every died player a coordinate and its corresponding type are saved in the dtoGame, in the right cell corresponding
	 * by its number (numberOfPlayer)
	 */
	
	public DTOGame attackMove() {
		dtoGame.setDestination(9);
		dtoGame.setTypeItemCard(ItemCardType.ATTACK); 	//imposta la carta attacco così che tutti possano visualizzarla
		gameStatus.setAttack(true); 		//segnala che hai attaccato
		Sector current = gameStatus.getPlayerPlay().getSector();
		for (int i = 0; i < current.getPlayers().size() - 1; i++) {
			Player attacked = current.getPlayers().get(i);
			if (isDefendable(attacked)) {
				dtoGame.setGameMessage(attacked + " : si salva grazie alla carta Difesa!\n");	//metti nel game message il messaggio relativo al salvataggio
			} else {
				dtoGame.setCoordinate(attacked.getSector().getCoordinate(),attacked.getNumber());	//segnala la coordinata dei giocatori eliminati
				dtoGame.setTypePlayer(attacked.getType(),attacked.getNumber()); 	//tipo del giocatore ucciso
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
		return dtoGame;
	}
	
	/**
	 * 
	 * @param attacked
	 * @return true if the player survive using the defense card, false if he dies, if he survive the card is discarded
	 */

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
			dtoGame.setGameMessage("Non puoi attaccare in questo momento\n");	//se non può attaccare avvisa solo chi ha fatto l'azione
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber()); 	//destinatario solo utente azione
		}
		return dtoGame;
	}
}
