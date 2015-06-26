package controller;

import model.Game;
import model.Player;
import model.PlayerState;
import model.PlayerType;
/**
 * This class controls if the player eliminated is the last human of the game
 * (after eliminated, there are not other humans in game).
 * If he is the last, alien who has attacked is set to winner
 * 
 * @author Filippo
 *
 */
public class CheckLastHuman {
	
	private final Player alien;
	private final Player attacked;
	private Game model;
	
	/**
	 * Creates the class with player and the player who has to attack
	 * @param alien
	 * @param attacked
	 * @param model
	 */
	
	public CheckLastHuman(Player alien, Player attacked, Game model){
		this.alien = alien;
		this.attacked = attacked;
		this.model = model;
	}
	
	/**
	 * Controls if the human killed is the last human alives
	 */

	public void check(){
		if( attacked.getType().equals(PlayerType.HUMAN) 
			&& allHumansOut() ){
			alien.setPlayerState(PlayerState.WINNER);
		}
		
	}
	
	/**
	 * Controls one by one the human, if there is one alive return false
	 * @return
	 */
	
	private boolean allHumansOut() {
		for (int i = 0; i < model.getPlayers().length; i++) {
			Player p = model.getPlayers(i);
			if (p.getType().equals(PlayerType.HUMAN) && p.isInGame())
				return false;
		}
		return true;
	}
}
