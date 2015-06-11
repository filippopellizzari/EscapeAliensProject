package controller;

import dto.DTOSend;
import model.*;

/**
 * In this class there are some controls: 1. the player who send the message is
 * the player that has to do his turn; 2. control of his status (dead or alive);
 * 3. control about the right coordinates send by player; 4. control about the
 * possession of an item card.
 * 
 * @author Nicola
 *
 */

public class ControlDataReceived {

	private final DTOSend dtoSend;
	private final Game game;
	private final int numberPlayer;
	
	public ControlDataReceived(DTOSend dtoSend, Game game, int numberPlayer){
		this.dtoSend = dtoSend;
		this.game = game;
		this.numberPlayer = numberPlayer;
	}
	
	public String verify() {
		if (numberPlayer != dtoSend.getNumberPlayer()){
			return "Ora non è il tuo turno";
		}
		if (!game.getPlayers(numberPlayer).isAlive()){
			return "Sei fuori dal gioco!";
		}
		if (dtoSend.getCoordinate() != null) {
			if (game.getMap().isNull(dtoSend.getCoordinate()))
				return "Il settore non esiste sulla mappa";
		}
		if (dtoSend.getItemCardType() != null) {
			if(!hasItemCard(dtoSend.getItemCardType()))
				return "Non possiedi questa carta";
			
		}
		return null;
	}
	
	
	private boolean hasItemCard(ItemCardType type){
		for (int i = 0; i < game.getPlayers(numberPlayer).getItem().size(); i++){
			if (game.getPlayers(numberPlayer).getItem().get(i).getType().equals(type)){
				return true;
			}
		}
		return false;			
	}
}
