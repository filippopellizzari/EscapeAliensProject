package controller;

import model.Game;
import model.Player;
import model.PlayerState;
import model.PlayerType;

/**
 * this class controls if the game is finished before all 39 rounds and
 * communicates how is finished
 * 
 * @author Filippo
 */

public class EndGame {

	private final Game model;

	public EndGame(Game model) {
		this.model = model;
	}

	/**
	 * This is the main method of this class and controls if game is finished.
	 * There are 3 cases of end Game: 
	 * 1)If in the last turn an alien has killed
	 * the last human in game, one of the aliens is surely winner; in this case,
	 * also other aliens win automatically (even if have been eliminated before)
	 * 2)if all human are in state of winners, it means that
	 * all have escaped successfully; 
	 * 3)if all HatchSectors are closed, but not
	 * all human are winner, it could mean two things: 
	 * a. some humans are been
	 * killed, but the last human is escaped successfully, so aliens have not
	 * exactly won; 
	 * b. some humans are still in the Game, but it's impossible
	 * for them to escape; in this last case, only humans escaped are winner.
	 * 
	 * 
	 * @return String, if game is finished, null otherwise
	 */
	public String control() {
		if (lastHumanKilled()) {
			return "L'ultimo umano in gioco è stato ucciso: gli alieni vincono";
		}
		if (allHumansWin()) {
			return "Tutti gli umani sono riusciti a scappare: gli umani vincono";
		}
		if (allHatchClosed()) {
			return "Non tutti gli umani sono riusciti a scappare: la partita finisce";
		}
		return null;
	}

	private boolean lastHumanKilled() {
		for (int i = 0; i < model.getPlayers().length; i++) {
			Player player = model.getPlayers(i);
			//se trovo un alieno winner, allora di sicuro nell'ultimo turno
			//ha ucciso l'ultimo umano in partita
			if (player.getType().equals(PlayerType.ALIEN)
					&& player.getPlayerState().equals(PlayerState.WINNER))
				return true;
		}
		return false;
	}

	private boolean allHumansWin() {
		for (int i = 0; i < model.getPlayers().length; i++) {
			Player player = model.getPlayers(i);
			if (player.getType().equals(PlayerType.HUMAN)
					&& !player.getPlayerState().equals(PlayerState.WINNER))
				return false;
		}
		return true;
	}

	private boolean allHatchClosed() {
		for (int i = 0; i < model.getMap().getHatchSectors().size(); i++) {
			if (!model.getMap()
					.getSector(model.getMap().getHatchSectors().get(i))
					.isClosed())
				return false;
		}
		return true;
	}

}
