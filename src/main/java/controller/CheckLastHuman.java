package controller;

import model.Game;
import model.Player;
import model.PlayerState;
import model.PlayerType;
/**
 * this class controls if the player eliminated is the last human of the game
 * (that is, after eliminated, there are not other humans in game)
 * If he is the last, aliens win automatically.
 * 
 * @author Filippo
 *
 */
public class CheckLastHuman {
	
	private final Player attacked;
	private Game model;
	
	public CheckLastHuman(Player attacked, Game model){
		this.attacked = attacked;
		this.model = model;
	}
	
	public void checkAlienWin(){
		if(allHumansOut() && attacked.getType().equals(PlayerType.HUMAN)){
			setAlienWin();
		}
	}
	
	private boolean allHumansOut() {
		for (int i = 0; i < model.getPlayers().length; i++) {
			Player player = model.getPlayers(i);
			if (player.getType().equals(PlayerType.HUMAN) && player.isInGame())
				return false;
		}
		return true;
	}
	
	private void setAlienWin(){
		for (int i = 0; i < model.getPlayers().length; i++) {
			Player player = model.getPlayers(i);
			if (player.getType().equals(PlayerType.ALIEN)){
				player.setPlayerState(PlayerState.WINNER);
			}
		}
	}
	
	

	

}
