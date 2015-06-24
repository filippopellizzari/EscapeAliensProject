package controller;

import dto.DTOSend;
import model.*;

/**
 * This class is used to control if it is the turn of a player trying to do an
 * action and if he is out of game; and then,control if some data of dtoSend are
 * right: coordinates exist in the map, a particular type of itemCard is in the
 * player's deck
 * 
 * 
 * @author Filippo
 * @author Nicola
 *
 */

public class ControlDataReceived {

	private final DTOSend dtoSend;
	private final Game game;
	private final int numberPlayer;
	private final Player player;

	public ControlDataReceived(DTOSend dtoSend, Game game, int numberPlayer) {
		this.dtoSend = dtoSend;
		this.game = game;
		this.numberPlayer = numberPlayer;
		this.player = game.getPlayers(numberPlayer);
	}

	public String verify() {
		if (!player.isInGame()) {
			return "Sei fuori dal gioco!";
		}
		
		if (numberPlayer != dtoSend.getNumberPlayer()) {
			return "Ora non è il tuo turno";
		}
		
		if (dtoSend.getCoordinate() != null) {
			if (game.getMap().isNull(dtoSend.getCoordinate()))
				return "Il settore non esiste sulla mappa";
		}
		if (dtoSend.getItemCardType() != null) {
			if (!hasItemCard(dtoSend.getItemCardType()))
				return "Non possiedi questa carta";

		}
		return null;
	}

	private boolean hasItemCard(ItemCardType type) {
		for (int i = 0; i < player.getItem().size(); i++) {
			if (player.getItem().get(i).getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
