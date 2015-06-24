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
	
	private final Player player;
	private final Player attacked;
	private Game model;
	
	/**
	 * Creates the class with player and the player who has to attack
	 * @param player
	 * @param attacked
	 * @param model
	 */
	
	public CheckLastHuman(Player player, Player attacked, Game model){
		this.player = player;
		this.attacked = attacked;
		this.model = model;
	}
	
	/**
	 * Controls if the human killed is the last human alives
	 */

	public void check(){
		//non serve controllare che chi attacca è alieno; 
		//se chi attacca è umano, allora di sicuro non tutti gli umani 
		//sono fuori dal gioco
		if( attacked.getType().equals(PlayerType.HUMAN) 
			&& allHumansOut() ){
			player.setPlayerState(PlayerState.WINNER);
		}
		
	}
	
	/**
	 * Controls one by one the human, if there is one alive return false
	 * @return
	 */
	
	private boolean allHumansOut() {
		for (int i = 0; i < model.getPlayers().length; i++) {
			Player player = model.getPlayers(i);
			if (player.getType().equals(PlayerType.HUMAN) && player.isInGame())
				return false;
		}
		return true;
	}
}
